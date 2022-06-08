package com.example.jpadto.application;

import com.example.jpadto.profesor.infraestructure.dto.input.InputDTOProfesor;
import com.example.jpadto.student.infraestructure.dto.input.InputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.input.InputDTOPersona;
import com.example.jpadto.profesor.infraestructure.dto.output.OutputDTOProfesor;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudent;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudentFull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UsuarioServicioInterface {
    public OutputDTOPersona guardar(InputDTOPersona persona) throws Exception;
    public OutputDTOProfesor guardarProfesor(InputDTOProfesor profesor) throws Exception;
    public List<OutputDTOPersona> getUsuarios();
    public List<OutputDTOPersona> getUsuariosByName(@PathVariable String nombre);
    public InputDTOPersona getUserById(String id) throws Exception ;
    public InputDTOPersona actualiza(@RequestBody InputDTOPersona usuario) throws Exception;
    public void deleteById(String id) throws Exception;
    public OutputDTOStudent guardarEstudiante(InputDTOStudent dtoStudent) throws Exception;
    public OutputDTOStudent getEstudiante(String id) throws Exception;
}
