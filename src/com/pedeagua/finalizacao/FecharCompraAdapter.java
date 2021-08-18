package com.pedeagua.finalizacao;


import java.util.List;
import br.com.projetofinal1.R;
import com.pedeagua.testews.ProdutoEmUso;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class FecharCompraAdapter extends BaseAdapter {
	private Context ctx;
	

	public static List<ProdutoEmUso> listaPEmUso;
	private LayoutInflater inflater;
	public static int SomaGeralQtdFecharConta =0;
	public static double SomaGeralValorFecharConta = 0.00;
	ProdutoEmUso pr;
	Button btDelProdutofecharCompra;
	View vi;
	
	
	public TextView textView,tvValorGlobalFecharConta, tv, tv1 ,tv2;
	
	public FecharCompraAdapter(Context ctx, List<ProdutoEmUso> pEmUso, TextView TextView,TextView  tvValorGlobalFecharConta) {
		this.ctx = ctx;
		this.listaPEmUso = pEmUso;
		this.textView = TextView;
		this.tvValorGlobalFecharConta = tvValorGlobalFecharConta;
		
	
		
		SomaGeralQtdFecharConta = MainActivity.qtdGlobalMain;
		SomaGeralValorFecharConta = MainActivity.precoGlobalMain;
		this.pr = new ProdutoEmUso();
		
		Log.i("textView", "construtor ="+textView.getText());
	}
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaPEmUso.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaPEmUso.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
		

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ProdutoEmUso PD = listaPEmUso.get(position);
		textView.setText(String.valueOf(SomaGeralQtdFecharConta));	
		tvValorGlobalFecharConta.setText("R$ "+String.valueOf(SomaGeralValorFecharConta));

		notifyDataSetChanged();
		 if (inflater == null)
			 inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     
		 if (convertView == null)
          	 convertView = inflater.inflate(R.layout.list_fechar_compra_linha, null);
	        
	     btDelProdutofecharCompra = (Button)convertView.findViewById(R.id.btDelProdutofecharCompra);
	     
	     this.vi = convertView;
	        
	     this.tv1 = (TextView)convertView.findViewById(R.id.tvQtdIndividualFechar);	
	     this.tv = (TextView)convertView.findViewById(R.id.tvProdutoFechar);
	     this.tv2 = (TextView)convertView.findViewById(R.id.tvTotalIndividualFechar);	
	     
	     	tv.setText(PD.getNome());

	     	if (PD.getQtdIndividual() == null) {
				tv1.getText().toString();
	     	} else {   		
				tv1.setText(PD.getQtdIndividual());		
	     	}
	     	
	     	if (PD.getPreco() == null) {	
				tv2.getText().toString();		
	     	} else {
				tv2.setText("R$ "+PD.getPreco());	
	     	}
	     	
	     	
	     	btDelProdutofecharCompra.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{
						
					listaPEmUso.remove(position);
					
					//pr.setNome(PD.getNome());
					//pr.setPreco("00,00");
					//pr.setQtdIndividual("0"); 
					
					//listaPEmUso.add(position, pr);
					

					notifyDataSetChanged();
											
						
					SomaGeralQtdFecharConta -= Integer.parseInt(PD.getQtdIndividual());
					SomaGeralValorFecharConta -= Double.parseDouble(PD.getPreco());

			
					//Log.i("deleta", "Somou antes = "+SomaGeral);
					textView.setText(String.valueOf(SomaGeralQtdFecharConta));
					tvValorGlobalFecharConta.setText("R$ "+String.valueOf(SomaGeralValorFecharConta));
					//Log.i("deleta", "Somou depois = "+SomaGeral);
				
					} catch(Exception e){
						Log.i("deleta", "deleta = "+e);
					}
	
				}
			});

	     	/*if(listaPEmUso.get(position).getQtdIndividual() =="0"){
	     		vi.setVisibility(View.GONE);
	     		vi = null;
	     	}*/

	     	 return convertView;
	        
	}
	
	


}
