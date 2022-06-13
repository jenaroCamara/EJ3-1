package com.example.jpadto.alumnos_estudios.application.port;

import com.example.jpadto.alumnos_estudios.infraestructure.DTO.input.InputDTOAlumnos_estudios;
import com.example.jpadto.alumnos_estudios.infraestructure.DTO.output.OutputDTOAlumnos_estudios;
import com.example.jpadto.topic.infraestructure.dto.input.inputDTOtopic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface alumnos_estudiosService {
    public OutputDTOAlumnos_estudios anadeAE(InputDTOAlumnos_estudios dto);
    public OutputDTOAlumnos_estudios anadeTopic( String id, inputDTOtopic topic)  throws Exception;
}
