package br.com.senac.api.useCases.funcionalidades;

import br.com.senac.api.entitys.Funcionalidades;
import br.com.senac.api.entitys.FuncionalidadesRoles;
import br.com.senac.api.useCases.funcionalidades.implement.FuncionalidadesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionalidadeService {
    @Autowired
    private FuncionalidadesRespository funcionalidadesRespository;

    public void criarFuncionalidade(String controller, String funcionalidade) {
        Optional<Funcionalidades> funcionalidadesResult = funcionalidadesRespository.findByControllerAndFuncionalidade(controller, funcionalidade);
        if(!funcionalidadesResult.isPresent()){
            Funcionalidades funcionalidadesPersist = new Funcionalidades(controller, funcionalidade);

            try {
                funcionalidadesRespository.save(funcionalidadesPersist);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao criar funcionalidade, controller: " + controller +
                        " funcionalidade: " + funcionalidade);
            }

        }

    }

    public boolean validarPermissoes(String controller, String funcionalidade){
        boolean autorizado = false;
        Optional<Funcionalidades> funcionalidadesResult = funcionalidadesRespository.findByControllerAndFuncionalidade(controller, funcionalidade);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        if(!roles.isEmpty() && roles.stream().filter(row -> row.getAuthority().equals("ADMIN")) != null){
            return true;
        }

        if(!funcionalidadesResult.isPresent()) {

            List<FuncionalidadesRoles> permissoesFuncionalidade = funcionalidadesResult.get().getRoles();

            for(GrantedAuthority role : roles){
                if(permissoesFuncionalidade.stream().filter(row -> row.getRole().getPermissao().equals(role.getAuthority())) != null){
                    autorizado = true;
                }
            }
        }

        return autorizado;
    }
}
