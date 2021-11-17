package br.com.serratec.trabalhofinal.dto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.serratec.trabalhofinal.model.Endereco;
import br.com.serratec.trabalhofinal.model.Foto;
import br.com.serratec.trabalhofinal.service.EnderecoService;

public class ClienteInserirDTO{
    private String email;
    private String nomeUsuario;
    private String nomeCompleto;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private Long numero;
    private String complemento;
    private Foto foto;

    @Autowired
    EnderecoService enderecoService;

    public ClienteInserirDTO(){

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

}
