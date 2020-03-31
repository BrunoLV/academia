package br.com.valhala.academia.alunos.interfaces.rest;

import br.com.valhala.academia.alunos.aplicacao.exceptions.DadosInvalidosException;
import br.com.valhala.academia.alunos.interfaces.rest.dto.ErroResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlunosControllerAdvice {

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity trataErroValidacao(DadosInvalidosException exception) {
        ErroResource resource = ErroResource.builder().codigo(400).mensagem(exception.getMessage()).build();
        exception.getMensagensValidacao().stream().forEach(m -> {
            resource.adicionaMensagemValidacao(m);
        });
        return ResponseEntity.badRequest().body(resource);
    }

}
