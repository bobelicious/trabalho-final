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

import br.com.serratec.trabalhofinal.dto.ProdutoDTO;
import br.com.serratec.trabalhofinal.exceptions.ProdutoException;
import br.com.serratec.trabalhofinal.model.Produto;
import br.com.serratec.trabalhofinal.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	@ApiOperation(value = "Listar todos os produtos", notes = "Listagem de Produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public List<ProdutoDTO> listar(){
		return produtoService.listar();
	}
	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um produto", notes = "Busca de Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> buscar(@PathVariable Long id){
		try {
			return ResponseEntity.ok(produtoService.buscar(id));
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar um produto", notes = "Altera um Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Altera um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Produto produto){
		try {
			return ResponseEntity.ok(produtoService.atualizar(produto, id));
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	@PostMapping
	@ApiOperation(value = "Cadastrar um produto", notes = "Cadastro de Produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cadastra todos os produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> adicionar(@Valid @RequestBody Produto produto){
		try {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
			produtoService.adicionar(produto);
			ProdutoDTO produtoDTO = new ProdutoDTO(produto);
			return ResponseEntity.created(uri).body(produtoDTO);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir um produto", notes = "Exclui um produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Exclui um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		try {
			produtoService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (ProdutoException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}
