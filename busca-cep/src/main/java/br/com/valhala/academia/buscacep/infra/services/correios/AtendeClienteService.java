package br.com.valhala.academia.buscacep.infra.services.correios;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.infra.correios.*;
import org.springframework.stereotype.Component;

import java.lang.Exception;

@Component
public class AtendeClienteService {

    private AtendeCliente atendeCliente;

    public AtendeClienteService(AtendeCliente atendeCliente) {
        this.atendeCliente = atendeCliente;
    }

    public EnderecoERP consultaCep(final String cep) throws SigepClienteException, SQLException_Exception {
        return atendeCliente.consultaCEP(cep);
    }

}
