package br.com.valhala.academia.buscacep.configuracao;

import br.com.valhala.academia.buscacep.aplicacao.exceptions.BuscaCepException;
import br.com.valhala.academia.buscacep.interfaces.rest.BuscaCepController;
import br.com.valhala.academia.buscacep.interfaces.rest.mappers.BuscaCepExceptionMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BuscaCepController.class);
        register(BuscaCepExceptionMapper.class);
        configureSwagger();
    }

    public void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);


        BeanConfig config = new BeanConfig();
        config.setConfigId("Busca CEP");
        config.setTitle("Busca CEP");
        config.setDescription("Aplicação de Busca de Endereços por CEP");
        config.setVersion("1.0.0");
        config.setBasePath("/api");
        config.setResourcePackage("br.com.valhala.academia.buscacep.interfaces.rest");
        config.setScan(true);
        config.setContact("brunolviana22@hotmail.com");
        config.setSchemes(new String[]{"http"});
        config.setPrettyPrint(true);
    }

}
