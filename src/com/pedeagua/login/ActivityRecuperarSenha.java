package com.pedeagua.login;


import java.io.File;
import br.com.projetofinal1.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityRecuperarSenha extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	protected static final String ANDROID_SUPPORT_EMAIL = null;
	Context ctx = ActivityRecuperarSenha.this;
	Button btConfirmar_Termos;
	EditText etEmailR;
	TextView tvFecharRecuperacao;
	RadioButton rBTermos_1;
	int duracao = Toast.LENGTH_SHORT;
	protected File crashLogFile;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recuperar_senha);
        
        this.setFinishOnTouchOutside(false);

      
       
	    etEmailR = (EditText) findViewById(R.id.etEmailR);
	    tvFecharRecuperacao = (TextView) findViewById(R.id.tvFecharRecuperacao);
	    btConfirmar_Termos = (Button) findViewById(R.id.btConfirmar_Termos);
	    
	    btConfirmar_Termos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					//Toast.makeText(ctx, "Senha enviada com sucesso.", duracao).show();
				sendEmail();
				finish();
			
				
			}
		});
	        
	       
    } // FIM DO ONCREATE
    
    public void tvFechar (View view) {
    	finish();
    }

    private void sendEmail(){

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + "fernandodlmf@gmail.com")); 
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My email's subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "My email's body");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ctx, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }
					
					
} // FIM DA ACTIVITY CADASTRO
