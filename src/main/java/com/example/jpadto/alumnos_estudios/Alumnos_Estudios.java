package com.example.jpadto.alumnos_estudios;

import com.example.jpadto.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "estudiante_asignatura")
@Getter
@Setter
public class Alumnos_Estudios {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id_study;
    @ManyToOne(cascade = CascadeType.ALL)//funciona
    @JoinColumn(name = "id_student")
    Student student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comment;
    @Column(name = "initial_date")
    Date initial_date;
    @Column(name = "finish_date")
    Date finish_date;
}