package br.com.senac.api.useCases.roles;

import br.com.senac.api.entitys.Roles;
import br.com.senac.api.entitys.Usuarios;
import br.com.senac.api.useCases.roles.implement.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

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

    public void criarRoles(String role, String descricao) {
        Optional<Roles> rolesResult = rolesRepository.findByPermissao(role);
        if(!rolesResult.isPresent()){
            Roles rolesPersist = new Roles(role, descricao);

        }
    }
}
