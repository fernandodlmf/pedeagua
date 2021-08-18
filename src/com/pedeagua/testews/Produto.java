package com.pedeagua.testews;

public class Produto {
	private int id;
	private String nome;
	private String preco;
	private int eh_agua;
	private int eh_gas;
	
	public Produto() {}

	public Produto(int id, String nome, String preco, int eh_agua, int eh_gas) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.eh_agua = eh_agua;
		this.eh_gas = eh_gas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public int getEh_agua() {
		return eh_agua;
	}

	public void setEh_agua(int eh_agua) {
		this.eh_agua = eh_agua;
	}

	public int getEh_gas() {
		return eh_gas;
	}

	public void setEh_gas(int eh_gas) {
		this.eh_gas = eh_gas;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco
				+ ", eh_agua=" + eh_agua + ", eh_gas=" + eh_gas + "]";
	}
	
	
	
	
	

}

