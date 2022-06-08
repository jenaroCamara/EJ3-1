package com.example.jpadto.student.infraestructure.dto.output.Student;

import com.example.jpadto.alumnos_estudios.Alumnos_Estudios;
import com.example.jpadto.profesor.domain.Profesor;
import com.example.jpadto.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class OutputDTOStudentFull extends OutputDTOStudent {
    private Persona persona;
    private int num_hours_week;
    private Profesor profesor;
    String branch;
    List<Alumnos_Estudios> asignaturas;
}
