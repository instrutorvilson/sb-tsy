package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.entidades.Usuario;
import com.aulas.RESTAPI.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
       return repository.save(usuario);
    }

    public List<Usuario> consultar(){
        return repository.findAll();
    }

}
