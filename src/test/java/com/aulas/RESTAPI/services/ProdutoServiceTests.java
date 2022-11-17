package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.dtos.ProdutoDTO;
import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.entidades.Produto;
import com.aulas.RESTAPI.enums.CategoriaStatus;
import com.aulas.RESTAPI.repositories.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {
    @InjectMocks //recurso a ser testado
    private ProdutoService produtoService;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    CategoriaService categoriaService;

    @Test
    public void retornaProdutoDTOQuandoSalvaComSucesso(){
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);

        Mockito.when(categoriaService.consultarById(1L)).thenReturn(categoria);

        Produto produto = new Produto();
        produto.setDescricao("Milho");
        produto.setEstoque(10.0f);
        produto.setPreco(1.50f);
        produto.setCategoria(categoria);

        Produto produtoExistente = new Produto();
        produtoExistente.setId(1L);
        produtoExistente.setDescricao("Milho");
        produtoExistente.setEstoque(10.0f);
        produtoExistente.setPreco(1.50f);
        produtoExistente.setCategoria(categoria);

        Mockito.when(produtoRepository.save(any())).thenReturn(produtoExistente);

        ProdutoDTO produtoDTO = new ProdutoDTO(produtoExistente);
        Assertions.assertNotNull(produtoService.salvar(produtoDTO));
    }
}
