package br.com.fiap.authentication.model;


import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Mapeia um conjunto de permissões que uma pessoa
 * pode ter nos diversos sistemas da empresa
 */
@Entity
@Table(name = "TB_COD", uniqueConstraints = {
        @UniqueConstraint(name = "UK_COD_ID", columnNames = "NR_ID")
    })
public class Profile {
@Id

@Column(name = "NR_ID", nullable = false)
    private Long id;
    private String nome;
    private Set<Role> roles = new LinkedHashSet<>();

    public Profile() {
    }

    public Profile(Long id, String nome, Set<Role> roles) {
        this.id = id;
        this.nome = nome;
        this.roles = roles;
    }

    @ManyToMany
    public Profile addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public Profile removeRole(Role role) {
        this.roles.remove(role);
        return this;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(this.roles);
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Profile setNome(String nome) {
        this.nome = nome;
        return this;
    }


    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", roles=" + roles +
                '}';
    }
}
