package com.example.vinos_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vinos_backend.entities.Presentacion;
import com.example.vinos_backend.helpers.Helpers;
import com.example.vinos_backend.models.Response;
import com.example.vinos_backend.repositories.PresentacionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/presentaciones")
@CrossOrigin(origins = "*")
public class PresentacionController {
    
    @Autowired
    private PresentacionRepository presentacionRepository;

    @Autowired
    private Helpers helpers;

    @GetMapping()
    public ResponseEntity<?> listarPresentacion() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Presentacion> presentacion = presentacionRepository.findAll();
            response = helpers.apiResponse(new Response<>(true, "Lista de presentaciones", presentacion));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al consultar todas las presentaciones");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPresentacionId(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Presentacion presentacionFound = presentacionRepository.findById(id).orElse(null);

            if (presentacionFound==null) {
                response = helpers.apiResponse(new Response<>(false, "No se encontro la presentacion", ""));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response = helpers.apiResponse(new Response<>(true, "Presentacion encontrada", presentacionFound));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al encontrar la presentacion por ID");
        }
    }

    @PostMapping()
    public ResponseEntity<?> crearPresentacion(@Valid @RequestBody Presentacion presentacion, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()) {
            return helpers.validarDatos(result);
        }
        try {
            presentacionRepository.save(presentacion);
            response = helpers.apiResponse(new Response<>(true, "Se creo una presentacion nueva", presentacion));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al crear la presentacion");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPresentacionId(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Presentacion presentacionFound = presentacionRepository.findById(id).orElse(null);

            if (presentacionFound==null) {
                response = helpers.apiResponse(new Response<>(false, "No se encontro la presentacion", ""));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            presentacionRepository.delete(presentacionFound);
            response = helpers.apiResponse(new Response<>(true, "Presentacion eliminada", ""));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al eliminar la presentacion por ID");
        }
    }

}
