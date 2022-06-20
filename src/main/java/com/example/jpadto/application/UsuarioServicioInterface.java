package com.example.jpadto.application;

import com.example.jpadto.alumnos_estudios.infraestructure.DTO.output.OutputDTOAlumnos_estudios;
import com.example.jpadto.persona.domain.Persona;
import com.example.jpadto.persona.infraestructure.dto.output.outputDTOpersonafull;
import com.example.jpadto.profesor.infraestructure.dto.input.InputDTOProfesor;
import com.example.jpadto.student.infraestructure.dto.input.InputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.input.InputDTOPersona;
import com.example.jpadto.profesor.infraestructure.dto.output.OutputDTOProfesor;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudentFull;
import com.example.jpadto.topic.infraestructure.dto.output.outputDTOtopic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UsuarioServicioInterface {
    public OutputDTOPersona guardar(InputDTOPersona persona) throws Exception;
    public OutputDTOProfesor guardarProfesor(InputDTOProfesor profesor) throws Exception;
    public List<Object> getUsuarios(String queryParam);
    public List<OutputDTOPersona> getUsuariosByNameCriteria(String nombre);
    public List<Object> getUsuariosByName(@PathVariable String nombre, String queryParam)throws Exception;
    public outputDTOpersonafull getUserById(String id) throws Exception;
    public InputDTOPersona actualiza(@RequestBody InputDTOPersona usuario) throws Exception;
    public void deleteById(String id) throws Exception;
    public OutputDTOStudent guardarEstudiante(InputDTOStudent dtoStudent) throws Exception;
    public OutputDTOStudent getEstudiante(String id) throws Exception;
    public OutputDTOStudentFull actualizaTopics(@PathVariable String id)throws Exception;
    public List<OutputDTOAlumnos_estudios> getAsignaturas(String id) throws Exception;
    public OutputDTOAlumnos_estudios eliminaTopic(String id, String id_topic) throws Exception;
}
