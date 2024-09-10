package br.com.senac.api.useCases.funcionalidades.implement;

import br.com.senac.api.entitys.Funcionalidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionalidadesRespository extends JpaRepository<Funcionalidades, Long> {
    Optional<Funcionalidades> findByControllerAndFuncionalidade(String controller, String funcionalidade);
}
