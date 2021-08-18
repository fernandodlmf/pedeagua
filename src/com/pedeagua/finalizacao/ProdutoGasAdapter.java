package com.pedeagua.finalizacao;


import java.util.List;
import br.com.projetofinal1.R;
import com.pedeagua.testews.ProdutoEmUso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProdutoGasAdapter extends BaseAdapter {
	//private ArrayList<String> lista;
	List<ProdutoEmUso> listaPEmUsoGas;
	private Context context;
    private LayoutInflater inflaterGas;
	
	
	public ProdutoGasAdapter(Context context, List<ProdutoEmUso> listaPEmUsoGas){
		this.context = context;
		this.listaPEmUsoGas = listaPEmUsoGas;
		
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaPEmUsoGas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaPEmUsoGas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ProdutoEmUso PD = listaPEmUsoGas.get(position);
		 if (inflaterGas == null)
	            inflaterGas = (LayoutInflater) context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        if (convertView == null)
	            convertView = inflaterGas.inflate(R.layout.list_produto_gas_linha, null);
	        

	       // View rootView = convertView;
	        TextView tv = (TextView)convertView.findViewById(R.id.tvProdutoGas);
	       

	   		tv.setText(PD.getNome());
	        
	     	if (PD.getQtdIndividual() == null) {
	     		 TextView tv1 = (TextView)convertView.findViewById(R.id.tvQtdIndividualGas);
				tv1.getText().toString();
	     	} else {   		
	     		TextView tv1 = (TextView)convertView.findViewById(R.id.tvQtdIndividualGas);
				tv1.setText(PD.getQtdIndividual());		
	     	}
	     	
	     	
	     	if (PD.getPreco() == null) {
	     		TextView tv2 = (TextView)convertView.findViewById(R.id.tvTotalIndividualGas);	
				tv2.getText().toString();
	     	} else {
	     		
	     		TextView tv2 = (TextView)convertView.findViewById(R.id.tvTotalIndividualGas);	
				tv2.setText("R$ "+PD.getPreco());	
	     	}

	       
	        
	 
	        return convertView;
	}

}
