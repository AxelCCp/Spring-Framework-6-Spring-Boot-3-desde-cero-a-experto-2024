package com.andres.curso.springboot.jpa.springbootjparelationship.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    // @JoinColumn(name = "client_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true/*, fetch = FetchType.EAGER*/)        //Eager : para evitar el error en removeAddress(),  donde se hacen 2 consultas. Esto no se recomienda mucho ya que va a hacer una consulta por cada direccion q tenga el cliente.
    @JoinTable(
        name = "tbl_clientes_to_direcciones", 
        joinColumns = @JoinColumn(name= "id_cliente"),
        inverseJoinColumns = @JoinColumn(name="id_direcciones"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"}))
    private List<Address> addresses;

    public Client() {
        addresses = new ArrayList<>();
    }

    public Client(String name, String lastname) {
        this();
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", lastname=" + lastname +
                ", addresses=" + addresses + "}";
    }

}
