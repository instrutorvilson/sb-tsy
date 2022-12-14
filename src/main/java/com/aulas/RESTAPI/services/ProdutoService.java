package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.dtos.ProdutoDTO;
import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.entidades.Produto;
import com.aulas.RESTAPI.enums.CategoriaStatus;
import com.aulas.RESTAPI.repositories.CategoriaRepository;
import com.aulas.RESTAPI.repositories.ProdutoRepository;
import com.aulas.RESTAPI.services.excpetions.CategoriaInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    public Page<ProdutoDTO> consultaPaginada(Pageable pageable){
        Page<Produto>  pageProduto = produtoRepository.findAll(pageable);
        Page<ProdutoDTO> pageProdutoDto = pageProduto.map(prod -> new ProdutoDTO(prod));
        return pageProdutoDto;
    }

    public List<ProdutoDTO> consultar(){
        List<Produto> lista = produtoRepository.findAll();
        List<ProdutoDTO> listaDTO = new ArrayList<>();
        for(Produto prod : lista){
            listaDTO.add(new ProdutoDTO(prod));
        }
        return  listaDTO;
    }

    private Produto consultar(Long id){
        Optional<Produto> obj = produtoRepository.findById(id);
        Produto prod = obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        return prod;
    }
    public ProdutoDTO consultarById(Long id){
      Optional<Produto> obj = produtoRepository.findById(id);
      Produto prod = null;// obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        try{
             prod = obj.get();
        }
        catch (NoSuchElementException e){
            throw new EntityNotFoundException("Produto não encontrado");
        }
      return new ProdutoDTO(prod);
    }
    @Transactional
    public ProdutoDTO salvar(ProdutoDTO produtoDTO){
        Categoria cat = categoriaService.consultarById(produtoDTO.getCategoria().getId());

        if(cat.getStatus() == CategoriaStatus.INATIVA){
            throw new CategoriaInativaException("Categoria inativa");
        }
        Produto prod = new Produto();
        prod.setDescricao(produtoDTO.getDescricao());
        prod.setPreco(produtoDTO.getPreco());
        prod.setEstoque(produtoDTO.getEstoque());
        prod.setCategoria(produtoDTO.getCategoria());

        Produto entidadeProduto = produtoRepository.save(prod);
        ProdutoDTO retornoDTO = new ProdutoDTO(entidadeProduto);
        return retornoDTO;
    }
    public Produto alterar(Long idproduto, Produto produto){
        Produto prod = this.consultar(idproduto);

        prod.setDescricao(produto.getDescricao());
        prod.setPreco(produto.getPreco());
        prod.setEstoque(produto.getEstoque());
        return  null;
       // return this.salvar(prod);
    }
    @Transactional
    public void excluir(Long idproduto){
        Produto prod = this.consultar(idproduto);
        produtoRepository.delete(prod);
    }

}
