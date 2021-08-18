package com.pedeagua.finalizacao.lightbox;


import com.android.volley.toolbox.ImageLoader;
import com.pedeagua.login.PrincipalActivity;
import com.pedeagua.testews.Usuario;
import com.pedeagua.testews.UsuarioDAO;
import com.pedeagua.vendedores.ActivityVendedores;
import com.pedeagua.vendedores.CircularNetworkImageView;
import com.pedeagua.vendedores.app.AppController;
import com.pedeagua.vendedores.model.Vendedor;

import br.com.projetofinal1.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityQualificacao extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityQualificacao.this;
	Button btConfirmar_Qualificacao;
	TextView tvFecharActivity;
	int duracao = Toast.LENGTH_SHORT;
	UsuarioDAO userDAO = null;
	Usuario user = null;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_qualificacao);
        
        this.setFinishOnTouchOutside(false);
        
        
        try{
        	   if (imageLoader == null)
                   imageLoader = AppController.getInstance().getImageLoader();

               
               CircularNetworkImageView thumbNail = (CircularNetworkImageView)findViewById(R.id.thumbnail_ven_qua);
               TextView title = (TextView)findViewById(R.id.title_ven_qua);
               TextView rating = (TextView)findViewById(R.id.rating_ven_qua);
              
        
               //Pegando dados do vendedor através do List<Vendedor>
               Vendedor m =  ActivityVendedores.movieList.get(ActivityVendedores.posicao3);
        
               //thumbnail image
              thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
                
              //title
              title.setText(m.getTitle());
                
              //rating
              rating.setText(m.getRating());
                
           }catch(Exception e){
        	   Log.i("ActivityQualificação", "Erro ao obter vendedor: ");
           }
       
        
        
        
        if(userDAO == null){
        	userDAO = new UsuarioDAO();
        }
        
       
	    tvFecharActivity = (TextView) findViewById(R.id.tvFecharActivity);
	    btConfirmar_Qualificacao = (Button) findViewById(R.id.btConfirmar_Qualificacao);
	    
	    btConfirmar_Qualificacao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				
				user = new Usuario();
					user.setId(5);
					user.setRating(5.0);
				
				userDAO.atualizarQualificacao(user);
				
				user = null;
				
				//LIMPAR PILHA DE ACTIVITYS
				Intent intent  = new Intent(ctx, PrincipalActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}
		});
	        
	       
    } // FIM DO ONCREATE
    
    public void tvFechar (View view) {
    	//finish();
    	
    	Intent intent  = new Intent(ctx, PrincipalActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
    }
					
					
} // FIM DA ACTIVITY CADASTRO
