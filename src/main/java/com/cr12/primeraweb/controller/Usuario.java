package com.cr12.primeraweb.controller;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity                     // Esto sirve para indicar que la clase es
                            // una tabla para una base de datos
@Table(name = "usuarios")   //Nombre de la tabla SQL
public class Usuario {

    @Id //Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    private String email;

    private String password;


    public String getPassword(){ return password;}

    public void setPassword(String password){ this.password = password;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
