package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Produto;
import com.aulas.RESTAPI.repositories.ProdutoRepository;
import com.aulas.RESTAPI.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos") //http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    ProdutoService service;
    @GetMapping
    public ResponseEntity<List<Produto>> consultarProdutos(){
        List<Produto> lista = service.consultar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        Produto prod = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }
    @GetMapping("/{idproduto}")
    public ResponseEntity<Produto> consultarById(@PathVariable("idproduto") Long idproduto){
       return  ResponseEntity.ok().body(service.consultarById(idproduto));
    }

    @PutMapping("/{idproduto}")
    public ResponseEntity<Object> alterar(@PathVariable("idproduto") Long idproduto,
                                          @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idproduto, produto));
    }

    @DeleteMapping("/{idproduto}")
    public ResponseEntity<Void> excluir(@PathVariable("idproduto") Long idproduto) {
         service.excluir(idproduto);
         return ResponseEntity.noContent().build();
    }

  /*
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
    public ResponseEntity<Object> alterar(@PathVariable("idproduto") Long idproduto,
                          @RequestBody Produto produto){
       //return ResponseEntity.ok().body(repo.save(produto));
        try{
            Produto prod = repo.findById(idproduto).get();
            if(prod != null){
               // prod.setDescricao(produto.getDescricao());
               // prod.setPreco(produto.getPreco());
              //  prod.setEstoque(produto.getEstoque());
              //  repo.save(prod);
                produto.setId(idproduto);
                repo.save(produto);
            }
            return ResponseEntity.ok().body(produto);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe.");
        }
    }

    @DeleteMapping("/{idproduto}")
    public ResponseEntity<String> excluir(@PathVariable("idproduto") Long idproduto){
       try{
           Produto prod = repo.findById(idproduto).get();
           if(prod != null){
               repo.delete(prod);
           }
           return ResponseEntity.ok().body("Produto excluído com sucesso.");
       }
       catch (Exception e) {
           return ResponseEntity.ok().body("Produto não existe.");
       }
    }*/

}
