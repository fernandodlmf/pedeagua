package com.pedeagua.finalizacao;

import java.util.List;
import br.com.projetofinal1.R;
import com.pedeagua.testews.ProdutoEmUso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProdutoAguaAdapter extends BaseAdapter {
	//private ArrayList<String> lista;
	List<ProdutoEmUso> listaPEmUso;
	private Context context;
    private LayoutInflater inflaterAgua;
    List<ArrayAdapter<String>> listaQuantidadeAgua;
	
	
	public ProdutoAguaAdapter(Context context, List<ProdutoEmUso> listaPEmUso){
		this.context = context;
		this.listaPEmUso = listaPEmUso;
		
	}
	
	public void produtoAguaQuantidade(Context context, List<ArrayAdapter<String>> listaQuantidadeAgua){
		this.context = context;
		this.listaQuantidadeAgua = listaQuantidadeAgua;
		
	}
	

	public List<ArrayAdapter<String>> getListaQuantidadeAgua() {
		return listaQuantidadeAgua;
	}

	public void setListaQuantidadeAgua(List<ArrayAdapter<String>> listaQuantidadeAgua) {
		this.listaQuantidadeAgua = listaQuantidadeAgua;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ProdutoEmUso PD = listaPEmUso.get(position);
		
		 if (inflaterAgua == null)
	            inflaterAgua = (LayoutInflater) context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        if (convertView == null)
	            convertView = inflaterAgua.inflate(R.layout.list_produto_agua_linha, null);
	        

	        
	        TextView tv = (TextView)convertView.findViewById(R.id.tvProdutoAgua);
	       
	   		
	   		
	     	
	   		
	   		tv.setText(PD.getNome());
	        
	     	if (PD.getQtdIndividual() == null) {
	     		 TextView tv1 = (TextView)convertView.findViewById(R.id.tvQtdIndividualAgua);
				tv1.getText().toString();
	     	} else {   		
	     		TextView tv1 = (TextView)convertView.findViewById(R.id.tvQtdIndividualAgua);
				tv1.setText(PD.getQtdIndividual());		
	     	}
	     	
	     	
	     	if (PD.getPreco() == null) {
	     		TextView tv2 = (TextView)convertView.findViewById(R.id.tvTotalIndividual);	
				tv2.getText().toString();
	     	} else {
	     		
	     		TextView tv2 = (TextView)convertView.findViewById(R.id.tvTotalIndividual);	
				tv2.setText("R$ "+PD.getPreco());	
	     	}

	        return convertView;
	}

}
