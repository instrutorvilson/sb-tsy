package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Transactional
    public Categoria salvar(Categoria categoria){
        //implementar validações
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> consultar(){
        return categoriaRepository.findAll();
    }

    public Categoria consultarById(long idcategoria) {
        Optional<Categoria> obj = categoriaRepository.findById(idcategoria);
        Categoria cat = obj.orElseThrow(()-> new EntityNotFoundException("Categoria não encontrada."));
        return cat;
    }

    public Categoria alterar(long idcategoria, Categoria categoria){
        Categoria cat = this.consultarById(idcategoria);
        cat.setDescricao(categoria.getDescricao());
        cat.setStatus(categoria.getStatus());

        return this.salvar(cat);
    }

}

