package br.com.serratec.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinal.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    
}
