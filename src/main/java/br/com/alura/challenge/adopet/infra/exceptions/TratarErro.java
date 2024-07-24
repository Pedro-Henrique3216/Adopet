package br.com.alura.challenge.adopet.infra.exceptions;

import br.com.alura.challenge.adopet.exception.EnderecoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class TratarErro {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> tratarErroDeTutorNaoEncontrado(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EnderecoException.class)
    public ResponseEntity<String> tratarErroDeBuscaDeEndereco(EnderecoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<MostradorErro>> tratarErroDeValidacao(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream()
                .map(erro -> new MostradorErro(erro.getField(), erro.getDefaultMessage()))
                .toList());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> tratarErroDeValidacao(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body("modelo do id invalido");
    }
}
