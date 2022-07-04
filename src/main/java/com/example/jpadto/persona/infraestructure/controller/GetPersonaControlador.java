package com.example.jpadto.persona.infraestructure.controller;

import com.example.jpadto.persona.domain.Persona;
import com.example.jpadto.persona.infraestructure.dto.input.InputDTOPersona;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.application.UsuarioServicioInterface;
import com.example.jpadto.persona.infraestructure.dto.output.outputDTOpersonafull;
import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.beans.PersistenceDelegate;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Get")
public class GetPersonaControlador {

    @Autowired
    private UsuarioServicioInterface usuarioServicio;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getUsuario/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") String id, @RequestParam String queryParam) throws Exception {
        outputDTOpersonafull outDTOuser = usuarioServicio.getUserById(id);
        if (!queryParam.equals("full")){
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(outDTOuser, OutputDTOPersona.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(outDTOuser);
    }

    @GetMapping("/getUsuarios/{nombre}")
    public List<Object> getUsuariosByName(@PathVariable String nombre, @RequestParam String queryParam) throws Exception {
        List<Object> listaUsuarios = usuarioServicio.getUsuariosByName(nombre, queryParam);
        return listaUsuarios;
    }

    @GetMapping("/getUsuarios2/{nombre}/{fech}")
    public List<OutputDTOPersona> getUsuariosByName2(@PathVariable String nombre, @PathVariable String fech) throws Exception {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date fecha = formatter.parse(fech);
        List<OutputDTOPersona> listaUsuarios = usuarioServicio.getUsuariosByNameCriteria(nombre, fech);
        return listaUsuarios;
    }

    @GetMapping("/getUsuarios")
    public ResponseEntity<List<Object>> getUsuarios(@RequestParam String queryParam) {
        return ResponseEntity.ok().body(usuarioServicio.getUsuarios(queryParam));
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
