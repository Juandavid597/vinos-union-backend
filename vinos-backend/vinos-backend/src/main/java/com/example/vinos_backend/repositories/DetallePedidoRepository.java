package com.example.vinos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vinos_backend.entities.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<Long, DetallePedido>{
    
}
