package com.aulas.RESTAPI.entidades;

import com.aulas.RESTAPI.enums.CategoriaStatus;

import javax.persistence.*;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private CategoriaStatus status;

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

    public CategoriaStatus getStatus() {
        return status;
    }

    public void setStatus(CategoriaStatus status) {
        this.status = status;
    }
}