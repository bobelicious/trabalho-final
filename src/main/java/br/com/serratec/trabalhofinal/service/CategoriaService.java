package br.com.serratec.trabalhofinal.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinal.exceptions.CategoriaException;
import br.com.serratec.trabalhofinal.model.Categoria;
import br.com.serratec.trabalhofinal.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    public Categoria buscar(String nome) throws CategoriaException{
        Categoria categoria = categoriaRepository.findByNome(nome);
        if(categoria == null){
            throw new CategoriaException("Categoria não existe");
        }
        return categoria;
    }
    
    public Categoria adicionar(Categoria categoria) throws CategoriaException {
		if(categoriaRepository.findByNome(categoria.getNome()) == null){
			categoriaRepository.save(categoria);
		}
		throw new CategoriaException("Categoria ja existe");
    }
    
    public Categoria atualizar(Long id, Categoria categoria) throws CategoriaException{
    	if(categoriaRepository.existsById(id)) {
    		categoria.setId(id);
    		categoria = categoriaRepository.save(categoria);
    		return categoria;
    	}
    	throw new CategoriaException("Categoria não existe, verifique o id");
    }
    
    public void deletar (Long id) throws CategoriaException {
    	Optional<Categoria> categoria = categoriaRepository.findById(id);
    	if(categoria.isEmpty()) {
    		throw new CategoriaException("Categoria não existe, verifique o id");
    	}
    	categoriaRepository.deleteById(id);
    }
    
}
