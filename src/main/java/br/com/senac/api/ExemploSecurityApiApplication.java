package br.com.senac.api;

import br.com.senac.api.useCases.funcionalidades.FuncionalidadeService;
import br.com.senac.api.useCases.roles.RolesService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExemploSecurityApiApplication {
	@Autowired
	private FuncionalidadeService funcionalidadeService;

	@Autowired
	private RolesService rolesService;

	public static void main(String[] args) {
		SpringApplication.run(ExemploSecurityApiApplication.class, args);
	}

	@PostConstruct
	private void criarFuncionalidade(){

		/* --- Criando Roles - Inicio --- */
		rolesService.criarRoles("ADMIN", "Administrador");

		/* --- Criando Roles - Final --- */

		/* --- Criando Funcionalidades - Inicio --- */
		funcionalidadeService.criarFuncionalidade("TesteController", "listagem");
		funcionalidadeService.criarFuncionalidade("UsuariosController", "manutencao");

		/* --- Criando Funcionalidades - Final --- */
	}

}
