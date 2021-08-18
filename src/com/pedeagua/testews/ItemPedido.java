package com.pedeagua.testews;



public class ItemPedido implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int cod_pedido;
	private int cod_produto;
	private int qtd;
	private int cancelado;
	
	public ItemPedido() {}

	public ItemPedido(int id, int cod_pedido, int cod_produto, int qtd,
			int cancelado) {
		this.id = id;
		this.cod_pedido = cod_pedido;
		this.cod_produto = cod_produto;
		this.qtd = qtd;
		this.cancelado = cancelado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCod_pedido() {
		return cod_pedido;
	}

	public void setCod_pedido(int cod_pedido) {
		this.cod_pedido = cod_pedido;
	}

	public int getCod_produto() {
		return cod_produto;
	}

	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public int getCancelado() {
		return cancelado;
	}

	public void setCancelado(int cancelado) {
		this.cancelado = cancelado;
	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", cod_pedido=" + cod_pedido
				+ ", cod_produto=" + cod_produto + ", qtd=" + qtd
				+ ", cancelado=" + cancelado + "]";
	}
	
	
}
