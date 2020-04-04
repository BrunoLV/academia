package br.com.valhala.academia.buscacep.configuracao;

import br.com.valhala.academia.buscacep.infra.correios.AtendeCliente;
import br.com.valhala.academia.buscacep.infra.correios.AtendeClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapClientConfig {

    @Bean
    public AtendeCliente atendeCliente() {
        AtendeClienteService atendeClienteService = new AtendeClienteService();
        return atendeClienteService.getAtendeClientePort();
    }

}
