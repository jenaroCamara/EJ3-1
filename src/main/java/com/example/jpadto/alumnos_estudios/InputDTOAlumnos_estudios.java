package com.example.jpadto.alumnos_estudios;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class InputDTOAlumnos_estudios {
    @NotNull @NotBlank
    int id_study;
    @NotNull @NotBlank
    String student;
    @NotNull @NotBlank
    String asignatura;
    @NotNull @NotBlank
    String comment;
    Date initial_date;
    Date finish_date;
}
