package br.com.senac.api.entitys;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE funcionalidades SET deleted_at = now() WHERE id=?")
@SQLRestriction("deleted_at is null")
public class Funcionalidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String controller;

    @Column(nullable = false)
    private String funcionalidade;

    @Column
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "funcionalidade")
    private List<FuncionalidadesRoles> roles;

    public Funcionalidades() {
    }

    public Funcionalidades(String controller, String funcionalidade) {
        this.controller = controller;
        this.funcionalidade = funcionalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public List<FuncionalidadesRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<FuncionalidadesRoles> roles) {
        this.roles = roles;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
