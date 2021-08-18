package com.pedeagua.SQLite;

public class Produto {
	private int ID_PRODUTO;
	private String NOME;
	private int ID_FOTO;
	private String DESCRICAO;
	private String PRECO;
	
	
	

	public String getNOME() {
		return NOME;
	}


	public void setNOME(String nOME) {
		NOME = nOME;
	}


	public int getID_PRODUTO() {
		return ID_PRODUTO;
	}


	public void setID_PRODUTO(int iD_PRODUTO) {
		ID_PRODUTO = iD_PRODUTO;
	}


	public int getID_FOTO() {
		return ID_FOTO;
	}


	public void setID_FOTO(int iD_FOTO) {
		ID_FOTO = iD_FOTO;
	}


	public String getDESCRICAO() {
		return DESCRICAO;
	}


	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}


	public String getPRECO() {
		return PRECO;
	}


	public void setPRECO(String pRECO) {
		PRECO = pRECO;
	}
	
	
	

}
