package com.pedeagua.testews;



public class Pedido {
	private int id;
	private int cod_cliente;
	private int cod_vendedor;
	private int ativo;
	private String valorTotal;
	private String trocoPara;
	private long tempoCancelamento;
	
	
	public Pedido(){}
	
	public Pedido(int id, int cod_cliente, int cod_vendedor, int ativo,
			String valorTotal, String trocoPara, long tempoCancelamento) {
		this.id = id;
		this.cod_cliente = cod_cliente;
		this.cod_vendedor = cod_vendedor;
		this.ativo = ativo;
		this.valorTotal = valorTotal;
		this.trocoPara = trocoPara;
		this.tempoCancelamento = tempoCancelamento;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCod_cliente() {
		return cod_cliente;
	}


	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}


	public int getCod_vendedor() {
		return cod_vendedor;
	}


	public void setCod_vendedor(int cod_vendedor) {
		this.cod_vendedor = cod_vendedor;
	}


	public int getAtivo() {
		return ativo;
	}


	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}


	public String getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	public String getTrocoPara() {
		return trocoPara;
	}

	public void setTrocoPara(String trocoPara) {
		this.trocoPara = trocoPara;
	}
	
	

	public long getTempoCancelamento() {
		return tempoCancelamento;
	}

	public void setTempoCancelamento(long tempoCancelamento) {
		this.tempoCancelamento = tempoCancelamento;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cod_cliente=" + cod_cliente
				+ ", cod_vendedor=" + cod_vendedor + ", ativo=" + ativo
				+ ", valorTotal=" + valorTotal + "]";
	}
	
	
	

	
}