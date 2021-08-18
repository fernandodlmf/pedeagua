package com.pedeagua.login;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.projetofinal1.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityCadastro extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityCadastro.this;
	Button btCadastrar_2;
	EditText etNomeC, etEmailC, etTelefoneC, etCriarSenhaC, etConfirmarSenhaC;
	TextView tvFecharCadastro;
	public static String etNomeCTemp = "", etEmailCTemp = "",etTelefoneCTemp = "", etCriarSenhaCTemp = "";
	String senhaMd5= "a";
	RadioButton rBTermos_1;
	int duracao = Toast.LENGTH_SHORT;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro);
        
       
        
        this.setFinishOnTouchOutside(false); // IMPEDE QUE O DIÁLOGO SEJA FECHADO COM TOQUE FORA DA VIEW

        etNomeC = (EditText) findViewById(R.id.etNomeC);
        etEmailC = (EditText) findViewById(R.id.etEmailC);
        etTelefoneC = (EditText) findViewById(R.id.etTelefoneC);
	    etCriarSenhaC = (EditText) findViewById(R.id.etCriarSenhaC);
	    etConfirmarSenhaC = (EditText) findViewById(R.id.etConfirmarSenhaC);
	    
	    rBTermos_1 = (RadioButton)findViewById(R.id.rBATermos_1);
	    btCadastrar_2 = (Button) findViewById(R.id.btConfirmar_Termos);
	    tvFecharCadastro = (TextView) findViewById(R.id.tvFecharCadastro);
	        
	        
	        
	    rBTermos_1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					
					startActivity(new Intent(ctx, ActivityTermos.class));	
					
				} catch (Exception e) {
					Log.i("TAG", "DENTRO = ENTROU NO CATCH" + e);
					Toast.makeText(ctx, "Erro ao abrir ENDEREÇO" +e.getMessage().toString(), duracao).show();
				}
				
			}
		});
	    
		btCadastrar_2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						if(etNomeC.getText().toString().equals("") ||
							etEmailC.getText().toString().equals("") ||
							etCriarSenhaC.getText().toString().equals("") || 
							etConfirmarSenhaC.getText().toString().equals("") ||
							etTelefoneC.getText().toString().equals("")) {
							
							Toast.makeText(ctx, "Os campos não podem estar vazios", duracao).show();
							
						} else { 
							
							final String Senha1 = etCriarSenhaC.getText().toString();
							final String Senha2 = etConfirmarSenhaC.getText().toString();
							//Log.i("TAG", "DENTRO =" + etCriarSenhaC.getText());
							//Log.i("TAG", "DENTRO =" + etConfirmarSenhaC.getText());
							
							
							//ESTE IF DETERMINA A VALIDAÇÃO DAS SENHAS DIGITADAS
							if(Senha1.equals(Senha2)){
								
								if(rBTermos_1.isChecked()) {
									try {
										criptografaSenha();
										
										etNomeCTemp = etNomeC.getText().toString();
										//USANDO MÉTODO DE VALIDAÇÃO DE EMAIL

										etTelefoneCTemp = etTelefoneC.getText().toString();
										etTelefoneCTemp = etTelefoneCTemp.replaceAll("[(]","");
										etTelefoneCTemp = etTelefoneCTemp.replaceAll("[)]","");
										etTelefoneCTemp = etTelefoneCTemp.replaceAll("[-]","");
										etCriarSenhaCTemp = senhaMd5;
														
										if(validaEmail(etEmailC.getText().toString())){
											
												etEmailCTemp = etEmailC.getText().toString();
												startActivity(new Intent(ctx, ActivityEndereco.class));

												finish();
											
											
										
										}else {
											etEmailC.setText("");
											Toast.makeText(ctx,"Ops, seu email não é válido.", Toast.LENGTH_LONG).show();
										};
										
										
									} catch (NoSuchAlgorithmException e) {

										e.printStackTrace();
									}	
								} else {
									Toast.makeText(ctx, "Por favor, aceite os termos de uso.", duracao).show();									
								}
																
								
								
							} else {
								
								Toast.makeText(ctx, "Senhas incorretas!", duracao).show();
								setarNull();
										
							}								
						}
					}
				});
		
			    
    } // FIM DO ONCREATE
    
    public boolean validaEmail(String email){
    	if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
      
    
    public void criptografaSenha() throws NoSuchAlgorithmException{

    		String senha = etCriarSenhaC.getText().toString();
    		
			MessageDigest md5;
		
		
				md5 = MessageDigest.getInstance("MD5");
				md5.update(senha.getBytes(),0,senha.length());
				
				BigInteger i = new BigInteger(1, md5.digest()); 
				
				this.senhaMd5 = String.format("%1$032X", i); 

    }

    // INÍCIO DO MÉTODO PARA LIMPAR OS EDITTEXTS DO CADASTRO
    
    public void setarNull () { // INÍCIO 

	    try {
		    etCriarSenhaC.setText("");
		    etConfirmarSenhaC.setText(""); 
	    }
	    catch (Exception e) {
			
		}
	} // FIM 
    
    public void tvFechar (View view) {
    	Toast.makeText(ctx, "Cadastro não realizado.", duracao).show();
    	finish();
    }
								
} // FIM DA ACTIVITY CADASTRO
