package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{idproduto}")
    public String alterar(@PathVariable("idproduto") String idproduto,
                          @RequestBody String produto){
        return "Id produto: " + idproduto + " Produto: " + produto;
    }
}
