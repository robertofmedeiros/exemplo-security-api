package br.com.senac.api.controllers;

import br.com.senac.api.useCases.usuarios.UsuariosService;
import br.com.senac.api.useCases.usuarios.domains.UsuariosLoginRequestDom;
import br.com.senac.api.useCases.usuarios.domains.UsuariosLoginResponseDom;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuariosLoginRequestDom usuario){
        try {
            UsuariosLoginResponseDom response = usuarioService.loginUsuario(usuario);
            return ResponseEntity.ok().body(response);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseUtil.responseMap(ex.getMessage()));
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastroUsuario(@RequestBody UsuariosLoginRequestDom usuario){
        try {
            UsuariosLoginResponseDom response = usuarioService.cadastroUsuario(usuario);
            return ResponseEntity.status(201).body(response);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap(ex.getMessage()));
        }
    }
}
