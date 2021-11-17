package br.com.serratec.trabalhofinal.dto;

import java.io.Serializable;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.trabalhofinal.model.Cliente;
import br.com.serratec.trabalhofinal.model.Endereco;
import br.com.serratec.trabalhofinal.service.EnderecoService;


public class ClienteDTO implements Serializable {
    private Long id;
    private String nomeUsuario;
    private String email;
    private Endereco endereco;
    private String foto;

    @Autowired
    EnderecoService enderecoService;

    public ClienteDTO(){

    }

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nomeUsuario = cliente.getNomeUsuario();
        this.email = cliente.getEmail();
        this.foto = uriGenerator(cliente.getId());
        this.endereco =  cliente.getEndereco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String uriGenerator (Long id){
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/clientes/{id}/foto")
        .buildAndExpand(id).toUri();
        return uri.toString();
    }
}
