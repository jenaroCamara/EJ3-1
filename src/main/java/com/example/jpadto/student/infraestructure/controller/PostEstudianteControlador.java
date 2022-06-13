package com.example.jpadto.student.infraestructure.controller;


import com.example.jpadto.alumnos_estudios.infraestructure.DTO.output.OutputDTOAlumnos_estudios;
import com.example.jpadto.student.infraestructure.dto.input.InputDTOStudent;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudent;
import com.example.jpadto.application.UsuarioServicioInterface;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudentFull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Estudiante")
public class PostEstudianteControlador {
    @Autowired
    private UsuarioServicioInterface usuarioServicio;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/anadirEstudiante")
    public ResponseEntity<OutputDTOStudent> anadirProfesor(@RequestBody InputDTOStudent dtoStudent) throws Exception {
        OutputDTOStudent estudiante = usuarioServicio.guardarEstudiante(dtoStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiante);
    }

    @PutMapping("/actualiza/{id}") //vamos a añadir todos los topics que hay a un estudiante
    public ResponseEntity<OutputDTOStudentFull> actualizaTopics(@PathVariable String id)throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.actualizaTopics(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OutputDTOAlumnos_estudios> eliminaTopic(@PathVariable String id, @PathVariable String id_topic) throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.eliminaTopic(id, id_topic));
    }

}
