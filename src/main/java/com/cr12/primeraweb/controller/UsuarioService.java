package com.cr12.primeraweb.controller;

import com.cr12.primeraweb.controller.SaludoController;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    //Tabla de base de datos temporal
    private List<Usuario> listaDeUsuarios = new ArrayList<>();

    //Contador manual para la asignacion de ID's
    private Long ultimoId = 0L;

    // Logica para buscar por nombre
    public Usuario buscarPorNombre(String nombre){
        //Bucle para buscar dentro de la tabla de la base de datos Usuario
        for (Usuario u : listaDeUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)){
                return u;
            }
        }
        return null;
    }

    // Eliminar un usuario de la tabla
    public boolean eliminar(String nombre){
        return listaDeUsuarios.removeIf(u -> u.getNombre().equalsIgnoreCase(nombre));
    }


    // Se guardan todos los datos en la tabla como forma de servicio
    public void guardar(Usuario usuario) {
        //Se aumenta el contador de ID's
        ultimoId++;

        //Asignamos al usuario el ID que se ha generado
        usuario.setId(ultimoId);

        //Se guarda en la lista
        listaDeUsuarios.add(usuario);
    }

    // Se obtienen todos los datos de la tabla
    public List<Usuario> obtenerTodos() {
        return listaDeUsuarios;
    }
}
