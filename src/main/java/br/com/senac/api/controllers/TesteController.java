package br.com.senac.api.controllers;

import br.com.senac.api.utils.anotacoes.controllerHeader.ControllerHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/teste")
@ControllerHeader
public class TesteController {

    @PreAuthorize("@rolesService.hasAnyRole('TESTE')")
    //@PreAuthorize("hasAnyRole('TESTE', 'ADMIN')")
    @GetMapping("/ola")
    public ResponseEntity<String> testeWs(){
        return ResponseEntity.ok("ok");
    }
}
