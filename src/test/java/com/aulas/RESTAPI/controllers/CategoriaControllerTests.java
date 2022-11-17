package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.enums.CategoriaStatus;
import com.aulas.RESTAPI.services.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTests {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Njg3NjcyMzAsInVzZXJfbmFtZSI6ImpvYW5hQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiYjI5YmMwMDMtZTI1Ny00NjVhLWI3MDktZmZkMjE2ZGI1NmQ5IiwiY2xpZW50X2lkIjoidHN5c3RlbSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.7wy5BicnzVKv2sz6mG-2aZ9Gi_r5PG3jZS7_wvVvUpA";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService service;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void retornaOKAoConsultarCategorias() throws Exception {
       ResultActions result = mockMvc.perform(get("/categorias")
                                     .accept(MediaType.APPLICATION_JSON));
       result.andExpect(status().isOk());
    }

    @Test
    public void retornaOkAoConsultarUmaCategoria() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Njg3NjcyMzAsInVzZXJfbmFtZSI6ImpvYW5hQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiYjI5YmMwMDMtZTI1Ny00NjVhLWI3MDktZmZkMjE2ZGI1NmQ5IiwiY2xpZW50X2lkIjoidHN5c3RlbSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.7wy5BicnzVKv2sz6mG-2aZ9Gi_r5PG3jZS7_wvVvUpA";

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);

        Mockito.when(service.consultarById(1L)).thenReturn(categoria);

        ResultActions result =
                mockMvc.perform(get("/categorias/{idcategoria}",categoria.getId())
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.descricao").exists());
        result.andExpect(jsonPath("$.descricao").value("Cereal"));
    }
    @Test
    public void deveRetornarCreatedAoInserirCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);

        Mockito.when(service.salvar(any())).thenReturn(categoria);

        String categoriaString = objectMapper.writeValueAsString(categoria);
        ResultActions result = mockMvc.perform(post("/categorias")
                .header("Authorization", "Bearer " + token)
                                      .content(categoriaString)
                                      .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.descricao").exists());
        result.andExpect(jsonPath("$.descricao").value("Cereal"));
    }

    @Test
    public void deveRetornarOkAlterarCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);

        Mockito.when(service.alterar(eq(1L),any())).thenReturn(categoria);

        String categoriaString = objectMapper.writeValueAsString(categoria);
        ResultActions result = mockMvc.perform(put("/categorias/{idcategoria}", 1L)
                .header("Authorization", "Bearer " + token)
                .content(categoriaString)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.descricao").exists());
        result.andExpect(jsonPath("$.descricao").value("Cereal"));
    }

    @Test
    public void naoRetornaNadaQuandoExcluirCategoria() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/categorias/{idcategoria}",1L)
                        .header("Authorization", "Bearer " + token));
        result.andExpect(status().isNoContent());
    }
}
