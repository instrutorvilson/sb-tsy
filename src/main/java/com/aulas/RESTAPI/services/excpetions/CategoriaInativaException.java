package com.aulas.RESTAPI.services.excpetions;

public class CategoriaInativaException extends  RuntimeException{
    public CategoriaInativaException(String msg){
        super(msg);
    }
}
