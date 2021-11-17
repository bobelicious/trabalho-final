package br.com.serratec.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
   public  Cliente findByEmail(String email);

}
