package com.example.vinos_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo codigo es obligatorio")
    @Size(min = 4, max = 20)
    @Column(name= "codigo")
    private String codigo;

    @NotNull(message = "El campo nombre es obligatorio")
    @Size(min = 4, max = 100)
    @Column(name = "nombre")
    private String nombre;

    @NotNull(message = "El campo descripcion es obligatorio")
    @Size(min = 4, max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "El campo categoria es obligatorio")
    @Size(min = 4, max = 50)
    @Column(name = "categoria")
    private String categoria;

    @NotNull(message = "El campo precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a cero")
    @Column(name = "precio")
    private Double precio;

    @PositiveOrZero(message = "El stock debe ser mayor o igual a cero")
    @Column(name = "stock", columnDefinition = "integer default 0")
    private Integer stock;

    @Column(name = "disponible", nullable = false, columnDefinition = "boolean default true")
    private Boolean disponible;

    @Column(name = "fecha_registro", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

}
