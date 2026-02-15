package com.cr12.primeraweb.pedidos;

import com.cr12.primeraweb.controller.Usuario;
import jakarta.persistence.*;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pedido() {}

    // getters

    public Long getId() {
        return id;
    }

    public String getProducto() {
        return producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
