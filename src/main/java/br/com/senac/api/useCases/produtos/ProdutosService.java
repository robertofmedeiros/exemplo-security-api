package br.com.senac.api.useCases.produtos;

import br.com.senac.api.entitys.Produtos;
import br.com.senac.api.useCases.produtos.domains.ProdutosRequestQueryDom;
import br.com.senac.api.useCases.produtos.domains.ProdutosResponseDom;
import br.com.senac.api.useCases.produtos.implement.ProdutosRepository;
import br.com.senac.api.utils.paginacao.Paginacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public List<ProdutosResponseDom> carregarTodosProdutos(){
        List<Produtos> produtosResult = produtosRepository.findAll();

        if(!produtosResult.isEmpty()){
            List<ProdutosResponseDom> produtosResponse = produtosResult.stream().map(row -> new ProdutosResponseDom(row.getId(), row.getNome(), row.getDescricao())).toList();

            return produtosResponse;
        }

        return null;
    }

    public Paginacao<List<ProdutosResponseDom>> carregarTodosProdutos(ProdutosRequestQueryDom pagina){
        PageRequest pageRequest = PageRequest.of(pagina.getPagina(), pagina.getTamanho(), Sort.Direction.valueOf("ASC"), pagina.getOrderBy());

        Page<Produtos> produtosResult = produtosRepository.findAll(pageRequest);

        if(!produtosResult.isEmpty()){
            List<ProdutosResponseDom> produtosResponse = produtosResult.stream().map(row -> new ProdutosResponseDom(row.getId(), row.getNome(), row.getDescricao())).toList();

            Paginacao<List<ProdutosResponseDom>> paginaResult = new Paginacao();
            paginaResult.setPagina(produtosResult.getNumber());
            paginaResult.setData(produtosResponse);
            paginaResult.setTamanho(produtosResult.getSize());
            paginaResult.setTotal(produtosResult.getTotalPages());

            return paginaResult;
        }

        return null;
    }
}
