package com.example.jpadto.profesor.infraestructure.dto.output;

import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class outputDTOprofesorNC {
    private String id_profesor;
    @NotNull
    private String coments;
    @NotNull
    private String branch;
}
