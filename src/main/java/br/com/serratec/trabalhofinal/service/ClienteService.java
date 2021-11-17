package br.com.serratec.trabalhofinal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.trabalhofinal.config.MailConfig;
import br.com.serratec.trabalhofinal.dto.ClienteDTO;
import br.com.serratec.trabalhofinal.dto.ClienteInserirDTO;
import br.com.serratec.trabalhofinal.exceptions.EmailException;
import br.com.serratec.trabalhofinal.model.Cliente;
import br.com.serratec.trabalhofinal.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private FotoService fotoService;

    public List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();

        for (Cliente cliente : clientes) {
          ClienteDTO clienteDTO = new ClienteDTO(cliente);
            clientesDTO.add(clienteDTO);
        }
        return clientesDTO;
    }
    @Transactional
    public ClienteDTO buscar(String email) throws EmailException {
        Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.findByEmail(email));
        if (cliente.isEmpty()) {
            throw new EmailException("Cliente não existe");
        }
        ClienteDTO clienteDTO = new ClienteDTO(cliente.get());
        return clienteDTO;
    }

    @Transactional
    public ClienteDTO inserir(ClienteInserirDTO clienteInserirDTO, MultipartFile file) throws EmailException, IOException {
        if (clienteRepository.findByEmail(clienteInserirDTO.getEmail()) != null) {
            throw new EmailException("Email ja existe! Insira outro");
        }
        clienteInserirDTO.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
        clienteInserirDTO.setEndereco(enderecoService.buscarEndereco(clienteInserirDTO.getEndereco().getCep()));
        Cliente cliente = new Cliente(clienteInserirDTO);
        fotoService.inserir(clienteRepository.save(cliente),file);
        mailConfig.enviarEmail(cliente.getEmail(), "Cadastro de cliente confirmado", cliente.toString());

        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO atualizar(String email, ClienteInserirDTO clienteInserirDTO, MultipartFile file) throws IOException,EmailException {
        if ( clienteRepository.findByEmail(email) == null) {
            throw new EmailException("Email não encontrado, verifique o email ou insira outro");
        }
        clienteInserirDTO.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
        clienteInserirDTO.setEndereco(enderecoService.buscarEndereco(clienteInserirDTO.getEndereco().getCep()));
        Cliente cliente = new Cliente(clienteInserirDTO);
        Cliente id = clienteRepository.findByEmail(email);
        cliente.setId(id.getId());
        fotoService.inserir(clienteRepository.save(cliente),file);

        mailConfig.enviarEmail(cliente.getEmail(), "Atualização de cadastro confirmado", cliente.toString());

        return new ClienteDTO(cliente);
    }

    @Transactional
    public void deletar(String email) throws EmailException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente.getEmail() == null) {
            throw new EmailException("Cliente não existe, verifique o email informado");
        }
        Long id = cliente.getId();
        clienteRepository.deleteById(id);
    }

    public ClienteDTO buscarById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return new ClienteDTO(cliente.get());
        }
        return null;
    }
}
