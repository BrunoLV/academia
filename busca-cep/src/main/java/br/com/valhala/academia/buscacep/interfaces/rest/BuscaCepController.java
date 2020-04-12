package br.com.valhala.academia.buscacep.interfaces.rest;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.aplicacao.externo.CorreiosService;
import br.com.valhala.academia.buscacep.interfaces.rest.dto.EnderecoResource;
import br.com.valhala.academia.buscacep.modelo.Endereco;
import io.swagger.annotations.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Slf4j
@Api(value = "/buscaCep", description = "Operações utilizando CEP para busca de endereços.")
@Component
@Path("/buscaCep")
public class BuscaCepController {

    private CorreiosService correiosService;

    public BuscaCepController(final CorreiosService correiosService) {
        this.correiosService = correiosService;
    }

    @ApiOperation(value = "Busca por CEP",
                  notes = "Operação para buscar endereço por CEP",
                  response = EnderecoResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Alguma coisa deu errado na requisição.")
    })
    @GET
    @Path("/{cep}")
    @Produces(value = "application/json")
    public Response buscaCep(@ApiParam(value = "CEP a ser pesquisado", required = true) @PathParam("cep") final String cep) throws BuscaCepException {
        log.info("Executanco servico de busca de endereco. CEP: " + cep);
        final Endereco endereco = correiosService.buscaPorCep(cep);
        return Response.ok(EnderecoResource.aPartirDe(endereco)).build();
    }

}
