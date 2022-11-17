package com.aulas.RESTAPI.integracao;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.enums.CategoriaStatus;
import com.aulas.RESTAPI.services.CategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
/*
* @SpringBootTest carrega o contexto da aplicação
* não trabalha com mocks, ou seja usa os recursos do contexto
* Dados pesquisados devem existir na base de dados
* dependência deve ser injetada com @Autowired
* */

@SpringBootTest
public class CategoriaServiceIntegracao {
    @Autowired
    private CategoriaService service;

    @Test
    public void retornaCategoriaAoSalvar(){
        Categoria categoria = new Categoria();
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);
        Assertions.assertNotNull(service.salvar(categoria));
    }

    @Test
    public void retornaCategoriaQuandoConsultaIdExistente(){
        Assertions.assertNotNull(service.consultarById(2L));
    }

    @Test
    public void lancaEntityNotFoundExcpetionWhenQuandoConsultaCategoriaIdIneExistente(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.consultarById(1L));
    }

}
