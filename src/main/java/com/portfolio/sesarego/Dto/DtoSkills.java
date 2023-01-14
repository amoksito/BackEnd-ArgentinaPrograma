package com.portfolio.sesarego.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoSkills {
    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;
    
}
