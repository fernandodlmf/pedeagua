package com.pedeagua.finalizacao;

import java.util.ArrayList;

import java.util.List;

import br.com.projetofinal1.R;

import com.pedeagua.SQLite.BD;
import com.pedeagua.finalizacao.lightbox.ActivityProdutoGas;
import com.pedeagua.testews.ProdutoEmUso;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityFinalizacaogGas extends Activity {
	Context ctx = ActivityFinalizacaogGas.this;
	int duracao = Toast.LENGTH_LONG;
	public static int posicaoProdutoGas;
	
	List<ProdutoEmUso> listaPEmUsoGas;
	ListView listProdutosGas = null;
	ProdutoGasAdapter adapterGAS;
	public static int qtdGlobalGAS = 0;
	public static double PrecoGlobalGAS = 0.00;
	BD bd;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_finalizacao_gas);
		
		if(bd == null){
        	bd = new BD(ctx);
        }
		
		qtdGlobalGAS = 0;
		PrecoGlobalGAS = 0.00;

		listaPEmUsoGas = ActivityFinalizacaoAgua.listaPEmUsoGas; 
		
		if(listaPEmUsoGas == null){
			listaPEmUsoGas = new ArrayList<ProdutoEmUso>();
		}
		
    	try {
            
            listProdutosGas = (ListView) findViewById(R.id.listProdutosGas);
          
            this.adapterGAS = new ProdutoGasAdapter(ctx, listaPEmUsoGas);
     
            listProdutosGas.setAdapter(adapterGAS);
            listProdutosGas.setOnItemClickListener (eventoClick()); // INSERINDO O MÉTODO DE RETORNO DO CLICK NO MEU ListView listProdutos
 
        }catch(Exception erro) {	
        	Log.i("TAG", "dentroGas = "+erro);  		
  	  
        } // FIM DO TRATAMENTO DE ERROS
        
	}	
	
//MÉTODO QUE RETORNA O RESULTADO DO CLICK EM UM ITEM DE UM ListView
    
    public OnItemClickListener eventoClick() { // INÍCIO DO MÉTODO
    	return (new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				
				try {	// INÍCIO DO TRATAMENTO DE RROS
					posicaoProdutoGas = position;
					
					//startActivity(new Intent(ctx, ActivityProdutoAgua.class));
					
					Intent it = new Intent(ctx, ActivityProdutoGas.class);
					it.putExtra("id", ActivityFinalizacaoAgua.listaGASPrincipal.get(posicaoProdutoGas).getId());
					it.putExtra("posicao", posicaoProdutoGas);
					it.putExtra("pProdutoGAS", ActivityFinalizacaoAgua.listaGASPrincipal.get(posicaoProdutoGas).getPreco());
					//Log.i("preco", ""+listaAguaPrincipal.get(posicaoProdutoAgua).getPreco());
					it.putExtra("nomeGAS", ActivityFinalizacaoAgua.listaGASPrincipal.get(posicaoProdutoGas).getNome());
					startActivityForResult(it, 2);
									
				
				} catch (Exception erro){
					
					Toast.makeText(ctx, "Erro ao chamar produto: " + erro.getMessage() , duracao).show();
				}	// FIM DO TRATAMENTO DE RROS
					
			}});
    	
    }	// FIM DO MÉTODO
    
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 2) {
	        if(resultCode == RESULT_OK){
	        	
	        	//EXTRAINDO DADOS DO PACOTE RECEBIDO.
	        	
				Bundle bdGAS = data.getBundleExtra("a"); 
				    
				int p = bdGAS.getInt("int");
				String n = bdGAS.getString("nome");
				int q = bdGAS.getInt("qtd");
				String v = bdGAS.getString("valor");	
				
				ProdutoEmUso np = new ProdutoEmUso();
				np.setQtdIndividual(String.valueOf(q));
				np.setNome(n);
				np.setPreco(v);
			
				listaPEmUsoGas.set(p, np);;
				
				adapterGAS.notifyDataSetChanged();

				
				
				qtdGlobalGAS = 0; 
				PrecoGlobalGAS = 0.00;
				
				int SomaQtdsGAS = 0;
				double SomaPrecosGAS = 0.00;
				
				try{
					for(int i = 0; i<listaPEmUsoGas.size(); i++) {
						if(listaPEmUsoGas.get(i).getPreco() == "00,00" || listaPEmUsoGas.get(i).getQtdIndividual() == "0" ) {
							
							listaPEmUsoGas.get(i).setPreco(String.valueOf(0.00));
							listaPEmUsoGas.get(i).setQtdIndividual(String.valueOf(0));
							
							SomaQtdsGAS += Integer.parseInt(listaPEmUsoGas.get(i).getQtdIndividual());
							SomaPrecosGAS += Double.parseDouble(listaPEmUsoGas.get(i).getPreco());
							
						} else {
							SomaQtdsGAS += Integer.parseInt(listaPEmUsoGas.get(i).getQtdIndividual());
							SomaPrecosGAS += Double.parseDouble(listaPEmUsoGas.get(i).getPreco());
						}
					}
					
					qtdGlobalGAS = SomaQtdsGAS;
					PrecoGlobalGAS = SomaPrecosGAS;
					
					//ActivityFinalizacaoAgua.qtdGlobal += qtdGlobal;
					//precoGlobal = String.valueOf(SomaPrecos);
					
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
	public void onBackPressed() {
    	qtdGlobalGAS = 0;
    	PrecoGlobalGAS = 0.00;
    	bd.deleteAll();
		finish();
	}
	
	
	
}
