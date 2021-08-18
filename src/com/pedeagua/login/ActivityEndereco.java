package com.pedeagua.login;



import com.pedeagua.testews.Endereco;
import com.pedeagua.testews.Usuario;
import com.pedeagua.testews.UsuarioDAO;
import com.pedeagua.vendedores.ActivityVendedores;

import br.com.projetofinal1.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityEndereco extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityEndereco.this;
	String Nome, Email, Senha, Telefone;
	Button btConfirmar_Cadastro;
	EditText etRua, etBairro, etCep, etNumero, etComplemento, etCidade;
	RadioButton rBTermos_1;
	int duracao = Toast.LENGTH_LONG;
	UsuarioDAO userDAO = null;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_endereco);
       
        this.setFinishOnTouchOutside(false);
      
        if(userDAO == null){
        	userDAO = new UsuarioDAO();
        }
        
        etRua = (EditText) findViewById(R.id.etRua);
        etBairro = (EditText) findViewById(R.id.etBairro);
        etCep = (EditText) findViewById(R.id.etCep);
        etNumero = (EditText) findViewById(R.id.etNumero);
        etComplemento = (EditText) findViewById(R.id.etComplemento);
        etCidade = (EditText) findViewById(R.id.etCidade);
        
        Nome = ActivityCadastro.etNomeCTemp;
        Email = ActivityCadastro.etEmailCTemp;
        Telefone = ActivityCadastro.etTelefoneCTemp;
        Senha = ActivityCadastro.etCriarSenhaCTemp;
      
        
        btConfirmar_Cadastro = (Button) findViewById(R.id.btConfirmar_Cadastro);
        
        listners();    
    }
    
    public void listners(){
    	try {

    		btConfirmar_Cadastro.setOnClickListener(new View.OnClickListener() {
    					
    					@Override
    					public void onClick(View v) {

    						try {
    							
    							if(etRua.getText().toString().equals("") ||
    							   etBairro.getText().toString().equals("") ||
    							   etCep.getText().toString().equals("") || 
    							   etNumero.getText().toString().equals("") ||
    							   etComplemento.getText().toString().equals("") ||
    							   etCidade.getText().toString().equals("")) {
    									
    									Toast.makeText(ctx, "Os campos não podem estar vazios", duracao).show();
    									
    								}else {
    									terminarCadastro();
    									finish();
    								}
    						
							} catch (Exception e) {
								e.printStackTrace();
							}
    					}
    				});
			
		} catch (Exception e) { 
			
		}
    }
    
    public void terminarCadastro () {
		try {
						
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			Endereco endereco = new Endereco();
				endereco.setRua(etRua.getText().toString());
				endereco.setBairro(etBairro.getText().toString());
				endereco.setCep(Integer.parseInt(etCep.getText().toString()));
				endereco.setNumero(Integer.parseInt(etNumero.getText().toString()));
				endereco.setComplemento(etComplemento.getText().toString());
				endereco.setCidade(etCidade.getText().toString());
				endereco.setEstado(null);
			
			int id_retornado_endereco = userDAO.inserirEndereco(endereco);
			Log.i("endereco", "endereco="+id_retornado_endereco);
			
			if (id_retornado_endereco >  0) {
				Usuario	user = new Usuario();
					user.setNome(Nome);
					user.setSenha(Senha);
					user.setEmail(Email);
					user.setCod_endereco(id_retornado_endereco);
					user.setTelefone(Telefone);
					user.setEh_vendedor(0);
					user.setEh_cliente(1);
					user.setAtivo(1);					
					
				boolean resposta1 = userDAO.inserirCliente(user);

				if(resposta1 == true) {
					
					Toast.makeText(ctx, "Dados salvos com sucesso!", duracao).show();
			
					startActivity(new Intent(ctx, ActivityVendedores.class));
					
				}  else {	
					Toast.makeText(ctx, "Não foi possível salvar os dados",
							Toast.LENGTH_LONG).show();
				}
				
				user =null;
			} else {
				Toast.makeText(ctx, "Não foi possível salvar endereço.",
						Toast.LENGTH_LONG).show();
			}
			
			endereco = null;

		} catch (Exception e){	
			Toast.makeText(ctx, "Erro ao salvar.: " + e.getMessage().toString(), duracao).show();
		}

    } // FIM
    
    public void tvFechar (View view) {
    	Toast.makeText(ctx, "Cadastro não realizado.", duracao).show();
    	finish();
    }
		
} // FIM DA ACTIVITY CADASTRO
