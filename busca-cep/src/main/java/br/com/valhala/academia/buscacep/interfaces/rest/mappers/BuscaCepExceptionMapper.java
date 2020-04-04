package br.com.valhala.academia.buscacep.interfaces.rest.mappers;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.interfaces.rest.dto.ErroResource;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BuscaCepExceptionMapper implements ExceptionMapper<BuscaCepException> {
    @Override
    public Response toResponse(BuscaCepException e) {
        return Response.status(500).entity(new ErroResource(e.getMessage())).type("application/json").build();
    }
}
