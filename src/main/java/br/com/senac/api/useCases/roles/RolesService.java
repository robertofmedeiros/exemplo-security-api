package br.com.senac.api.useCases.roles;

import br.com.senac.api.entitys.Usuarios;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class RolesService {
    public boolean hasAnyRole(String permissoes) {
        boolean autorizado = false;
        String permissoesValidacao = "ADMIN";
        if(permissoes.length() > 0) {
            permissoesValidacao += "," + permissoes;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        for(GrantedAuthority role : roles){
            if(permissoesValidacao.contains(role.getAuthority())){
                autorizado = true;
            }
        }

        return autorizado;
    }
}
