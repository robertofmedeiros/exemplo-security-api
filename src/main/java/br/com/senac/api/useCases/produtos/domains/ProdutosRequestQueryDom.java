package br.com.senac.api.useCases.produtos.domains;

public class ProdutosRequestQueryDom {
    private int pagina = 0;
    private int tamanho = 50;
    private String orderBy = "id";

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina - 1 < 0 ? 0 : pagina - 1;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
