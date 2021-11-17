package br.com.serratec.trabalhofinal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinal.dto.PedidoDTO;
import br.com.serratec.trabalhofinal.exceptions.EmailException;
import br.com.serratec.trabalhofinal.exceptions.PedidoException;
import br.com.serratec.trabalhofinal.model.Pedido;
import br.com.serratec.trabalhofinal.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    public List<PedidoDTO> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOs = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            PedidoDTO pedidoDTO = new PedidoDTO(pedido);
            pedidoDTO.setClienteDTO(clienteService.buscarById(pedido.getCliente().getId()));
            pedidoDTOs.add(pedidoDTO);
        }
        return pedidoDTOs;
    }

    public PedidoDTO buscar(Long id) throws PedidoException {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            PedidoDTO pedidoDTO = new PedidoDTO(pedido.get());
            pedidoDTO.setClienteDTO(clienteService.buscarById(pedido.get().getCliente().getId()));
            return pedidoDTO;
        }
        throw new PedidoException("Pedido não existe");
    }

    public PedidoDTO adicionar(Pedido pedido) throws EmailException {
        pedido.setId(null);
        pedido.setDataPedido(LocalDate.now());
        pedidoRepository.save(pedido);
        PedidoDTO pedidoDTO = new PedidoDTO(pedido);
        pedidoDTO.setClienteDTO(clienteService.buscarById(pedido.getCliente().getId()));
        return pedidoDTO;
    }

    public PedidoDTO atualizar(Long id, Pedido pedido) throws PedidoException, EmailException {
        if (pedidoRepository.existsById(id)) {
            Optional<Pedido> pedidoNovo = pedidoRepository.findById(id);
            pedido.setDataPedido(pedidoNovo.get().getDataPedido());
            pedido.setCliente(pedidoNovo.get().getCliente());
            pedido = pedidoRepository.save(pedido);
            PedidoDTO pedidoDTO = new PedidoDTO(pedido);
            pedidoDTO.setClienteDTO(clienteService.buscarById(pedido.getCliente().getId()));
            return pedidoDTO;
        }
        throw new PedidoException("Pedido não existe");
    }

    public void deletar(Long id) throws PedidoException {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isEmpty()) {
            throw new PedidoException("Pedido não existe, verifique o id");
        }
        pedidoRepository.deleteById(id);
    }
}
