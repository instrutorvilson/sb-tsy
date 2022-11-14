package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.enums.CategoriaStatus;
import com.aulas.RESTAPI.repositories.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceTest {
    @InjectMocks //recurso a ser testado
    private CategoriaService service;

    @Mock  //recurso simulado que será usado pelo recurso a ser testado
    private CategoriaRepository repository;

    @Test
    public void retornaCategoriaAoSalvar(){
        Categoria categoria = new Categoria();
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);

        Mockito.when(repository.save(categoria)).thenReturn(categoria);
        Assertions.assertNotNull(service.salvar(categoria));

        Mockito.verify(repository,Mockito.times(1)).save(categoria);
    }

    @Test
    public void fazNadaQuandoExcluirCategoriaExistente(){
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);

        Mockito.when(repository.findById(categoria.getId())).thenReturn(Optional.of(categoria));
        Mockito.doNothing().when(repository).delete(categoria);

        Assertions.assertDoesNotThrow(()-> service.excluir(categoria.getId()));
        Mockito.verify(repository, Mockito.times(1)).delete(categoria);
    }

    @Test
    public void RetornaListaQuandoConsultaTodosSemInformaStatus(){
        List<Categoria> lista = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(lista);
        Assertions.assertNotNull(service.consultar(""));
    }

    @Test
    public void RetornaListaQuandoConsultaTodosQuandoInformaStatus(){
        List<Categoria> lista = new ArrayList<>();
        CategoriaStatus status = CategoriaStatus.ATIVA;
        Mockito.when(repository.findCategorias(status)).thenReturn(lista);
        Assertions.assertNotNull(service.consultar(status.name()));
    }



}
