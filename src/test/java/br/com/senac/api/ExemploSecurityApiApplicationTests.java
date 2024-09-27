package br.com.senac.api;

import br.com.senac.api.services.email.EmailService;
import br.com.senac.api.useCases.usuarios.UsuariosService;
import br.com.senac.api.useCases.usuarios.domains.UsuariosLoginRequestDom;
import br.com.senac.api.useCases.usuarios.domains.UsuariosLoginResponseDom;
import br.com.senac.api.useCases.usuarios.implement.UsuariosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

@SpringBootTest
@ActiveProfiles("test")
class ExemploSecurityApiApplicationTests {

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private EmailService emailService;

	@Test
	@DisplayName("Caso de teste - Usuário (Cadastro)")
	void testeUsuarioCadastro() throws Exception {

		UsuariosLoginRequestDom usuario = new UsuariosLoginRequestDom();
		usuario.setLogin("teste");
		usuario.setSenha("1234");
		usuario.setRoles(Collections.singletonList("ADMIN").stream().toList());

		UsuariosLoginResponseDom usuarioResult = usuariosService.cadastroUsuario(usuario);

		assertAll(
				"Teste de usuario - Cadastro",
				() -> assertNotNull(usuarioResult.getId(), "Id do Usuario não foi gerado"),
				() -> assertEquals(usuarioResult.getLogin(), "teste", "Nome do usuario não confere com o que foi enviado"),
				() -> assertNotNull(usuarioResult.getToken(), "Token não foi gerado"));
	}

	@Test
	@DisplayName("Caso de teste - Usuário (Login)")
	void testeUsuarioLogin() throws Exception {

		UsuariosLoginRequestDom usuario = new UsuariosLoginRequestDom();
		usuario.setLogin("teste");
		usuario.setSenha("1234");

		UsuariosLoginResponseDom usuarioResult = usuariosService.loginUsuario(usuario);

		assertAll(
				"Teste de usuario - Login",
				() -> assertNotNull(usuarioResult.getToken(), "Token não foi gerado"));
	}

	@Test
	@DisplayName("Caso de teste - Email")
	void testeEnvioEmail() {
		emailService.sendMail("roberto.medeiros@prof.sc.senac.br", "Teste Email", "teste");
	}

}
