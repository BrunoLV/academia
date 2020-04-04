package br.com.valhala.academia.buscacep.interfaces.rest;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.aplicacao.interno.saida.CorreiosService;
import br.com.valhala.academia.buscacep.interfaces.rest.dto.EnderecoResource;
import br.com.valhala.academia.buscacep.modelo.Endereco;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Path("/buscaCep")
public class BuscaCepController {

    private CorreiosService correiosService;

    public BuscaCepController(final CorreiosService correiosService) {
        this.correiosService = correiosService;
    }

    @GET
    @Path("/{cep}")
    @Produces(value = "application/json")
    public Response buscaCep(@PathParam("cep") final String cep) throws BuscaCepException {

        final Endereco endereco = correiosService.buscaPorCep(cep);

        return Response.ok(EnderecoResource.aPartirDe(endereco)).build();

    }

}
