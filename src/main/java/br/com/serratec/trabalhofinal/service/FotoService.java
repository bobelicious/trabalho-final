package br.com.serratec.trabalhofinal.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import br.com.serratec.trabalhofinal.exceptions.EmailException;
import br.com.serratec.trabalhofinal.model.Cliente;
import br.com.serratec.trabalhofinal.model.Foto;
import br.com.serratec.trabalhofinal.repository.FotoRepository;

@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    public Foto inserir(Cliente cliente, MultipartFile file) throws IOException, EmailException {
        Foto foto = new Foto();
        foto.setNome(file.getName());
        foto.setDados(file.getBytes());
        foto.setTipo(file.getContentType());
        foto.setCliente(cliente);
        fotoRepository.save(foto);
        return foto;
    }

    public Foto buscar(Long id){
        Optional<Foto> foto = fotoRepository.findById(id);
        if (foto.isPresent()){
            return foto.get();
        }
        return null;
    }

}
