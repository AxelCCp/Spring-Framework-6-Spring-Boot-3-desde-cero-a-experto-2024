package com.andres.curso.springboot.app.springbootcrud.entities;

import java.util.ArrayList;
import java.util.List;

import com.andres.curso.springboot.app.springbootcrud.validation.ExistByUsername;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExistByUsername
    @Column(unique = true)
    @NotBlank
    @Size(min=4, max=12)
    private String username;
    
    @NotBlank
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)                                //es es para que excluya este campo en el json, cuando se consulta con el get desde el postman.
    private String password;

    @Transient//este campo no va en la tabla.
    private boolean admin;

    private boolean enabled;

    @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})                                                      //"users":para evitar loop entre las listas de roles en User y users en Role. //"handler", "hibernateLazyInitializer" : al consultar una lista se genera un proxy y este proxy tiene atributos basura y con esto se eliminan.
    @ManyToMany
    @JoinTable(name="users_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})  //se mapean las foreign key de la tabla intermedia.
    private List<Role>roles;

    @PrePersist
    public void prePersist(){
        this.enabled = true;
    }

    public User() {
        this.roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    
    

}
