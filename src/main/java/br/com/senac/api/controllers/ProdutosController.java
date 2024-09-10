package br.com.senac.api.controllers;

import br.com.senac.api.useCases.produtos.ProdutosService;
import br.com.senac.api.useCases.produtos.domains.ProdutosRequestQueryDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @GetMapping("/carregar")
    @PreAuthorize("@funcionalidadeService.validarPermissoes('ProdutosController', 'listagem')")
    public ResponseEntity<?> carregarProdutos(ProdutosRequestQueryDom paginacao) {
        try {

            if(paginacao != null){
                return ResponseEntity.ok(produtosService.carregarTodosProdutos(paginacao));
            }

            return ResponseEntity.ok(produtosService.carregarTodosProdutos());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
