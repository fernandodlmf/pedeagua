package com.pedeagua.login;




import br.com.projetofinal1.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityTermos extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityTermos.this;
	Button btConfirmar_Termos;
	EditText etEmail, etCriarSenha, etConfirmarSenha;
	RadioButton rBTermos_2;
	int duracao = Toast.LENGTH_SHORT;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_termos);
      
        this.setFinishOnTouchOutside(false);
        //Esconder a Action Bar
       // ActionBar actionBar = getActionBar(); 
       // actionBar.hide();

	    
	    rBTermos_2 = (RadioButton)findViewById(R.id.rBTermos_2);
	    rBTermos_2.setChecked(true);
	    btConfirmar_Termos = (Button) findViewById(R.id.btConfirmar_Termos);
	    
	        
	        
	        
	    btConfirmar_Termos.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {

							finish();

						//Toast.makeText(ctx, "Salvo com sucesso!", duracao).show();
						
					}
				});
			    
    }
		
    public void tvFechar (View view) {
    	finish();
    }
					
} // FIM DA ACTIVITY CADASTRO
