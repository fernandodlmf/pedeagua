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

public class ActivityProdutoAgua extends Activity {	// INÍCIO DA ACTIVITY CADASTRO
	
	Context ctx = ActivityProdutoAgua.this;
	Button btCadastrar_2, btConfirmarProduto, bt_ArrowMais, bt_ArrowMenos;
	TextView tvFecharCadastro, tv_QtdIndividualProdutoAgua;
	int duracao = Toast.LENGTH_SHORT;
	int qtdProdutoAgua = 1, posicao;
	//public static int qtdProdutoAguaFinal;
	public static List<ProdutoEmUso> listaPFecharConta = null;
	ProdutoEmUso pdEmUso;
	String[] descricaoProdutoAgua;
	String pProdutoAgua, nomeAgua;
	int id;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_produto_agua);
        String descricaoIndaia ="Desde a captação até seu envasamento,"
				        			+ " a Indaiá é totalmente livre do contato humano, o que garante "
				        			+ "uma água mineral direto da natureza para você.";
        String descricaoItacoatiara ="Descricao da iacoatiara";
        String descricaoSublime ="Descricao da Sublime";
        
        descricaoProdutoAgua = new String[] { descricaoIndaia, descricaoItacoatiara, descricaoSublime};
        
        TextView tvDescricaoProdutoAgua = (TextView)findViewById(R.id.tvDescricaoProdutoAgua);
        TextView tvPrecoProdutoAgua = (TextView)findViewById(R.id.tv_PrecoUnitarioAgua);
        TextView tvNomeAgua = (TextView)findViewById(R.id.tv_NomeAgua);
        
        
        
        id = getIntent().getExtras().getInt("id");
        posicao = getIntent().getExtras().getInt("posicao");
        pProdutoAgua = getIntent().getExtras().getString("pAgua");
        nomeAgua = getIntent().getExtras().getString("nomeAgua");
        
        
        
        
        
        
        //Log.i("tag", "pProdutoAgua" + pProdutoAgua);
        
        
        tvDescricaoProdutoAgua.setText(descricaoProdutoAgua[posicao]);
        tvPrecoProdutoAgua.setText(pProdutoAgua);
        tvNomeAgua.setText(nomeAgua);
       

        this.setFinishOnTouchOutside(false); // IMPEDE QUE O DIÁLOGO SEJA FECHADO COM TOQUE FORA DA VIEW
        
        tv_QtdIndividualProdutoAgua = (TextView) findViewById(R.id.tv_QtdIndividualProdutoAgua);
        
        qtdProdutoAgua = Integer.parseInt(tv_QtdIndividualProdutoAgua.getText().toString());
        
        bt_ArrowMais = (Button) findViewById(R.id.bt_ArrowMais);
        bt_ArrowMenos = (Button) findViewById(R.id.bt_ArrowMenos);
        
        btConfirmarProduto = (Button)findViewById(R.id.btConfirmarProdutoAgua);


        
        btConfirmarProduto.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						
						//qtdProdutoAguaFinal = qtdProdutoAgua;

						double precoTotalLocal = 0;
						double ValorTotalLocar = 0;
						
						//Log.i("tag", "Tentando converter o double");
						precoTotalLocal = Double.parseDouble(pProdutoAgua);
						//Log.i("tag", "Tentando converter o double");
						
						
						ValorTotalLocar = qtdProdutoAgua * precoTotalLocal;
					
						
						
					  // Log.i("tag", "dbqtd"+dbqtd);
					  // Log.i("tag", "dbqtd1"+dbqtd1);
					   //Log.i("tag", "dbqtotal"+dbqtotal);
						
		
						Intent it = new Intent();
						Bundle bd = new Bundle();
						bd.putInt("int", posicao);
						bd.putString("nome", nomeAgua);
						bd.putInt("qtd", qtdProdutoAgua);
						bd.putString("valor", String.valueOf(ValorTotalLocar));
						
						ProdutoEmUso pd12 = new ProdutoEmUso();
						pd12.setId(id);
						pd12.setNome(nomeAgua);
						pd12.setQtdIndividual(String.valueOf(qtdProdutoAgua));
						pd12.setPreco(String.valueOf(ValorTotalLocar));
						
						BD bd1 = new BD(ctx);
						
						Log.i("BancoD", "Busca por id = "+bd1.bucarPedido(id).getQtdIndividual());
						
						if(bd1.bucarPedido(id).getQtdIndividual() == null) {
							bd1.inserirPedido(pd12);
						} else {	
							bd1.atualizarPedido(pd12);
						}

						
						it.putExtra("a", bd);
						setResult(RESULT_OK,it);
						
						//ActivityFinalizacaoAgua.qtdGlobal += qtdProdutoAgua;
						//MainActivity.qtdGlobalFinal = qtdProdutoAgua;
						
						qtdProdutoAgua = 1;
	
						
						
						
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
				qtdProdutoAgua += 1;
				
				
				
				tv_QtdIndividualProdutoAgua.setText(String.valueOf(qtdProdutoAgua));
				
			}
		});
        
        bt_ArrowMenos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(qtdProdutoAgua != 1){
					
					qtdProdutoAgua -= 1;
					
					tv_QtdIndividualProdutoAgua.setText(String.valueOf(qtdProdutoAgua));
				
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
