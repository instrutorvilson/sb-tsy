package com.aulas.RESTAPI.services;

import com.aulas.RESTAPI.entidades.Usuario;
import com.aulas.RESTAPI.repositories.UsuarioRepository;
import com.aulas.RESTAPI.services.excpetions.EmailJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario){
        if(repository.findByEmail(usuario.getEmail()) != null){
            throw new EmailJaCadastradoException("Email já cadastrado");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public List<Usuario> consultar(){
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByEmail(username);
        return user;
    }
}
