package br.com.alura.challenge.adopet.infra;

import br.com.alura.challenge.adopet.exception.EnderecoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public ResponseEntity<MostradorErro> tratarErroDeValidacao(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new MostradorErro(e.getBindingResult().getFieldError().getField(), e.getBindingResult().getFieldError().getDefaultMessage()));
    }
}
