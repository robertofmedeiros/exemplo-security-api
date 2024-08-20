package br.com.senac.api.useCases.usuarios.domains;

import java.util.List;

public class UsuariosLoginRequestDom {
    private String login;

    private String senha;

    private List<String> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
