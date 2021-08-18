package com.pedeagua.finalizacao;

import java.util.ArrayList;
import java.util.List;



import br.com.projetofinal1.R;

import com.pedeagua.SQLite.BD;
import com.pedeagua.finalizacao.lightbox.ActivityProdutoAgua;
import com.pedeagua.testews.Produto;
import com.pedeagua.testews.ProdutoDAO;
import com.pedeagua.testews.ProdutoEmUso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityFinalizacaoAgua extends Activity {
		Context ctx = ActivityFinalizacaoAgua.this;
		int duracao = Toast.LENGTH_SHORT;
	
	//PRODUTOS
		ProdutoEmUso pEmUso;
		ProdutoDAO pDAO = null;
	
	//LISTAS
		public static ArrayList<Produto> listaPrincipal;
		public static ArrayList<Produto> listaGASPrincipal;
		public static ArrayList<Produto> listaAGUAPrincipal;
		List<ProdutoEmUso> listaPEmUsoAgua;
		public static List<ProdutoEmUso> listaPEmUsoGas;
		//public static List<ProdutoEmUso> listaPEmUsoAuxiliar;
		ListView listProdutosAgua = null;
	
		public static ArrayList<ProdutoEmUso> listaPFecharConta = null;
	//OUTROS
		public static int posicaoProdutoAgua ;	
	
	//ADAPTER
		ProdutoAguaAdapter adapter;
		
	//QUANTIDADES GLOBAIS
		public static int qtdGlobal = 0;
		public static double precoGlobal = 0.00;
		public static int vTotalGlobal = 0;
		BD bd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_finalizacao_agua);
		
		// MUDANDO COR DA ACTION BAR
        //getActionBar().setBackgroundDrawable(
                //new ColorDrawable(Color.parseColor("#66ccff")));
		qtdGlobal = 0;
		precoGlobal = 0.00;
		
		try{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	  		StrictMode.setThreadPolicy(policy);
	  		
	  		
	  		if(bd == null){
	        	bd = new BD(ctx);
	        }
	  		
	  		listProdutosAgua = (ListView) findViewById(R.id.listProdutosAgua); 
			
	  		
	  			pDAO = new ProdutoDAO();	
	  			listaPrincipal = pDAO.bucarProdutoAll();
	  			
	  			listaGASPrincipal = new ArrayList<Produto>();
	  			listaAGUAPrincipal = new ArrayList<Produto>();
	  			
	  			//FOR LISTA PRINCIPAL GÁS
	  			for (Produto pGas : listaPrincipal) {
	  				if(pGas.getEh_agua() == 0 && pGas.getEh_gas()== 1){
						Produto pGAS = new Produto();
						pGAS.setId(pGas.getId());
						pGAS.setNome(pGas.getNome());
						pGAS.setPreco(pGas.getPreco());
						pGAS.setEh_gas(pGas.getEh_gas());
						pGAS.setId(pGas.getId());
						
						listaGASPrincipal.add(pGAS);
	  				}
					
				}
	  			
	  			//FOR LISTA PRINCIPAL ÁGUA
	  			for (Produto pAgua : listaPrincipal) {
	  				if(pAgua.getEh_agua() == 1 && pAgua.getEh_gas()== 0){
						Produto pAGUA = new Produto();
						pAGUA.setId(pAgua.getId());
						pAGUA.setNome(pAgua.getNome());
						pAGUA.setPreco(pAgua.getPreco());
						pAGUA.setEh_gas(pAgua.getEh_gas());
						pAGUA.setId(pAgua.getId());
						
						listaAGUAPrincipal.add(pAGUA);
	  				}
					
				}
	  			
	  		//Log.i("listaAguaPrincipal", ""+listaAguaPrincipal.toString());
	  			
	  		
	  		listaPEmUsoAgua = new ArrayList<ProdutoEmUso>();
			listaPEmUsoGas = new ArrayList<ProdutoEmUso>();

			
			
			//FOR DA LISTA ÁGUA
			
			for (Produto p : listaPrincipal) {
				pEmUso = new ProdutoEmUso();
				if(p.getEh_agua() == 1 && p.getEh_gas()== 0){
					pEmUso.setId(p.getId());
					pEmUso.setNome(p.getNome());
					pEmUso.setQtdIndividual(String.valueOf(0));
					pEmUso.setPreco("00,00");
					listaPEmUsoAgua.add(pEmUso);
				}
			}
			
			pEmUso = null;
			//FOR DA LISTA GAS
			for (Produto p : listaPrincipal) {
				pEmUso = new ProdutoEmUso();
				
				if(p.getEh_gas() == 1 && p.getEh_agua() == 0){
					pEmUso.setId(p.getId());
					pEmUso.setNome(p.getNome());
					pEmUso.setQtdIndividual(String.valueOf(0));
					pEmUso.setPreco("00,00");

					listaPEmUsoGas.add(pEmUso);
				}
					
			}
			
			//Log.i("listaEmUso", ""+listaPEmUso.toString());
			
			
			this.adapter = new ProdutoAguaAdapter(ctx, listaPEmUsoAgua);
			
			listProdutosAgua.setAdapter(adapter);
			listProdutosAgua.setOnItemClickListener (eventoClick()); 
		}catch(Exception e){
			Log.i("NOVO", ""+e);
		}
		
	}
	
	
