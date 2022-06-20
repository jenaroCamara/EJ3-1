package com.example.jpadto.student.application.port;

import com.example.jpadto.alumnos_estudios.infraestructure.DTO.output.OutputDTOAlumnos_estudios;
import com.example.jpadto.student.infraestructure.dto.input.InputDTOStudent;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudent;
import com.example.jpadto.student.infraestructure.dto.output.Student.OutputDTOStudentFull;
import java.util.List;

public interface EstudianteService {
    public OutputDTOStudent guardarEstudiante(InputDTOStudent dtoStudent);
    public OutputDTOStudentFull getEstudiante(String id);
    public OutputDTOStudentFull actualizaTopics(String id) throws Exception;
    public List<OutputDTOAlumnos_estudios> getAsignaturas(String id) throws Exception;
    public OutputDTOAlumnos_estudios eliminaTopic(String id, String id_topic) throws Exception;
}
