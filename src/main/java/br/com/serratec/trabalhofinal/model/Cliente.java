package br.com.serratec.trabalhofinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.validator.constraints.br.CPF;

import br.com.serratec.trabalhofinal.dto.ClienteInserirDTO;


@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column
    @NotBlank(message = "por favor insira um email")
    @Email(message = "email invalido")
    private String email;

    @Column(name = "nome_usuario")
    @NotBlank(message = "usuario não pode ser nulo")
    private String nomeUsuario;

    @Column(name = "nome_completo")
    @NotBlank(message = "nome não pode ser nulo")
    private String nomeCompleto;

    @Column(nullable = false)
    @NotBlank(message = "senha não pode ser nula")
    private String senha;

    @Column
    @CPF(message = "cpf inválido")
    @NotBlank(message = "cpf não pode ser nulo")
    private String cpf;

    @Column
    private String telefone;

    @Column(name="data_nasc")
    private LocalDate dataNascimento;

    @Column
    private String complemento;

    @Column(nullable = false)
    private Long numero;



    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    @JsonBackReference
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pedido>pedidos;

    @OneToOne(mappedBy = "cliente", cascade=CascadeType.ALL)
    private Foto foto;

    @Override
    public String toString() {
        return "Cliente [email=" + email + ", id=" + id + ", nomeUsuario=" + nomeUsuario + "]";
    }

    public Cliente() {
    }

    public Cliente(ClienteInserirDTO clienteInserirDTO) {
        this.email = clienteInserirDTO.getEmail();
        this.nomeUsuario = clienteInserirDTO.getNomeUsuario();
        this.nomeCompleto = clienteInserirDTO.getNomeCompleto();
        this.senha = clienteInserirDTO.getSenha();
        this.cpf = clienteInserirDTO.getCpf();
        this.telefone = clienteInserirDTO.getTelefone();
        this.dataNascimento = clienteInserirDTO.getDataNascimento();
        this.numero = clienteInserirDTO.getNumero();
        this.complemento = clienteInserirDTO.getComplemento();
        this.endereco = clienteInserirDTO.getEndereco();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
