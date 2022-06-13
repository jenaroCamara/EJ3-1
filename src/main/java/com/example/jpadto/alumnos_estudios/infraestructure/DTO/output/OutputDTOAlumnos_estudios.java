package com.example.jpadto.alumnos_estudios.infraestructure.DTO.output;

import com.example.jpadto.student.domain.Student;
import com.example.jpadto.topic.infraestructure.dto.input.inputDTOtopic;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OutputDTOAlumnos_estudios {
    String id_study;
    inputDTOtopic id_topic;
    String comment;
    Date initial_date;
    Date finish_date;
}
