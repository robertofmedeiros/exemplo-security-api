package br.com.senac.api.useCases.funcionalidadesRoles.implement;

import br.com.senac.api.entitys.FuncionalidadesRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionalidadesRolesRepository extends JpaRepository<FuncionalidadesRoles, Long> {
}
