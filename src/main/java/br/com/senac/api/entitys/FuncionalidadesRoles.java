package br.com.senac.api.entitys;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(name = "idx_funcionalidades_roles_001", columnList = "funcionalidade_id, role_id", unique = true))
public class FuncionalidadesRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionalidade_id", nullable = false)
    private Funcionalidades funcionalidade;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionalidades getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(Funcionalidades funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
