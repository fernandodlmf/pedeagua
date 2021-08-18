package com.pedeagua.finalizacao.lightbox;



import java.util.List;
import com.pedeagua.SQLite.BD;
import com.pedeagua.testews.ProdutoEmUso;
import br.com.projetofinal1.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityProdutoGas extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityProdutoGas.this;
	Button btCadastrar_2, btConfirmarProduto, bt_ArrowMais, bt_ArrowMenos;
	TextView tvFecharCadastro, tv_QtdIndividualProdutoGAS;
	int duracao = Toast.LENGTH_SHORT;
	int qtdProdutoGAS = 1, posicao, id;
	public static int qtdProdutoGASFinal;
	public static List<ProdutoEmUso> listaPFecharConta = null;
	ProdutoEmUso pdEmUso;
	String[] descricaoProdutoAgua;
	String pProdutoGAS, nomeGAS;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_produto_gas);
        String descricaoIndaia ="Desde a captação até seu envasamento,"
				        			+ " a Indaiá é totalmente livre do contato humano, o que garante "
				        			+ "uma água mineral direto da natureza para você.";
        String descricaoItacoatiara ="Descricao da iacoatiara";
        String descricaoSublime ="Descricao da Sublime";
        
        descricaoProdutoAgua = new String[] { descricaoIndaia, descricaoItacoatiara, descricaoSublime};
     
        TextView tvDescricaoProdutoGAS = (TextView)findViewById(R.id.tvDescricaoProdutoGAS);
        TextView tvPrecoProdutoGAS = (TextView)findViewById(R.id.tv_PrecoUnitarioGas);
        TextView tvNomeGAS = (TextView)findViewById(R.id.tv_NomeGAS);
        
        
        
        id = getIntent().getExtras().getInt("id");
        posicao = getIntent().getExtras().getInt("posicao");
        pProdutoGAS = getIntent().getExtras().getString("pProdutoGAS");
        nomeGAS = getIntent().getExtras().getString("nomeGAS");
        
        
        
        
        //Log.i("tag", "pProdutoAgua" + pProdutoAgua);
        
        
        tvDescricaoProdutoGAS.setText(descricaoProdutoAgua[posicao]);
        tvPrecoProdutoGAS.setText(pProdutoGAS);
        tvNomeGAS.setText(nomeGAS);
       

        this.setFinishOnTouchOutside(false); // IMPEDE QUE O DIÁLOGO SEJA FECHADO COM TOQUE FORA DA VIEW
        
        tv_QtdIndividualProdutoGAS = (TextView) findViewById(R.id.tv_QtdIndividualProdutoGas);
        
        qtdProdutoGAS = Integer.parseInt(tv_QtdIndividualProdutoGAS.getText().toString());
        
        bt_ArrowMais = (Button) findViewById(R.id.bt_ArrowMais);
        bt_ArrowMenos = (Button) findViewById(R.id.bt_ArrowMenos);
        
        btConfirmarProduto = (Button)findViewById(R.id.btConfirmarProdutoAgua);


        
        btConfirmarProduto.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						
						qtdProdutoGASFinal = qtdProdutoGAS;
						
						double dbqtd = 0;
						double dbqtd1 = 0;
						double dbqtotal = 0;
						
						dbqtd = qtdProdutoGASFinal;
						//Log.i("tag", "Tentando converter o double");
						dbqtd1 = Double.parseDouble(pProdutoGAS);
						//Log.i("tag", "Tentando converter o double");
						
						
						dbqtotal = dbqtd * dbqtd1;
						
						
						
					  // Log.i("tag", "dbqtd"+dbqtd);
					  // Log.i("tag", "dbqtd1"+dbqtd1);
					   //Log.i("tag", "dbqtotal"+dbqtotal);
						
		
						Intent it = new Intent();
						Bundle bd = new Bundle();
						bd.putInt("int", posicao);
						bd.putString("nome", nomeGAS);
						bd.putInt("qtd", qtdProdutoGASFinal);
						bd.putString("valor", String.valueOf(dbqtotal));
						
						
						
						ProdutoEmUso pd12 = new ProdutoEmUso();
						pd12.setId(id);
						pd12.setNome(nomeGAS);
						pd12.setQtdIndividual(String.valueOf(qtdProdutoGASFinal));
						pd12.setPreco(String.valueOf(dbqtotal));
						
						BD bd1 = new BD(ctx);
						
						Log.i("BancoD", "Busca por id = "+bd1.bucarPedido(id).getQtdIndividual());
						
						if(bd1.bucarPedido(id).getQtdIndividual() == null) {
							bd1.inserirPedido(pd12);
						} else {	
							bd1.atualizarPedido(pd12);
						}
							
						it.putExtra("a", bd);
						setResult(RESULT_OK,it);
						
						qtdProdutoGAS = 1;
	
						//ActivityFinalizacaoAgua.qtdGlobal += qtdProdutoGASFinal;
						
						
						finish();	

					} catch (Exception e) {
						Log.i("TAG", "DENTRO = ENTROU NO CATCH" + e);
						Toast.makeText(ctx, "Erro ao adicionar produto " +e.getMessage().toString(), duracao).show();
					}
					
				}
			});
        
        bt_ArrowMais.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				qtdProdutoGAS += 1;
				
				
				
				tv_QtdIndividualProdutoGAS.setText(String.valueOf(qtdProdutoGAS));
				
			}
		});
        
        bt_ArrowMenos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(qtdProdutoGAS != 1){
					
					qtdProdutoGAS -= 1;
					
					tv_QtdIndividualProdutoGAS.setText(String.valueOf(qtdProdutoGAS));
				
				}
			}
		});
              
    }
    
  
    public void tvFechar (View view) {
    	finish();
    }
    			
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    }
					
} // FIM DA ACTIVITY CADASTRO
