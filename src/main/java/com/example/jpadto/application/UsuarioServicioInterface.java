package com.example.jpadto.application;

import com.example.jpadto.alumnos_estudios.infraestructure.DTO.output.OutputDTOAlumnos_estudios;
import com.example.jpadto.persona.domain.Persona;
import com.example.jpadto.persona.infraestructure.dto.output.outputDTOpersonafull;
import com.example.jpadto.student.infraestructure.dto.input.InputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.input.InputDTOPersona;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudentFull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public interface UsuarioServicioInterface {
    public OutputDTOPersona guardar(InputDTOPersona persona) throws Exception;
    public List<Object> getUsuarios(String queryParam);
    public List<OutputDTOPersona> getUsuariosByNameCriteria(String nombre, String fecha);
    public List<Object> getUsuariosByName(@PathVariable String nombre, String queryParam)throws Exception;
    public outputDTOpersonafull getUserById(String id) throws Exception;
    public InputDTOPersona actualiza(@RequestBody InputDTOPersona usuario) throws Exception;
    public void deleteById(String id) throws Exception;
}
