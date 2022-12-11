package com.dhernandez.partidos.controllers;

import com.dhernandez.partidos.dtos.ErrorResponseDTO;
import com.dhernandez.partidos.dtos.ValidationErrorDTO;
import com.dhernandez.partidos.exceptions.ModelNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ModelNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> notModelException(HttpServletRequest peticion, ModelNotFoundException ex){

        return construirResponse(peticion,ex.getMessage(),HttpStatus.NOT_FOUND,null);
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponseDTO> invalidDataException(HttpServletRequest request, ConstraintViolationException ex){
        List<ValidationErrorDTO> errores = new ArrayList<>();
        for( ConstraintViolation violation :ex.getConstraintViolations() ) {
            ValidationErrorDTO err = new ValidationErrorDTO(violation.getPropertyPath().toString(),violation.getMessage());
            errores.add(err);
        }
        return construirResponse(request,"Error Validacion de datos",HttpStatus.BAD_REQUEST,errores);
    }

    private ResponseEntity<ErrorResponseDTO> construirResponse(HttpServletRequest request, String mensaje, HttpStatus status, List<ValidationErrorDTO> errores){
        ErrorResponseDTO responseDTO =  new ErrorResponseDTO(mensaje,status.value(),request.getRequestURI(),errores);
        return new ResponseEntity<>(responseDTO,status);
    }
}
