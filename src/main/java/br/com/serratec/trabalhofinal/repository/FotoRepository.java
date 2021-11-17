package br.com.serratec.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinal.model.Foto;

public interface FotoRepository extends JpaRepository<Foto,Long> {
    
}
