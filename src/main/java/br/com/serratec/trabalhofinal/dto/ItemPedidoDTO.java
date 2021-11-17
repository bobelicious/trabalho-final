package br.com.serratec.trabalhofinal.dto;

import br.com.serratec.trabalhofinal.model.ItemPedido;
import br.com.serratec.trabalhofinal.model.Pedido;
import br.com.serratec.trabalhofinal.model.Produto;

public class ItemPedidoDTO {

	private Double precoVenda;
	private Pedido pedido;
	private Produto produto;
	private Double subTotal;
	
	
	public ItemPedidoDTO() {

	}

	public ItemPedidoDTO(ItemPedido itemPedido) {
		super();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.pedido = itemPedido.getPedido();
		this.produto = itemPedido.getProduto();
		this.subTotal = itemPedido.getSubTotal();
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
}
