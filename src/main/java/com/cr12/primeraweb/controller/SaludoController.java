package com.cr12.primeraweb.controller;

import com.cr12.primeraweb.controller.Usuario;

import com.cr12.primeraweb.pedidos.Pedido;
import com.cr12.primeraweb.pedidos.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/usuarios")
public class SaludoController {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PedidoService pedidoService;

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


    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        // 1. Verificación básica de seguridad
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio");
        } else if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("La contraseña es obligatoria, tranquil@ no se hará publica");
        }


        // 2. Guardar en la base de datos real a través del servicio
        usuarioService.guardar(usuario);

        // 3. Devolver una respuesta HTTP 201 (Created)
        return ResponseEntity.status(200)
                .body("Usuario " + usuario.getNombre() + " creado con éxito.");
    }

    //GET | Listar a todos los usuarios
    @GetMapping
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodos();
    }

    //GET | Listar a los usuarios por ID
    @GetMapping("/{nombre}")
    public Usuario obtenerUsuarioPorId(@PathVariable String nombre){
        return usuarioService.buscarPorNombre(nombre);
    }

    //UP
    @PutMapping("/{nombreABuscar}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable String nombreABuscar, @RequestBody Usuario usuarioNuevosDatos) {

        Usuario actualizado = usuarioService.actualizar(nombreABuscar, usuarioNuevosDatos);

        if (actualizado != null) {
            return ResponseEntity.ok("Usuario " + nombreABuscar + " actualizado con éxito a: " + actualizado.getNombre());
        } else {
            return ResponseEntity.status(404)
                    .body("No se encontró el usuario con nombre: " + nombreABuscar);
        }
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
