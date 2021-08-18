package com.pedeagua.SQLite;

import java.util.ArrayList;
import java.util.List;

import com.pedeagua.testews.ProdutoEmUso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.AutoCompleteTextView.Validator;

public class BD {
	private SQLiteDatabase db;
	
	
	public BD(Context ctx) {
		DBCore auxDB = new DBCore(ctx);
		db = auxDB.getWritableDatabase();
	}
	
	public void inserirProduto(Produto produto) {
		ContentValues valores = new ContentValues();
		valores.put("id_produto", produto.getID_PRODUTO());
		valores.put("id_foto", produto.getID_FOTO());
		valores.put("nome", produto.getNOME());
		valores.put("descricao", produto.getDESCRICAO());
		valores.put("preco", produto.getPRECO());
		
		db.insert("produto_agua", null, valores);
	}
	
	public void inserirPedido(ProdutoEmUso produto) {
		ContentValues valores = new ContentValues();
		valores.put("id_produto", produto.getId());
		valores.put("qtdIndividual", produto.getQtdIndividual());
		valores.put("nome", produto.getNome());
		valores.put("preco", produto.getPreco());
		
		db.insert("pedido_agua", null, valores);
	}
	
	
	
	
	
	
	
	public void atualizarPedido(ProdutoEmUso produto) {
		ContentValues valores = new ContentValues();
		//valores.put("id_produto", produto.getId());
		valores.put("qtdIndividual", produto.getQtdIndividual());
		valores.put("nome", produto.getNome());
		valores.put("preco", produto.getPreco());
		
		db.update("pedido_agua", valores, "id_produto ="+produto.getId(), null);

	}
	
	
	
	
	
	public Produto bucarProduto(int id) {
		Produto produto = new Produto();
		String[] colunas = {"nome", "descricao", "preco" };
		
		Cursor cursor = db.query("produto_agua", colunas, "id_produto =" + id, null, null, null, null);
			Log.i("", "cursor = " +cursor.getCount());
			
					
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			
			produto.setNOME(cursor.getString(0));
			produto.setDESCRICAO(cursor.getString(1));
			produto.setPRECO(cursor.getString(2));		
		}

		return (produto);	
	}
	
	
	public ProdutoEmUso bucarPedido(int id) {
		ProdutoEmUso produto = new ProdutoEmUso();
		String[] colunas = {"nome", "qtdIndividual", "preco" };
		
		Cursor cursor = db.query("pedido_agua", colunas, "id_produto =" + id, null, null, null, null);
			Log.i("", "cursor = " +cursor.getCount());
			
					
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			
			produto.setNome(cursor.getString(0));
			produto.setQtdIndividual(cursor.getString(1));
			produto.setPreco(cursor.getString(2));		
		}

		return (produto);	
	}
	
	
	
	public List<Produto >bucarProdutoAll() {
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		String[] colunas = {"nome", "descricao", "preco" };
		
		Cursor cursor = db.query("produto_agua", colunas, null, null, null, null, null, null);
			Log.i("", "cursor = " +cursor.getCount());
			
					
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			
			do{
				Produto produto = new Produto();
				
				produto.setNOME(cursor.getString(0));
				produto.setDESCRICAO(cursor.getString(1));
				produto.setPRECO(cursor.getString(2));
				
				listaProdutos.add(produto);
				
			} while(cursor.moveToNext());
			
			
		}

		return (listaProdutos);	
	}
	
	
	public List<ProdutoEmUso>bucarPedidoAll() {
		List<ProdutoEmUso> listaProdutos = new ArrayList<ProdutoEmUso>();
		
		String[] colunas = {"id_produto","nome", "qtdIndividual", "preco" };
		
		Cursor cursor = db.query("pedido_agua", colunas, null, null, null, null, null, null);
			Log.i("", "cursor = " +cursor.getCount());
			
					
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			
			do{
				ProdutoEmUso p10  = new ProdutoEmUso();
				p10.setId(cursor.getInt(0));
				p10.setNome(cursor.getString(1));
				p10.setQtdIndividual(cursor.getString(2));
				p10.setPreco(cursor.getString(3));
				
				listaProdutos.add(p10);
				
			} while(cursor.moveToNext());
			
			
		}

		return (listaProdutos);	
	}
	

	
	public void deleteAll() {
		
		db.delete("pedido_agua", null, null);

	}

}
