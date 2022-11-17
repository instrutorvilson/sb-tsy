package com.aulas.RESTAPI.integracao;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.enums.CategoriaStatus;
import com.aulas.RESTAPI.services.CategoriaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerIntegracao {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Njg3NjcyMzAsInVzZXJfbmFtZSI6ImpvYW5hQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiYjI5YmMwMDMtZTI1Ny00NjVhLWI3MDktZmZkMjE2ZGI1NmQ5IiwiY2xpZW50X2lkIjoidHN5c3RlbSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.7wy5BicnzVKv2sz6mG-2aZ9Gi_r5PG3jZS7_wvVvUpA";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaService service;

    @Test
    public void retornaOkAoConsultarUmaCategoriaExistente() throws Exception {
         ResultActions result =
                mockMvc.perform(get("/categorias/{idcategoria}", 2L)
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.descricao").exists());
        result.andExpect(jsonPath("$.descricao").value("Cereal"));
    }

    @Test
    public void retorna404AoConsultarUmaCategoriaNaoExistente() throws Exception {
       ResultActions result =
                mockMvc.perform(get("/categorias/{idcategoria}", 1L)
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }
}
