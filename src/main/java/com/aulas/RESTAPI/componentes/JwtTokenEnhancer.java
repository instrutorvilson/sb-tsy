package com.aulas.RESTAPI.componentes;

import com.aulas.RESTAPI.entidades.Usuario;
import com.aulas.RESTAPI.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
/*
* injetar token em Authorization server
* no metodo configure(AuthorizationServerEndpointsConfigurer endpoints)
* adicionar TokenChain
* */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());

        /*Conetudo a ser adicionado ao token*/
        Map<String, Object> map = new HashMap<>();
        map.put("Name",usuario.getUsername());
        map.put("Id", usuario.getId());
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(map);

        return token;
    }
}
