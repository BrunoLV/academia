package br.com.valhala.academia.alunos.aplicacao.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DadosInvalidosException extends RuntimeException {

    private Set<String> mensagensValidacao;

    public DadosInvalidosException(String message, Set<String> mensagensValidacao) {
        super(message);
        this.mensagensValidacao = mensagensValidacao;
    }

}
