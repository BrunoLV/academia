package br.com.valhala.academia.buscacep.aplicacao.externo;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.infra.correios.EnderecoERP;
import br.com.valhala.academia.buscacep.infra.correios.SQLException_Exception;
import br.com.valhala.academia.buscacep.infra.correios.SigepClienteException;
import br.com.valhala.academia.buscacep.infra.services.correios.AtendeClienteService;
import br.com.valhala.academia.buscacep.modelo.Endereco;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CorreiosService {

    private AtendeClienteService atendeClienteService;

    public CorreiosService(final AtendeClienteService atendeClienteService) {
        this.atendeClienteService = atendeClienteService;
    }

    public Endereco buscaPorCep(final String cep) throws BuscaCepException {
        try {
            log.info("Chamando servico externo dos correios.");
            EnderecoERP enderecoERP = atendeClienteService.consultaCep(cep);
            return Endereco.builder().
                    bairro(enderecoERP.getBairro()).
                    cep(enderecoERP.getCep()).
                    cidade(enderecoERP.getCidade()).
                    endereco(enderecoERP.getEnd()).
                    uf(enderecoERP.getUf()).
                    build();
        } catch (SigepClienteException | SQLException_Exception e) {
            log.error("Ocorreu um erro ao chamar o servico externo dos correios. Erro: " + e.getMessage(), e);
            throw new BuscaCepException(e.getMessage());
        }
    }

}
