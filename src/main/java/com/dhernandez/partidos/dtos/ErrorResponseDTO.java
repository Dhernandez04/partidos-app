package com.dhernandez.partidos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private String mensaje;
    private int codigoEstado;
    private String path;
    private List<ValidationErrorDTO> errores;
}
