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

import br.com.serratec.trabalhofinal.exceptions.CategoriaException;
import br.com.serratec.trabalhofinal.model.Categoria;
import br.com.serratec.trabalhofinal.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	@ApiOperation(value = "Listar todas as categorias", notes = "Listagem de Categorias")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todas as categorias"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public List<Categoria> listar(){
		return categoriaService.listar();
	}
	
	@GetMapping("/{nome}")
	@ApiOperation(value = "Buscar categoria", notes = "Busca de Categoria por nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> buscar(@PathVariable String nome){
		try {
			return ResponseEntity.ok(categoriaService.buscar(nome));
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar uma categoria", notes = "Altera uma Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Altera uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
		try {
			return ResponseEntity.ok(categoriaService.atualizar(id, categoria));
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastrar uma categoria", notes = "Cadastro de categorias")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cadastra todas as categorias"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> adicionar(@Valid @RequestBody Categoria categoria){
		try {
			categoriaService.adicionar(categoria);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(categoria.getId()).toUri();
			return ResponseEntity.created(uri).body(categoria);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir uma categoria", notes = "Exclui uma Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Exclui uma categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		try {
			categoriaService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (CategoriaException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}
