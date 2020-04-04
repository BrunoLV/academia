package br.com.valhala.academia.buscacep.aplicacao.exceptions;

import br.com.valhala.academia.buscacep.interfaces.rest.BuscaCepController;
import br.com.valhala.academia.buscacep.interfaces.rest.dto.ErroResource;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class BuscaCepException extends Exception {

    public BuscaCepException(final String message) {
        super(message);
    }

}
