package br.com.serratec.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinal.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    public Categoria findByNome(String nome);
}
