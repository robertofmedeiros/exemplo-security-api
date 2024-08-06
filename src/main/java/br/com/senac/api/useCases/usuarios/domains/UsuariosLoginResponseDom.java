package br.com.senac.api.useCases.usuarios.domains;

public class UsuariosLoginResponseDom {

    private Long id;
    private String login;
    private String token;

    public UsuariosLoginResponseDom(Long id, String login, String token) {
        this.id = id;
        this.login = login;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
