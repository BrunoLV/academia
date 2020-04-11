package br.com.valhala.academia.buscacep.aplicacao.interno.saida;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.infra.correios.EnderecoERP;
import br.com.valhala.academia.buscacep.infra.correios.SQLException_Exception;
import br.com.valhala.academia.buscacep.infra.correios.SigepClienteException;
import br.com.valhala.academia.buscacep.infra.services.correios.AtendeClienteService;
import br.com.valhala.academia.buscacep.modelo.Endereco;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class CorreiosService {

    private AtendeClienteService atendeClienteService;

    public CorreiosService(final AtendeClienteService atendeClienteService) {
        this.atendeClienteService = atendeClienteService;
    }

    public Endereco buscaPorCep(final String cep) throws BuscaCepException {
        try {
            log.info("Chamando correios");
            EnderecoERP enderecoERP = atendeClienteService.consultaCep(cep);
            return Endereco.builder().
                    bairro(enderecoERP.getBairro()).
                    cep(enderecoERP.getCep()).
                    cidade(enderecoERP.getCidade()).
                    endereco(enderecoERP.getEnd()).
                    uf(enderecoERP.getUf()).
                    build();
        } catch (SigepClienteException | SQLException_Exception e) {
            throw new BuscaCepException(e.getMessage());
        }
    }

}
