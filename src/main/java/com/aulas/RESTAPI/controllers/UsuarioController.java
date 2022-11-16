package com.aulas.RESTAPI.controllers;

import com.aulas.RESTAPI.entidades.Usuario;
import com.aulas.RESTAPI.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> consultar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(usuario));
    }
}
