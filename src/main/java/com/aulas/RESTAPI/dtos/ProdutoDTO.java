package com.aulas.RESTAPI.dtos;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.entidades.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProdutoDTO {
    private long id;
    @NotBlank(message = "Informe descrição")
    @Size(message = "O campo deve ter entre 3 e 10 caracteres", max = 10, min = 3)
    private String descricao;
    private Float preco;
    private Float estoque;
    private Categoria categoria;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto){
       id = produto.getId();
       descricao = produto.getDescricao();
       preco = produto.getPreco();
       estoque = produto.getEstoque();
       categoria = produto.getCategoria();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Float getEstoque() {
        return estoque;
    }

    public void setEstoque(Float estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
