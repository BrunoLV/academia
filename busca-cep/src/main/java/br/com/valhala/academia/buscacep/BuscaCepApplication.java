package br.com.valhala.academia.buscacep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BuscaCepApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuscaCepApplication.class, args);
    }

}
