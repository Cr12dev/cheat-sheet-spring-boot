package com.cr12.primeraweb.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Spring genera la consulta SQL automaticamente
    Optional<Usuario> findByNombreIgnoreCase(String nombre);

    // Para borrar por nombres
    void deleteByNombreIgnoreCase(String nombre);
}
