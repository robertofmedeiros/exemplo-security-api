package br.com.senac.api.useCases.usuarios.implement;

import br.com.senac.api.entitys.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRolesRepository extends JpaRepository<Roles, Long> {
    List<Roles> findByPermissaoIn(List<String> permissoes);
}
