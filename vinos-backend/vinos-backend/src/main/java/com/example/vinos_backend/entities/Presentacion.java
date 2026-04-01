package com.example.vinos_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "presentacion")
public class Presentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo es obligatorio")
    @Size(max = 100, message = "El tipo no puede superar 100 caracteres")
    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @NotNull(message = "El contenido en ml es obligatorio")
    @Positive(message = "El contenido debe ser mayor a cero")
    @Column(name = "contenido_ml", nullable = false, columnDefinition = "integer default 750")
    private Integer contenidoMl = 750;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

}
