package br.com.serratec.trabalhofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinal.dto.ItemPedidoDTO;
import br.com.serratec.trabalhofinal.exceptions.ItemPedidoException;
import br.com.serratec.trabalhofinal.model.ItemPedido;
import br.com.serratec.trabalhofinal.model.Pedido;
import br.com.serratec.trabalhofinal.model.Produto;
import br.com.serratec.trabalhofinal.repository.ItemPedidoRepository;
import br.com.serratec.trabalhofinal.repository.PedidoRepository;
import br.com.serratec.trabalhofinal.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ItemPedidoDTO> listar(){
		List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
		List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<>();
		for (ItemPedido itemPedido : itemPedidos) {
			ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO(itemPedido);
			itemPedidoDTOs.add(itemPedidoDTO);
		}
		return itemPedidoDTOs;
	}
	
	public ItemPedidoDTO buscar(Long id) throws ItemPedidoException{
		Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
		if(itemPedido.isPresent()) {
			return new ItemPedidoDTO(itemPedido.get());
		}
		throw new ItemPedidoException("Não encontrado");
	}
	
	public ItemPedidoDTO adicionar(ItemPedido itemPedido) {
		itemPedido.setPedido(buscarPedido(itemPedido.getPedido().getId()));
		itemPedido.setProduto(buscarProduto(itemPedido.getProduto().getId()));
		itemPedido.setPrecoVenda(itemPedido.getProduto().getVlUnitario());
		itemPedidoRepository.save(itemPedido);
		return new ItemPedidoDTO(itemPedido);
	}

	private Pedido buscarPedido(Long id){
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.get();
	}

	private Produto buscarProduto(Long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.get();
	}
	
	public ItemPedidoDTO atualizar (Long id, ItemPedido itemPedido) throws ItemPedidoException {
		if(itemPedidoRepository.existsById(id)) {
			itemPedido.setPedido(buscarPedido(itemPedido.getPedido().getId()));
			itemPedido.setProduto(buscarProduto(itemPedido.getProduto().getId()));
			itemPedido.setPrecoVenda(itemPedido.getProduto().getVlUnitario());
			itemPedido.setId(id);
			itemPedidoRepository.save(itemPedido);
			return new ItemPedidoDTO(itemPedido);
		}
		throw new ItemPedidoException("Não existe");
	}
	
	public void deletar(Long id) throws ItemPedidoException{
		Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
		if(itemPedido.isEmpty()) {
			throw new ItemPedidoException("Não existe, verifique o id");
		}
		itemPedidoRepository.deleteById(id);
	}
}
