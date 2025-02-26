package br.com.planisa.covid_benchmark.controller.common;

import br.com.planisa.covid_benchmark.dto.FieldErrorDetailDTO;
import br.com.planisa.covid_benchmark.dto.ResponseErrorDTO;
import br.com.planisa.covid_benchmark.exceptions.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldError = e.getFieldErrors();
        List<FieldErrorDetailDTO> errorsList = fieldError
                .stream()
                .map(fe -> new FieldErrorDetailDTO(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Failed", errorsList);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleOperationNotAllowedException(HttpMessageNotReadableException e) {
        return ResponseErrorDTO.defaultError(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleOperationNotAllowedException(MethodArgumentTypeMismatchException e) {
        return ResponseErrorDTO.defaultError(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseErrorDTO handleDublicateRegisterExcelption(ResourceAlreadyExistsException e) {
        return new ResponseErrorDTO(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of()
        );
    }

}
