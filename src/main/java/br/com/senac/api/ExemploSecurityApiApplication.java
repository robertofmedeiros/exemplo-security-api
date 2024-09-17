package br.com.senac.api;

import br.com.senac.api.schedulers.TesteTriggerBuild;
import br.com.senac.api.useCases.funcionalidades.FuncionalidadeService;
import br.com.senac.api.useCases.roles.RolesService;
import jakarta.annotation.PostConstruct;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExemploSecurityApiApplication {
	@Autowired
	private FuncionalidadeService funcionalidadeService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private TesteTriggerBuild testeTriggerBuild;

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
