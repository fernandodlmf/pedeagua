package com.pedeagua.finalizacao.lightbox;


import com.pedeagua.SQLite.BD;
import com.pedeagua.finalizacao.ActivityFecharCompra;
import com.pedeagua.finalizacao.FecharCompraAdapter;
import com.pedeagua.login.PrincipalActivity;
import com.pedeagua.testews.ItemPedido;
import com.pedeagua.testews.Pedido;
import com.pedeagua.testews.PedidoDAO;
import com.pedeagua.testews.ProdutoEmUso;

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

public class ActivityConfirmarCompra extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityConfirmarCompra.this;
	Button btConfirmar_Compra;
	TextView tvFecharActivity, tvQtdGlobalConfirmarConta, tvValorGlobalConfirmarConta, tvTrocoPara;
	int duracao = Toast.LENGTH_SHORT;
	PedidoDAO pedidoDAO = null;
	public static String js;
	boolean resposta;
	public static int id_pedido_retornado =0;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirmar_compra);
       
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  		StrictMode.setThreadPolicy(policy);
  		
        this.setFinishOnTouchOutside(false);

	    tvFecharActivity = (TextView) findViewById(R.id.tvFecharActivity);
	    btConfirmar_Compra = (Button) findViewById(R.id.btConfirmar_Compra);
	    
	    tvQtdGlobalConfirmarConta = (TextView) findViewById(R.id.tvQtdGlobalConfirmarConta);
	    tvValorGlobalConfirmarConta = (TextView) findViewById(R.id.tvValorGlobalConfirmarConta);
	    tvTrocoPara = (TextView) findViewById(R.id.tvTrocoPara);
	    
	    
	    tvQtdGlobalConfirmarConta.setText(ActivityFecharCompra.tvQtdGlobalFecharConta.getText().toString());
	    tvValorGlobalConfirmarConta.setText(ActivityFecharCompra.tvValorGlobalFecharConta.getText().toString());
	    
	    tvTrocoPara.setText(ActivityFecharCompra.etTrocoPara.getText().toString());
	    

	    btConfirmar_Compra.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  	
			  	if(tvQtdGlobalConfirmarConta.getText().toString().equals("0")) {
			  		Toast.makeText(ctx, "A quantidade não pode ser 0.", duracao).show();
			  	}else {
			  		if(inserirPedido()){
						Intent it = new Intent(ctx, ActivityAguardandoChegar.class);
						startActivity(it);
						BD bd = new BD(ctx);
						bd.deleteAll();
						Toast.makeText(ctx, "Pedido realizado!", duracao).show();
						//setResult(RESULT_OK);
					} else{
						Toast.makeText(ctx, "Pedido não realizado!", duracao).show();
					}	
			  	}
			  	
				finish();
			}
		});
	        
	       
    } // FIM DO ONCREATE
    
    public void tvFechar (View view) {
    	finish();
    }
    
    public boolean inserirPedido() {
    	try{
    		String valorTotal = tvValorGlobalConfirmarConta.getText().toString();
    		String TrocoPara = tvTrocoPara.getText().toString();
    		
    		Log.i("Pedido", "TrocoPara = "+TrocoPara);
    		valorTotal = valorTotal.replaceAll("[R$]", "");
    		TrocoPara = TrocoPara.replaceAll("[,]", ".");
    		
    		if(TrocoPara.equals("")){
    			TrocoPara = "0";
    		}
    		Log.i("Pedido", "valorTotal depois = "+valorTotal);
    		
    		Pedido pedido = new Pedido();
	       	 	pedido.setCod_cliente(PrincipalActivity.cod_cliente);
	       	 	pedido.setCod_vendedor(2);
	       	 	pedido.setValorTotal(valorTotal);
	       	 	pedido.setTrocoPara(TrocoPara);
	       	 	
	       	
	        pedidoDAO = new PedidoDAO();
	        id_pedido_retornado = pedidoDAO.inserirPedido(pedido);
	       	
	       	if(id_pedido_retornado != 0){
	       		try{
		       		 ItemPedido itemPedido = null;
			           
			            for (ProdutoEmUso pem : FecharCompraAdapter.listaPEmUso) {
				       		itemPedido = new ItemPedido();
				       		resposta = false;
				       		
				       		itemPedido.setCod_pedido(id_pedido_retornado);
				       		itemPedido.setCod_produto(pem.getId());
				       		itemPedido.setQtd(Integer.parseInt(pem.getQtdIndividual()));     
			           		
				           	resposta = pedidoDAO.inserirItemPedido(itemPedido);
				           	
				           	itemPedido = null;
			           	
				           	//Log.i("Pedido", "resposta = "+resposta);
						}
			            
			            pedido = null;	
	       			
	       		}catch(Exception e){}
		       	
	       	} else {
	       		return false;
	       	}

       }catch(Exception e){
       		Log.i("Pedido", "erro ao obter id do pedido = "+e);
       		return false;
       }
    	
    	return resposta;
    }
					
					
} // FIM DA ACTIVITY CADASTRO
