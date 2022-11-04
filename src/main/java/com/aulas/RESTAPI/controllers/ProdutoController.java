package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Produto;
import com.aulas.RESTAPI.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos") //http://localhost:8080/produtos
public class ProdutoController {
    @Autowired
    ProdutoRepository repo;
    @GetMapping
    public ResponseEntity<List<Produto>> consultarProdutos(){
        List<Produto> lista = repo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    @GetMapping("/{idproduto}")
    public ResponseEntity<Produto> consultarById(@PathVariable("idproduto") Long idproduto){
       Produto prod = repo.findById(idproduto).get();
       return  ResponseEntity.ok().body(prod);
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
    public ResponseEntity<Produto> alterar(@PathVariable("idproduto") Long idproduto,
                          @RequestBody Produto produto){
       return ResponseEntity.ok().body(repo.save(produto));
    }
}
