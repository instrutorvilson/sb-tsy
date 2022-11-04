package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Produto;
import com.aulas.RESTAPI.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos") //http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    ProdutoRepository repo;

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
        Produto prod = repo.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @PutMapping("/{idproduto}")
    public String alterar(@PathVariable("idproduto") String idproduto,
                          @RequestBody String produto){
        return "Id produto: " + idproduto + " Produto: " + produto;
    }
}
