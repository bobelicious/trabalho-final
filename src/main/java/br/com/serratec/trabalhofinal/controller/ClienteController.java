package br.com.serratec.trabalhofinal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.trabalhofinal.dto.ClienteDTO;
import br.com.serratec.trabalhofinal.dto.ClienteInserirDTO;
import br.com.serratec.trabalhofinal.exceptions.EmailException;
import br.com.serratec.trabalhofinal.model.Foto;
import br.com.serratec.trabalhofinal.service.ClienteService;
import br.com.serratec.trabalhofinal.service.FotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FotoService fotoService;

    @GetMapping
    @ApiOperation(value = "Listar todos os clientes", notes = "Listagem de Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{email}")
    @ApiOperation(value = "Buscar cliente", notes = "Busca de Cliente por Email")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
    public ResponseEntity<Object> buscar( @PathVariable String email) {
        try {
            return ResponseEntity.ok(clienteService.buscar(email));
        } catch (EmailException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar um cliente", notes = "Cadastro de Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cadastra todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
    public ResponseEntity<Object> adicionar(@Valid @RequestPart ClienteInserirDTO clienteInserirDTO, @RequestParam MultipartFile file) {
        try {
            ClienteDTO clienteDTO = clienteService.inserir(clienteInserirDTO,  file);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(clienteDTO);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PutMapping("/{email}")
    @ApiOperation(value = "Alterar um cliente", notes = "Altera um Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Altera um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
    public ResponseEntity<Object> atualizar(@PathVariable String email, @Valid @RequestPart ClienteInserirDTO clienteInserirDTO, @RequestParam MultipartFile file) {
        try {
            ClienteDTO clienteDTO = clienteService.atualizar(email, clienteInserirDTO, file);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(clienteDTO);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @DeleteMapping("/{email}")
    @ApiOperation(value = "Excluir um cliente", notes = "Exclui um Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Exclui um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
    public ResponseEntity<Object> deletar(@PathVariable String email){
        try {
            clienteService.deletar(email);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> buscarPorFoto(@PathVariable Long id){
        Foto foto = fotoService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", foto.getTipo());
        headers.add("content-length", String.valueOf(foto.getDados().length));
        return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
    }
}
