package com.aulas.RESTAPI.services.excpetions;

public class EmailJaCadastradoException extends  RuntimeException{

    public EmailJaCadastradoException(String msg){
        super(msg);
    }
}
