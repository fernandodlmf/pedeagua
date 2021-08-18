package com.pedeagua.testews;

public class ProdutoEmUso {
	private int id;
	private String nome;
	private String qtdIndividual;
	private String preco;
	
	public ProdutoEmUso() {}

	public ProdutoEmUso(String nome, String qtdIndividual, String preco, int id) {
		this.nome = nome;
		this.qtdIndividual = qtdIndividual;
		this.preco = preco;
		this.id = id;
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

	public String getQtdIndividual() {
		return qtdIndividual;
	}

	public void setQtdIndividual(String qtdIndividual) {
		this.qtdIndividual = qtdIndividual;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "ProdutoEmUso [nome=" + nome + ", qtdIndividual="
				+ qtdIndividual + ", preco=" + preco + "]";
	}
	
	

}
