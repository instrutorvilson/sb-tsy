package com.aulas.RESTAPI.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos") //http://localhost:8080/produtos
public class ProdutoController {

    @GetMapping
    public String ola(){
        return "Hello world.";
    }

    @GetMapping("/categorias") //http://localhost:8080/produtos/categorias
    public String consultar(){
        return "Pagina de consulta de produtos";
    }

    @GetMapping("/categorias/{nomeCategoria}")
    public String consultarByCategoria(@PathVariable("nomeCategoria") String categoria){
        return "vc consultou pela categoria:  " + categoria ;
    }

    @PostMapping
    public String salvar(@RequestBody String produto){
        return produto;
    }

    @PutMapping("/{idproduto}")
    public String alterar(@PathVariable("idproduto") String idproduto,
                          @RequestBody String produto){
        return "Id produto: " + idproduto + " Produto: " + produto;
    }
}
