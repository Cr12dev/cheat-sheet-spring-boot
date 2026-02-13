package com.cr12.primeraweb.controller;

import com.cr12.primeraweb.controller.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class SaludoController {

    @Autowired
    private UsuarioService usuarioService;

//    @GetMapping("/hola")
//    public String holamundo () {
//        return "Hola Mundo";
//    }
//
//    @GetMapping("/holanombre/{nombre}/{edad}")
//    public String holaMundoNombre (@PathVariable String nombre,
//                                   @PathVariable int edad) {
//        return "Hola Mundo!" + nombre + edad;
//    }

    //Post | CREAR NUEVO USUARIO
    @PostMapping
    public String crearUsuario(@RequestBody Usuario usuario) {
        // Añadir usuario a la base de datos temporal que se ha creado antes:
        usuarioService.guardar(usuario);

        return "Usuario " + usuario.getNombre() + " creado con exito.";
    }

    //GET | Listar a todos los usuarios
    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodos();
    }

    //GET | Listar a los usuarios por ID
    @GetMapping("/{nombre}")
    public Usuario obtenerUsuarioPorId(@PathVariable String nombre){
        return usuarioService.buscarPorNombre(nombre);
    }

    //UP
    @PutMapping("/{nombreABuscar}")
    public String actualizarUsuario(@PathVariable String nombreABuscar, @RequestBody Usuario usuarioNuevosDatos){

        //Bucle para buscar usuarios en la lista de usuarios (en la base de datos)
        for (Usuario usuario : usuarioService.obtenerTodos()) {
            //Buscar al usuario en la lista por su nombre
            if (usuario.getNombre().equalsIgnoreCase(nombreABuscar)) {
                //Se cambian los datos del body de momento solo el nombre
                usuario.setNombre(usuarioNuevosDatos.getNombre());

                return "Usuario " + nombreABuscar + " actualizado a" + usuarioNuevosDatos;
            }
        }

        return "No se encontro el usuario con nombre: " + nombreABuscar;
    }

    //DEL | Eliminar un usuario de la tabla
    @DeleteMapping("/{nombre}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String nombre) {
        boolean borrado = usuarioService.eliminar(nombre);

        if (borrado) {
            return ResponseEntity.ok("Usuario " + nombre + " eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("No se pudo eliminar: el usuario " + nombre + " no existe.");
        }
    }
}
