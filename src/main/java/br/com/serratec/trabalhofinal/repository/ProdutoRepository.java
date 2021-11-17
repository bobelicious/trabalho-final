package br.com.serratec.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinal.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    public Produto findByNome(String nome);
}
