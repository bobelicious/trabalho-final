package br.com.serratec.trabalhofinal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.trabalhofinal.dto.ItemPedidoDTO;
import br.com.serratec.trabalhofinal.model.ItemPedido;
import br.com.serratec.trabalhofinal.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping
	@ApiOperation(value = "Listar todos os itens do pedido", notes = "Listagem de Item do Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os itens do pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public List<ItemPedidoDTO> listar(){
		return itemPedidoService.listar();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um item pedido", notes = "Buscar um ItemPedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um item pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public ResponseEntity<Object> buscar (@PathVariable Long id){
		try {
			return ResponseEntity.ok(itemPedidoService.buscar(id));
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar um item pedido", notes = "Altera um ItemPedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Altera um item pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public ResponseEntity<Object> atualizar (@PathVariable Long id, @RequestBody ItemPedido itemPedido){
		try {
			return ResponseEntity.ok(itemPedidoService.atualizar(id, itemPedido));
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastrar um item pedido", notes = "Cadastra um ItemPedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cadastra um item pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public ResponseEntity<Object> adicionar (@Valid @RequestBody ItemPedido itemPedido){
		try {
			ItemPedido item = itemPedido;
			itemPedidoService.adicionar(itemPedido);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(itemPedido.getId()).toUri();
			return ResponseEntity.created(uri).body(item);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		} 
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir um item pedido", notes = "Exclui um ItemPedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Exclui um item pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public ResponseEntity<Object> deletar (@PathVariable Long id){
		try {
			itemPedidoService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}
