package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria){
        //implementar validações
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> consultar(){
        return categoriaRepository.findAll();
    }
}
