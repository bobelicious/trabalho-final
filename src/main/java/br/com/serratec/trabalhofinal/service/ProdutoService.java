package br.com.serratec.trabalhofinal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinal.dto.ProdutoDTO;
import br.com.serratec.trabalhofinal.exceptions.CategoriaException;
import br.com.serratec.trabalhofinal.exceptions.PedidoException;
import br.com.serratec.trabalhofinal.exceptions.ProdutoException;
import br.com.serratec.trabalhofinal.model.Categoria;
import br.com.serratec.trabalhofinal.model.Produto;
import br.com.serratec.trabalhofinal.repository.CategoriaRepository;
import br.com.serratec.trabalhofinal.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<ProdutoDTO> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto : produtos) {
            produtoDTOs.add(new ProdutoDTO(produto));
        }
        return produtoDTOs;
    }

    public ProdutoDTO buscar(Long id) throws ProdutoException {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            throw new ProdutoException("Produto não encontrado verifique o id");
        }
        return new ProdutoDTO(produto.get());
    }
    
    public ProdutoDTO atualizar(Produto produto, Long id) throws ProdutoException{
    	if(produtoRepository.findById(id) == null) {
    		throw new ProdutoException("Produto não encontrado verifique o id");
    	}
    	produto.setId(id);
    	produto = produtoRepository.save(produto);
    	return new ProdutoDTO(produto);
    }

    public ProdutoDTO adicionar(Produto produto) throws PedidoException, CategoriaException {
        if (produtoRepository.findByNome(produto.getNome()) != null) {
            throw new PedidoException("O pedido que você está cadastrando já existe");
        }
        produto.setDataCadastro(LocalDate.now());
        produto.setCategoria(buscarCategoria(produto.getCategoria()));
        produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    /**
     * metodo verificar se existe a categoria, se ela não existir ele salva a
     * categoria e retorna ela criada, caso a categoria exista ele retorna a
     * categoria.
     * 
     * @param categoria
     * @return Categoria
     * @throws CategoriaException
     */
    private Categoria buscarCategoria(Categoria categoria) throws CategoriaException {
        if (categoriaRepository.findByNome(categoria.getNome()) == null) {
            categoriaRepository.save(categoria);
            return categoria;
        }
        return categoriaRepository.findByNome(categoria.getNome());
    }

    public ProdutoDTO atualizar(Long id, Produto produto) throws ProdutoException {
        if (produtoRepository.findById(id).isEmpty()) {
            throw new ProdutoException("Produto não encontrado, verifique o id");
        }
        produto.setId(id);
        produto.setDataCadastro(LocalDate.now());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    public void deletar(Long id) throws ProdutoException {
        if (produtoRepository.findById(id).isEmpty()) {
            throw new ProdutoException("Produto não encontrado, verifique o id");
        }
        produtoRepository.deleteById(id);
    }

}
