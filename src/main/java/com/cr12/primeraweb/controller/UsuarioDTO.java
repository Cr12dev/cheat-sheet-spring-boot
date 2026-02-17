package com.cr12.primeraweb.controller;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String domicilio;

    public UsuarioDTO(Long id, String nombre, String domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String nombre) {
        this.domicilio = domicilio;
    }


}
