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

import com.example.vinos_backend.entities.Producto;
import com.example.vinos_backend.helpers.Helpers;
import com.example.vinos_backend.models.Response;
import com.example.vinos_backend.repositories.ProductoRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private Helpers helpers;

    @GetMapping()
    public ResponseEntity<?> listarProductos() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Producto> productos=productoRepository.findAll();
            response = helpers.apiResponse(new Response<>(true, "Lista de productos", productos));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al consultar todos los productos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarProductoId(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto productoFound = productoRepository.findById(id).orElse(null);

            if (productoFound==null) {
                response = helpers.apiResponse(new Response<>(false, "No se encontro el producto", ""));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response = helpers.apiResponse(new Response<>(true, "Producto encontrado", productoFound));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al buscar el producto por el ID");
        }
    }

    @PostMapping()
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            return helpers.validarDatos(result);
        }
        try {
            
            productoRepository.save(producto);
            response=helpers.apiResponse(new Response<>(true, "Producto creado", producto));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al crear el producto");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProductoId(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto productoFound = productoRepository.findById(id).orElse(null);

            if (productoFound==null) {
                response = helpers.apiResponse(new Response<>(false, "No se encontro el producto", ""));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            productoRepository.delete(productoFound);

            response = helpers.apiResponse(new Response<>(true, "Producto elimado", ""));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            return helpers.catchErrors(e, "Error al eliminar el producto por ID");
        }
    }
    
}
