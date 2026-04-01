package com.example.vinos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vinos_backend.entities.Presentacion;

public interface PresentacionRepository extends JpaRepository<Long, Presentacion>{
    
}
