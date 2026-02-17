package com.cr12.primeraweb.controller;

import com.cr12.primeraweb.controller.SaludoController;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    //Tabla de la base de datos real
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Logica para buscar por nombre
    public Usuario buscarPorNombre(String nombre){
        return usuarioRepository.findByNombreIgnoreCase(nombre).orElse(null);
    }

    // Eliminar un usuario de la tabla
    @Transactional //Para operaciones de borrado personal
    public boolean eliminar(String nombre){
        if (usuarioRepository.findByNombreIgnoreCase(nombre).isPresent()) {
            usuarioRepository.deleteByNombreIgnoreCase(nombre);
            return true;
        }
        return true;
    }


    // Se guardan todos los datos en la tabla como forma de servicio
    public Usuario guardar(Usuario usuario) {

        //Se guarda en la lista
        return usuarioRepository.save(usuario);
    }

    // Se obtienen todos los datos de la tabla
    public List<UsuarioDTO> obtenerTodos() {
        List<Usuario> usuarios =  usuarioRepository.findAll();

        //Para combertirlo en una Array
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuariosDTO.add(
                    new UsuarioDTO(
                            usuario.getId(),
                            usuario.getNombre(),
                            usuario.getDomicilio()
                    )
            );
        }

        return usuariosDTO;
    }

    //Metodo para la logica de la actualizacion
    public Usuario actualizar(String nombreABuscar, Usuario nuevosDatos) {
        //Buscar el usuario que queremos en la base de datos
        return usuarioRepository.findByNombreIgnoreCase(nombreABuscar)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombre(nuevosDatos.getNombre());
                    usuarioExistente.setEmail(nuevosDatos.getEmail());

                    //Se guardan los cambios
                    return usuarioRepository.save(usuarioExistente);
                })
                .orElse(null);
    }
}
