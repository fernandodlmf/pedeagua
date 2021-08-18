package com.pedeagua.finalizacao;

import java.util.ArrayList;

import br.com.projetofinal1.R;

import com.android.volley.toolbox.ImageLoader;
import com.pedeagua.SQLite.BD;
import com.pedeagua.testews.ProdutoEmUso;
import com.pedeagua.vendedores.ActivityVendedores;
import com.pedeagua.vendedores.CircularNetworkImageView;
import com.pedeagua.vendedores.app.AppController;
import com.pedeagua.vendedores.model.Vendedor;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class MainActivity extends ActivityGroup{
	
	Context ctx = MainActivity.this;
	int duracao = Toast.LENGTH_SHORT;
	public static int qtdGlobalMain;
	public static double 	precoGlobalMain;
	public static ArrayList<ProdutoEmUso> auxListaFecharConta;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	BD bd;
		
	// tabHost;
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        
        
        try{
       	   if (imageLoader == null)
                  imageLoader = AppController.getInstance().getImageLoader();

              
              CircularNetworkImageView thumbNail = (CircularNetworkImageView)findViewById(R.id.thumbnail_ven);
              TextView title = (TextView)findViewById(R.id.title_ven);
              TextView rating = (TextView)findViewById(R.id.rating_ven);
             
       
              //getting movie data for the row
              Vendedor m =  ActivityVendedores.movieList.get(ActivityVendedores.posicao3);
       
              //thumbnail image
             thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
               
             //title
             title.setText(m.getTitle());
               
             //rating
             rating.setText(m.getRating());
               
          }catch(Exception e){
       	   Log.i("mainActivity", "Erro ao obter vendedor: ");
          }
      

        qtdGlobalMain=0;
        precoGlobalMain=0;
        
        if(bd == null){
        	bd = new BD(ctx);
        }
        
       ActivityFecharCompra.setNull();
        
        // MUDANDO COR DA ACTION BAR
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#66ccff")));
        
        
       final TabHost  tabHost = (TabHost) findViewById(R.id.tabHost);
        	 tabHost.setup(getLocalActivityManager());
        	 
        	 tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                 public void onTabChanged(String tabId) {
                     setTabColor(tabHost);
                 }
             });
       
        Intent intent = new Intent(ctx, ActivityFinalizacaoAgua.class);
        //Intent intent = new Intent(ctx, ActivityAgua.class);	 
        TabSpec  spec = tabHost.newTabSpec("AGUA");
        		 spec.setIndicator("AGUA");
        		 spec.setContent(intent);
 
        
        
        Intent intent1 = new Intent(ctx, ActivityFinalizacaogGas.class);
            TabSpec  spec1 = tabHost.newTabSpec("GAS");
            	spec1.setIndicator("GAS");
            	spec1.setContent(intent1);
            	
            

        tabHost.addTab(spec);
        tabHost.addTab(spec1);
        
        
     
        

    }
    
@SuppressWarnings("unchecked")
public void minhaConta (View view) {
		
		//Toast.makeText(ctx, "NÃO IMPLEMENTADO", duracao).show();
	try{
		
			Intent intent = new Intent(ctx, ActivityFecharCompra.class);
		startActivity(intent);
	} catch(Exception e){
		Toast.makeText(ctx, "Erro ao abrir minha conta ->" +e , duracao).show();
	}
	
	
	
		
		
	}

    
	private void setTabColor(final TabHost tabHost){
		
		
		for(int i=0; i< tabHost.getTabWidget().getChildCount(); i++){
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
			
	    }
	        
		if(tabHost.getCurrentTab() == 0){
	    	tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.bg_tab);
	    } else if (tabHost.getCurrentTab() == 1){
	    	tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.bg_tab_gas);
	    }
		 
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onResume(){
		super.onResume();
		qtdGlobalMain = 0;
		precoGlobalMain = 0;
		
		ActivityFecharCompra.setNull();
		
		// ATUALIZANDO QUANTIDADE GLOBAL
		
		TextView tv = (TextView)findViewById(R.id.tvQtdGlobal);
		TextView tv1 = (TextView)findViewById(R.id.tvPrecoGlobal);
		
		
		String qtdGlobalTv = tv.getText().toString();
		String precoGlobalTv = tv1.getText().toString();
		
		try{
			qtdGlobalMain = ActivityFinalizacaoAgua.qtdGlobal + ActivityFinalizacaogGas.qtdGlobalGAS;
			precoGlobalMain = ActivityFinalizacaoAgua.precoGlobal + ActivityFinalizacaogGas.PrecoGlobalGAS;
		
			if (qtdGlobalTv !=  String.valueOf(qtdGlobalMain)) {
				
				tv.setText(String.valueOf(qtdGlobalMain));
			}
			
			Log.i("tag", "precoGlobal = " +String.valueOf(precoGlobalMain));
			Log.i("tag", "precoGlobalTv = " +precoGlobalTv);
			
			if (precoGlobalTv.equals(String.valueOf(precoGlobalMain))) {
				return;
				
			} else {
				tv1.setText("R$ "+String.valueOf(precoGlobalMain) +"0");
			}
			
		}catch(Exception e) {
			
		}
		
		
	}
	
	@Override
	public void onBackPressed() {
		qtdGlobalMain = 0;
		bd.deleteAll();
		finish();
	}
	

}
