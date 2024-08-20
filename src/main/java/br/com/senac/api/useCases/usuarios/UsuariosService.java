package br.com.senac.api.useCases.usuarios;

import br.com.senac.api.entitys.Roles;
import br.com.senac.api.entitys.Usuarios;
import br.com.senac.api.jwt.TokenService;
import br.com.senac.api.useCases.usuarios.domains.UsuariosLoginRequestDom;
import br.com.senac.api.useCases.usuarios.domains.UsuariosLoginResponseDom;
import br.com.senac.api.useCases.usuarios.implement.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public UsuariosLoginResponseDom cadastroUsuario(UsuariosLoginRequestDom usuario) throws Exception {

        Usuarios usuarioEntidade = new Usuarios();

        usuarioEntidade.setLogin(usuario.getLogin());
        usuarioEntidade.setSenha(passwordEncoder.encode(usuario.getSenha()));

        List<Roles> roles = new ArrayList<>();

        for(String role : usuario.getRoles()) {
            Roles rolePersist = new Roles();
            rolePersist.setPermissao(role);

            roles.add(rolePersist);
        }

        usuarioEntidade.setRoles(roles);

        Usuarios responseUsuario = usuarioRepository.save(usuarioEntidade);

        String token = tokenService.gerarToken(usuarioEntidade);
        return new UsuariosLoginResponseDom(responseUsuario.getId(),responseUsuario.getLogin(),token);
    }

    public UsuariosLoginResponseDom loginUsuario(UsuariosLoginRequestDom usuario) throws Exception {
        Optional<Usuarios> resultUsuario = usuarioRepository.findByLogin(usuario.getLogin());

        if(resultUsuario.isEmpty()){
            throw new Exception("Login inválido!");
        }

        Usuarios response = resultUsuario.get();

        if(!passwordEncoder.matches(usuario.getSenha(),resultUsuario.get().getSenha())){
            throw new Exception("Senha inválida!");
        }

        String token = tokenService.gerarToken(response);

        return new UsuariosLoginResponseDom(response.getId(),response.getLogin(),token);
    }
}
