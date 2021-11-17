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

import br.com.serratec.trabalhofinal.dto.PedidoDTO;
import br.com.serratec.trabalhofinal.exceptions.EmailException;
import br.com.serratec.trabalhofinal.exceptions.PedidoException;
import br.com.serratec.trabalhofinal.model.Pedido;
import br.com.serratec.trabalhofinal.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ApiOperation(value = "Listar todos os pedidos", notes = "Listagem de Pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public List<PedidoDTO> listar() {
        return pedidoService.listar();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um pedido", notes = "Busca de Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public ResponseEntity<Object> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.buscar(id));
        } catch (PedidoException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar um pedido", notes = "Cadastra um Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cadastra todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public ResponseEntity<Object> adicionar(@Valid @RequestBody Pedido pedido) {
        try {
           PedidoDTO pedidoDTO = pedidoService.adicionar(pedido);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedidoDTO.getId())
            .toUri();
            return ResponseEntity.created(uri).body(pedidoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Alterar um pedido", notes = "Altera um Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Altera um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok(pedidoService.atualizar(id, pedido));
        } catch (PedidoException | EmailException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir um pedido", notes = "Exclui um Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Exclui um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public ResponseEntity<Object> remover(@PathVariable Long id) {
        try {
            pedidoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (PedidoException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
