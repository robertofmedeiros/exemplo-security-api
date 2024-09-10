package br.com.senac.api.utils.anotacoes.controllerHeader;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerHeaderAspect {
    @Around("@annotation(ControllerHeader)")
    public void validacaoController() throws Throwable {
        System.out.println("Teste");
    }
}
