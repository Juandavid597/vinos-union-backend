package com.example.vinos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vinos_backend.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
