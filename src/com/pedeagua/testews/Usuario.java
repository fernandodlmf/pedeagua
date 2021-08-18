package com.pedeagua.testews;



public class Usuario {
	private int id;
	private String nome;
	private String senha;
	private String email;
	private int cod_endereco;
	private String telefone;
	private int eh_vendedor;
	private int eh_cliente;
	private int ativo;
	private double rating;
	
	
	public Usuario() {}


	public Usuario(int id, String nome, String senha, String email,
			int cod_endereco, String telefone, int eh_vendedor, int eh_cliente,
			int ativo, double rating) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cod_endereco = cod_endereco;
		this.telefone = telefone;
		this.eh_vendedor = eh_vendedor;
		this.eh_cliente = eh_cliente;
		this.ativo = ativo;
		this.rating = rating;
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


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getCod_endereco() {
		return cod_endereco;
	}


	public void setCod_endereco(int cod_endereco) {
		this.cod_endereco = cod_endereco;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public int getEh_vendedor() {
		return eh_vendedor;
	}


	public void setEh_vendedor(int eh_vendedor) {
		this.eh_vendedor = eh_vendedor;
	}


	public int getEh_cliente() {
		return eh_cliente;
	}


	public void setEh_cliente(int eh_cliente) {
		this.eh_cliente = eh_cliente;
	}


	public int getAtivo() {
		return ativo;
	}


	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	
	
}
