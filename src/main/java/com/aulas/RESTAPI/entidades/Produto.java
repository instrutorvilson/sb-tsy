package com.aulas.RESTAPI.entidades;

import javax.persistence.*;

@Entity
@Table(name = "Tb_produto")
public class Produto {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private Float preco;
    private Float estoque;

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
}
