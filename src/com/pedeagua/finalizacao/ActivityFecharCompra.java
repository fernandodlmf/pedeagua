package com.pedeagua.finalizacao;



import java.util.ArrayList;
import java.util.List;
import java.security.*;

import com.pedeagua.SQLite.BD;
import com.pedeagua.finalizacao.lightbox.ActivityConfirmarCompra;
import com.pedeagua.mascaras.MonetaryMask;
import com.pedeagua.testews.ProdutoEmUso;

import br.com.projetofinal1.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InlinedApi") public class ActivityFecharCompra extends Activity {
	
	Button bt_FecharConta, bt_MinhaConta_2;
	public static EditText etTrocoPara;
	public static TextView tvValorGlobalFecharConta, tvQtdGlobalFecharConta;
	int duracao = Toast.LENGTH_SHORT;
	Context ctx = ActivityFecharCompra.this;
	public static List<ProdutoEmUso> listaPFecharConta;
	List<ProdutoEmUso> listaPFecharContaTeste;
	FecharCompraAdapter adapter;
	ListView listFecharCompra = null;
	int qtdGlobalFecharConta = 0;
	public static String precoGlobalFecharConta = "0";
	BD bd1;
	//int Soma = 0;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) { // INICIO DO ONCREATE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fechar_compra);
        
        Log.i("cicloDeVida", "chamou o onCreate");
        
 
        // MUDANDO COR DA ACTION BAR
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#66ccff")));
        
    
      
        listFecharCompra = (ListView) findViewById(R.id.lvFecharCompra); 
        
        tvQtdGlobalFecharConta = ((TextView) findViewById(R.id.tvQtdGlobalFecharConta));
        tvValorGlobalFecharConta = (TextView) findViewById(R.id.tvValorGlobalFecharConta);
        etTrocoPara = (EditText) findViewById(R.id.etTrocoPara);
        
        
        etTrocoPara.addTextChangedListener(new MonetaryMask(etTrocoPara));
        
        
        
        if(bd1 == null){
        	bd1 = new BD(ctx);
        }
		
        
        listaPFecharConta = bd1.bucarPedidoAll(); //ActivityFinalizacaoAgua.listaPFecharConta;
        
        //Log.i("cicloDeVida", "listaPFecharConta no onCreate  ="+ActivityFinalizacaoAgua.listaPFecharConta.toString());
        
       
        if(listaPFecharConta == null){
        	listaPFecharConta = new ArrayList<ProdutoEmUso>();
        } 
        
        
        try {
        	 this.adapter = new FecharCompraAdapter(ctx, listaPFecharConta,tvQtdGlobalFecharConta, tvValorGlobalFecharConta  );
             
             listFecharCompra.setAdapter(adapter);
        }catch(Exception e){
        	
        }
        
       
        
        
        qtdGlobalFecharConta = MainActivity.qtdGlobalMain;
        precoGlobalFecharConta = String.valueOf(MainActivity.precoGlobalMain);

        //BOTÕES
		bt_FecharConta = (Button) findViewById(R.id.bt_FecharConta);
		bt_MinhaConta_2 = (Button) findViewById(R.id.bt_MinhaConta_2);
		
		
		tvQtdGlobalFecharConta.setText(String.valueOf(qtdGlobalFecharConta));
		tvValorGlobalFecharConta.setText(String.valueOf("R$ "+precoGlobalFecharConta));
				
		    
		try {
			listners();
		} catch (Exception e) { }
		Log.i("cicloDeVida", "Terminou o onCreate");
    } // FIM DO ONCREATE
 
    public void listners ()  throws NoSuchAlgorithmException {
    	
    	try {
    	
	    	bt_FecharConta.setOnClickListener(new View.OnClickListener() {
		
				@Override
				public void onClick(View arg0) {
					
					try {	
	
						Intent telaProdutos = new Intent(ctx, ActivityConfirmarCompra.class);
		    			startActivity(telaProdutos);

					} catch (Exception erro) {
						Toast.makeText(ctx, "Erro ao entrar: " + erro.getMessage(), duracao).show();
					} 
					
				} 
			});
	    	
		    bt_MinhaConta_2.setOnClickListener(new View.OnClickListener() {// INICIO BTCADASTRAR_1
					
				@Override
				public void onClick(View arg0) {
					//ActivityFinalizacaoAgua.listaPFecharConta = null;
					try{
						//ActivityFinalizacaoAgua.qtdGlobal = FecharCompraAdapter.SomaGeralQtdFecharConta;
						listaPFecharConta.clear();
			    		adapter = new FecharCompraAdapter(ctx, listaPFecharConta,tvQtdGlobalFecharConta, tvValorGlobalFecharConta);
			    	
			    		adapter.notifyDataSetChanged();
			    		listFecharCompra.setAdapter(adapter);
						finish();
					
						
						//onBackPressed();	
					}catch(Exception e) {
						Log.i("FecharCompra", "erro ao minha conta= "+e);			
					}
				
					}
				});
	    

    	} catch(Exception e) {
    		
    	}
	
	}	

    
    @Override
    public void onBackPressed() {
        //aqui dentro vai o codigo que controla o comportamento do botão voltar ao se clicar nele
        //use super.onBackPressed(); para que o aplicativo seja fechado
    	super.onBackPressed();
    	finish();
    }
    
    @Override
    protected void onResume() {
    	Log.i("FecharCompra", "listaPFecharConta no  onResume= "+listaPFecharConta.toString());
    	super.onResume();
    }
    
    @Override
    protected void onStop() {
    	Log.i("cicloDeVida", "chamou o onStop");
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	try{
    		
    		listaPFecharConta = null;
    	Log.i("cicloDeVida", "Limpou lista");
    	}catch(Exception e) {
    		//listaPFecharConta = null;
    		Log.i("cicloDeVida", "entrou no catch");
    	}
    	
    	super.onDestroy();
    	Log.i("cicloDeVida", "executou o super");
    }
    
    
    public static void setNull(){
    	ActivityFecharCompra.listaPFecharConta = null;
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 3) {
	        if(resultCode == RESULT_OK){
	        	finish();

			}
	    }
						
	    if (resultCode == RESULT_CANCELED) {
	        //Write your code if there's no result
	    }
	    
	}//onActivityResult
    
    
  

} // FIM DA ACTIVITY



    

