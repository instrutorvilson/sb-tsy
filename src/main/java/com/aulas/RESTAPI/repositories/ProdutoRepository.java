package com.aulas.RESTAPI.repositories;

import com.aulas.RESTAPI.entidades.Categoria;
import com.aulas.RESTAPI.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByCategoria(Categoria cat);
}
