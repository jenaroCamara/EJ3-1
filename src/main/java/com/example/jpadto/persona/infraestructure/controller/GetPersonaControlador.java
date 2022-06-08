package com.example.jpadto.persona.infraestructure.controller;

import com.example.jpadto.persona.infraestructure.dto.input.InputDTOPersona;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.application.UsuarioServicioInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Get")
public class GetPersonaControlador {

    @Autowired
    private UsuarioServicioInterface usuarioServicio;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getUsuario/{id}")
    public ResponseEntity<InputDTOPersona> getById(@PathVariable(value = "id") String id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServicio.getUserById(id));
    }

    @GetMapping("/getUsuarios/{nombre}")
    public List<OutputDTOPersona> getUsuariosByName(@PathVariable String nombre) {
        return usuarioServicio.getUsuariosByName(nombre);
    }

    @GetMapping("/getUsuarios")
    public List<OutputDTOPersona> getUsuarios() {
        return usuarioServicio.getUsuarios();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
