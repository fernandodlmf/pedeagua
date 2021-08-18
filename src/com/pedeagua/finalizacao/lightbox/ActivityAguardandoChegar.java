package com.pedeagua.finalizacao.lightbox;

import com.pedeagua.finalizacao.ActivityFecharCompra;
import com.pedeagua.login.PrincipalActivity;
import com.pedeagua.testews.Pedido;
import com.pedeagua.testews.PedidoDAO;
import com.pedeagua.vendedores.ActivityVendedores;
import br.com.projetofinal1.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAguardandoChegar extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityAguardandoChegar.this;
	Button bt_Entregue;
	TextView tvFecharActivity, tvValorTotalAguardandoChegar, tvQtdGlobalAguardandoChegar, tvTrocoAguardandoChegar;
	int duracao = Toast.LENGTH_SHORT;
	
	Chronometer cronometro;
	long mili, miliDeReferencia;
	PedidoDAO pedidoDAO = null;
	Pedido p =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_aguardando_chegar);
        
    
        if(pedidoDAO == null){
            pedidoDAO = new PedidoDAO();
        }

        
        this.setFinishOnTouchOutside(false);
        
        //miliDeReferencia = 1800000;
        mili = 0;
        try{
        	 cronometro = (Chronometer)findViewById(R.id.chronometer);
        	 
        	 cronometro.setOnChronometerTickListener(new OnChronometerTickListener(){
        	        @Override
        	            public void onChronometerTick(Chronometer cArg) {
        	            long time = SystemClock.elapsedRealtime() - cArg.getBase();
        	            int h   = (int)(time /3600000);
        	            int m = (int)(time - h*3600000)/60000;
        	            int s= (int)(time - h*3600000- m*60000)/1000 ;
        	            String hh = h < 10 ? "0"+h: h+"";
        	            String mm = m < 10 ? "0"+m: m+"";
        	            String ss = s < 10 ? "0"+s: s+"";
        	            cArg.setText(hh+":"+mm+":"+ss);
        	        }
        	    });
        	 cronometro.setBase(SystemClock.elapsedRealtime());
        	 cronometro.start();
             
        }catch(Exception e){
        	Log.i("cronometro", "Erro ao iniciar cronometro.");
        }
       
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.llCronometro);
        

        miliDeReferencia = 30000;
       
        rl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 mili = SystemClock.elapsedRealtime() - cronometro.getBase();

		        if(mili >= miliDeReferencia){
				  	
		        	caixaDialogo("CANCELAR PEDIDO", "Deseja realmente cancelar?");
		        	
		        }else {
		        	Toast.makeText(ctx, "Você só pode cancelar após 30 minutos.", Toast.LENGTH_LONG).show();
		        }	
			}
		});
        
        
        
	    tvFecharActivity = (TextView) findViewById(R.id.tvFecharActivity);
	    tvValorTotalAguardandoChegar = (TextView) findViewById(R.id.tvValorTotalAguardandoChegar);
	    tvQtdGlobalAguardandoChegar = (TextView) findViewById(R.id.tvQtdGlobalAguardandoChegar);
	    tvTrocoAguardandoChegar = (TextView) findViewById(R.id.tvTrocoAguardandoChegar);
	    bt_Entregue = (Button) findViewById(R.id.bt_Entregue);
	    
	    tvValorTotalAguardandoChegar.setText(ActivityFecharCompra.tvValorGlobalFecharConta.getText().toString());
	    tvQtdGlobalAguardandoChegar.setText(ActivityFecharCompra.tvQtdGlobalFecharConta.getText().toString());
	    tvTrocoAguardandoChegar.setText(ActivityFecharCompra.etTrocoPara.getText().toString());
	    
	    
	    bt_Entregue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(ctx, "Senha enviada com sucesso.", duracao).show();
				p = null;
				p = new Pedido();
					p.setId(ActivityConfirmarCompra.id_pedido_retornado);
					p.setCod_cliente(PrincipalActivity.cod_cliente);
				
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			  	StrictMode.setThreadPolicy(policy);
				
				pedidoDAO.setarEntregue(p);
				
				startActivity(new Intent(ctx, ActivityQualificacao.class));
				
				finish();
			}
		});
	        
	       
    } // FIM DO ONCREATE
    
    public void iniciarCronometro(){
  	  cronometro.setBase(SystemClock.elapsedRealtime());
  	  cronometro.start();
    }
      
      public void pararCronometro(){
      	mili = cronometro.getBase();
      	Log.i("cronometro", "mili= "+mili);
      	Log.i("cronometro", "elapsedRealtime= "+SystemClock.elapsedRealtime());
    	  	cronometro.stop();
      }
    
    
    @Override
    public void onBackPressed(){
    	onPause();
    	
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    }
    
    
    public void caixaDialogo(String nome, String texto){
    	AlertDialog.Builder a = new AlertDialog.Builder(ctx);
    	a.setTitle(nome);
    	a.setMessage(texto);
    	a.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				p = new Pedido();
					p.setId(ActivityConfirmarCompra.id_pedido_retornado);
					p.setCod_cliente(PrincipalActivity.cod_cliente);
					p.setTempoCancelamento(mili);
					
					//pararCronometro();
					
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			  	StrictMode.setThreadPolicy(policy);
				
				boolean cancelou = pedidoDAO.cancelarPedido(p);
				
				if(cancelou == true){
					Toast.makeText(ctx, "Pedido Cancelado!", duracao).show();
					startActivity(new Intent(ctx, ActivityVendedores.class));;
					finish();
				}else {
					Toast.makeText(ctx, "Tente novamente.", duracao).show();
				}
				
			}
		});
    	
    	a.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
    	
    	a.show();
    }

					
} // FIM DA ACTIVITY CADASTRO