//MÉTODO QUE RETORNA O RESULTADO DO CLICK EM UM ITEM DE UM ListView
    
    public OnItemClickListener eventoClick () { // INÍCIO DO MÉTODO
    	return (new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				
				posicaoProdutoAgua = position;
				
				
				try {	// INÍCIO DO TRATAMENTO DE RROS
				
				
					//startActivity(new Intent(ctx, ActivityProdutoAgua.class));
					
					Intent it = new Intent(ctx, ActivityProdutoAgua.class);
					it.putExtra("id", listaAGUAPrincipal.get(posicaoProdutoAgua).getId());
					it.putExtra("posicao", posicaoProdutoAgua);
					it.putExtra("pAgua", listaAGUAPrincipal.get(posicaoProdutoAgua).getPreco());
					//Log.i("preco", ""+listaAguaPrincipal.get(posicaoProdutoAgua).getPreco());
					it.putExtra("nomeAgua", listaAGUAPrincipal.get(posicaoProdutoAgua).getNome());
					startActivityForResult(it, 1);
									
				
				} catch (Exception erro){
					
					Toast.makeText(ctx, "Erro ao chamar produto: " + erro.getMessage() , duracao).show();
				}	// FIM DO TRATAMENTO DE RROS
					
			}});
    	
    }	// FIM DO MÉTODO
	
		
	
	

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	        	
	        	//EXTRAINDO DADOS DO PACOTE RECEBIDO.
	        	
				Bundle bd = data.getBundleExtra("a"); 
				    
				int p = bd.getInt("int");
				String n = bd.getString("nome");
				int q = bd.getInt("qtd");
				String v = bd.getString("valor");	
				
				ProdutoEmUso np = new ProdutoEmUso();
				np.setQtdIndividual(String.valueOf(q));
				np.setNome(n);
				np.setPreco(v);
				
				listaPEmUsoAgua.set(p, np);;
				
				
				
				adapter.notifyDataSetChanged();
		
				
				
				qtdGlobal = 0; 
				precoGlobal = 0.00;
				
				int SomaQtds = 0;
				double SomaValoresIndividuais = 0;
				
				try{
					for(int i = 0; i<listaPEmUsoAgua.size(); i++) {
						if(listaPEmUsoAgua.get(i).getPreco() == "00,00" || listaPEmUsoAgua.get(i).getQtdIndividual() == "0" ) {
							
							listaPEmUsoAgua.get(i).setPreco(String.valueOf(0.00));
							listaPEmUsoAgua.get(i).setQtdIndividual(String.valueOf(0));
							
							SomaQtds += Integer.parseInt(listaPEmUsoAgua.get(i).getQtdIndividual());
							SomaValoresIndividuais += Double.parseDouble(listaPEmUsoAgua.get(i).getPreco());
							
						} else {
							SomaQtds += Integer.parseInt(listaPEmUsoAgua.get(i).getQtdIndividual());
							SomaValoresIndividuais += Double.parseDouble(listaPEmUsoAgua.get(i).getPreco());
						}
					}
					
					qtdGlobal = SomaQtds;
					precoGlobal = SomaValoresIndividuais;
					
				} catch(Exception e){
					Log.i("TAG", "erro for = "+e);
				}
	        }
	        
		    if (resultCode == RESULT_CANCELED) {
		        //Write your code if there's no result
		    }
	    }
	}//onActivityResult
	
	@Override
	public void onBackPressed(){
		ActivityFinalizacaogGas.qtdGlobalGAS = 0; //SETANDO QTDGLOBALGAS = 0 AO VOLTAR DENTRO DA FINALIZAÇÃO AGUA
		ActivityFinalizacaogGas.PrecoGlobalGAS = 0.00;
		bd.deleteAll();
		finish();
	}
	
	@Override
	protected void onResume(){
		try{
			Log.i("onResume", "Entrou no onResume");
			Log.i("onResume", "listaPEmUso=" + listaPFecharConta.toString());
		} catch(Exception e){}
		
		adapter.notifyDataSetChanged();
		super.onResume();
	}

}
