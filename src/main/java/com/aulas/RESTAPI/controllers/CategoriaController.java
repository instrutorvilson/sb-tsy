package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

     @GetMapping
     public ResponseEntity<List<Categoria>> consultar(){
         return ResponseEntity.status(HttpStatus.OK).body(categoriaService.consultar());
     }

     @GetMapping("/{idcategoria}")
     public ResponseEntity<Categoria> consultarById(@PathVariable("idcategoria") long idcategoria){
         return ResponseEntity.status(HttpStatus.OK).body(categoriaService.consultarById(idcategoria));
     }

     @PostMapping
     public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvar(categoria));
     }

     @PutMapping("/{idcategoria}")
    public ResponseEntity<Categoria> alterar(@PathVariable("idcategoria") long idcategoria,
                                             @RequestBody Categoria categoria){
         return ResponseEntity.status(HttpStatus.OK).body(categoriaService.alterar(idcategoria, categoria));
     }

}