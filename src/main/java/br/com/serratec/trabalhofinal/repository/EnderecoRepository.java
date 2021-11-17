package br.com.serratec.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinal.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public Endereco findByCep(String cep);
}