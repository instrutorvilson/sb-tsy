package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.enums.CategoriaStatus;
import com.aulas.RESTAPI.services.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTests {
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

    //corpo
    @Test
    public void deveRetornarCreatedAoInserirCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setDescricao("Cereal");
        categoria.setStatus(CategoriaStatus.ATIVA);

        String categoriaString = objectMapper.writeValueAsString(categoria);

        ResultActions result = mockMvc.perform(post("/categorias")
                                      .content(categoriaString)
                                      .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
    }

    @Test
    public void naoRetornaNadaQuandoExcluirCategoria() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/categorias/{idcategoria}",1L)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNoContent());
    }
}
