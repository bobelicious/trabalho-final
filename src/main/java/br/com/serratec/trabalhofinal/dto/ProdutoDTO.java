package br.com.serratec.trabalhofinal.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import br.com.serratec.trabalhofinal.model.Categoria;
import br.com.serratec.trabalhofinal.model.ItemPedido;
import br.com.serratec.trabalhofinal.model.Produto;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private Long qtdEstoque;
    private LocalDate dataCadastro;
    private Double vlUnitario;
    private Categoria categoria;
    private List<ItemPedido> itemPedidos;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.qtdEstoque = produto.getQtdEstoque();
        this.dataCadastro = produto.getDataCadastro();
        this.vlUnitario = produto.getVlUnitario();
        this.categoria = produto.getCategoria();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Long qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getVlUnitario() {
        return vlUnitario;
    }

    public void setVlUnitario(Double vlUnitario) {
        this.vlUnitario = vlUnitario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }
}
