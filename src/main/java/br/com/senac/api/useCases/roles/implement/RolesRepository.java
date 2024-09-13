package br.com.senac.api.useCases.roles.implement;

import br.com.senac.api.entitys.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByPermissao(String permissao);

}
