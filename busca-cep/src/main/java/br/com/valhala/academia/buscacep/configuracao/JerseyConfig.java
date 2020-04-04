package br.com.valhala.academia.buscacep.configuracao;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.interfaces.rest.BuscaCepController;
import br.com.valhala.academia.buscacep.interfaces.rest.mappers.BuscaCepExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BuscaCepController.class);
        registerClasses(BuscaCepExceptionMapper.class);
    }

}
