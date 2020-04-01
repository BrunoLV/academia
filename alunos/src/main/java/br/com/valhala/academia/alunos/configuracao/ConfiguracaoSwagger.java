package br.com.valhala.academia.alunos.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableSwagger2
public class ConfiguracaoSwagger {

    @Bean
    public Docket alunosApi() {

        final ApiInfo apiInfo = new ApiInfo("Alunos",
                new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/apiInfo.txt"))).
                        lines().collect(Collectors.joining(System.lineSeparator())),
                "1.0.0-RC1",
                "",
                new Contact("Bruno Luiz Viana", "", "brunolviana22@hotmail.com"),
                "",
                "", Collections.emptyList());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.valhala.academia.alunos.interfaces.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo)
                .pathMapping("/");
    }

}
