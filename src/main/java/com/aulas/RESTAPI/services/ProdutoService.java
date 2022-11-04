package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.entidades.Produto;
import com.aulas.RESTAPI.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> consultar(){
        return  produtoRepository.findAll();
    }

    public Produto consultarById(Long id){
      Optional<Produto> obj = produtoRepository.findById(id);
      Produto prod = null;// obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        try{
             prod = obj.get();
        }
        catch (NoSuchElementException e){
            throw new EntityNotFoundException("Produto não encontrado");
        }
      return prod;
    }
    @Transactional
    public Produto salvar(Produto produto){
        //validações
        return produtoRepository.save(produto);
    }

    public Produto alterar(Long idproduto, Produto produto){
        Produto prod = this.consultarById(idproduto);

        prod.setDescricao(produto.getDescricao());
        prod.setPreco(produto.getPreco());
        prod.setEstoque(produto.getEstoque());

        return this.salvar(prod);
    }
    @Transactional
    public void excluir(Long idproduto){
        Produto prod = this.consultarById(idproduto);
        produtoRepository.delete(prod);
    }

}
