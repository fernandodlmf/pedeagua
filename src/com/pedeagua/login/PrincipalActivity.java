package com.pedeagua.login;


import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.math.*;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.pedeagua.SQLite.BD;
import com.pedeagua.testews.Usuario;
import com.pedeagua.testews.UsuarioDAO;
import com.pedeagua.vendedores.ActivityVendedores;

import br.com.projetofinal1.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("InlinedApi") public class PrincipalActivity extends Activity {
	
	Button btEntrar, btCadastrar_1, btFacebook;
	EditText etNome,etSenha;
	int duracao = Toast.LENGTH_SHORT;
	Context ctx = PrincipalActivity.this;
	public static int cod_cliente = 0;
	BD bd;
	UsuarioDAO userDAO = null;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) { // IN�?CIO DO ONCREATE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        
      
        if(bd == null){
        	bd = new BD(ctx);
        }
        bd.deleteAll();
        
        if(userDAO == null){
        	userDAO = new UsuarioDAO();
        }
        
        
      //BOTÕES
		btEntrar = (Button) findViewById(R.id.btEntrar);
		btCadastrar_1 = (Button) findViewById(R.id.btCadastrar_1);
		btFacebook = (Button) findViewById(R.id.bt_facebook);
		
		//EDITTEXTS
		etNome = (EditText) findViewById(R.id.etNome);
		etSenha = (EditText) findViewById(R.id.etSenha);
		
		
		    
		try {
			listners();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    } // FIM DO ONCREATE
 

    public void listners ()  throws NoSuchAlgorithmException {
    	
    	try {
    	
	    	btEntrar.setOnClickListener(new View.OnClickListener() {
		
				@Override
				public void onClick(View arg0) {
					entrar();
				}
			});   // AQUI TERMINA O BOTAO ENTRAR 
	    	
	    btCadastrar_1.setOnClickListener(new View.OnClickListener() {// INICIO BTCADASTRAR_1
				
			@Override
			public void onClick(View arg0) {
			
				Intent cadastro = new Intent(ctx, ActivityCadastro.class);
				startActivity(cadastro);
					
				}
			});
	    
	    	} catch (Exception erro){}
    	
    	}	
    
    public void recuperarSenha (View view) {
    	
    	try{
    		
        	startActivity(new Intent(ctx,ActivityRecuperarSenha.class));

    	}catch(Exception e){
    		Log.i("TAG", "DENTRO = ENTROU NO CATCH" +e);
    	}
    	
    }
    
    public void entrar() {

		try {	
			
			
			if(etNome.equals("") || etSenha.equals("")){
				
				Toast.makeText(ctx, "Os campos não podem estar vazios", duracao).show();	
			} else {
				
				String senha = etSenha.getText().toString();

	    		MessageDigest md5 = MessageDigest.getInstance("MD5");
	    		md5.update(senha.getBytes(),0,senha.length());
	    			
	    		BigInteger i = new BigInteger(1, md5.digest()); 

	    		String string = "a";
	    				
	    		string = String.format("%1$032X", i);
	    		Log.i("senha", "senhaMD5 = "+ string);
				
				
				
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    		StrictMode.setThreadPolicy(policy);
				
				Usuario	user = new Usuario();
				user.setEmail(etNome.getText().toString());
				user.setSenha(string);
				
				Log.i("senha", "EmailUser = "+ user.getEmail());
				Log.i("senha", "senhaMD5User = "+ user.getSenha());
				
					cod_cliente = userDAO.buscarUsuario(user);
					
					user =null;
					
					if(cod_cliente != 0) {
    					
    					Intent telaProdutos = new Intent(ctx, ActivityVendedores.class);
    	    			startActivity(telaProdutos);
    	    					
    	    			//Toast.makeText(ctx, "Bem vindo " + etNome.getText().toString(),duracao).show();
    					
    				} else {
    					
    					startActivity(new Intent(ctx, ActivityDadosIncorretos.class));
    					etSenha.setText("");
    				}    	
				
			} 

		} catch (Exception erro) {
			//Log.i("resposta", "dentro = entrou no catch"+ erro);
			Toast.makeText(ctx, "Erro ao entrar: " + erro.getMessage(), duracao).show();
		
		} // FIM DO CATCH DO LOGAR.PHP 			
    	
    }
    
    public boolean validaEmail(String email){
    	if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
    
    @Override
    public void onBackPressed() {
    	finish();
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    }
    
    public void loginFacebook(View view){
    	try{
    		List<String> permissions = new ArrayList<String>();
    		permissions.add("email");
    		
    		
    		 Session.openActiveSession(this, true,permissions, new Session.StatusCallback() {
    			
  		      // callback when session changes state
  		      @Override
  		      public void call(Session session, SessionState state, Exception exception) {
  		        if (session.isOpened()) {

  		          // make request to the /me API
  		          Request.newMeRequest(session, new Request.GraphUserCallback() {
  		        	 

  		            // callback after Graph API response with user object
  		            @Override
  		            public void onCompleted(GraphUser user, Response response) {
  		              if (user != null) {

  		            	  
  		            	Log.i("Script", "Name: "+user.getName());
  		                Log.i("Script", "niver: "+user.getBirthday());
  		                Log.i("Script", "Email1: "+user.asMap().get("email"));
  		                
  		                Log.i("Script", "Email: "+ user.getProperty("email"));
  		             
  		                startActivity(new Intent(ctx, ActivityEndereco.class));
  		                
  		                
  		              }
  		            }
  		          }).executeAsync();
  		        }
  		      }
  		    });
      	
    	}catch(Exception e){
    		Log.i("Script", "Erro: "+e);
    	}
    	
    	
    }
    
    
    @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      
	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	  }
    
} // FIM DA ACTIVITY



    

