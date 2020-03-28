package br.com.valhala.academia.alunos.interfaces.rest;

import br.com.valhala.academia.alunos.interfaces.rest.dto.AlunoResource;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DBRider
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AlunoControllerTest {

    @LocalServerPort
    int port;

    @Test
    @DataSet(value = {"alunos.xml", "enderecos.xml"})
    void deve_retornar_aluno_e_status_code_200() {

        final String guidEsperado = "5e9f7532-c505-4ec0-8fe3-187b5baee778";
        final String nomeEsperado = "Bruno";
        final String nomeDoMeioEsperado = "Luiz";
        final String sobrenomeEsperado = "Viana";

        expect()
            .body("guid", equalTo(guidEsperado))
            .body("nome", equalTo(nomeEsperado))
            .body("nomeDoMeio", equalTo(nomeDoMeioEsperado))
            .body("sobrenome", equalTo(sobrenomeEsperado))
            .body("endereco", not(nullValue()))
        .given()
            .port(port)
            .header("accept", "application/json")
            .basePath("/alunos")
            .pathParam("guid", "5e9f7532-c505-4ec0-8fe3-187b5baee778")
        .when()
            .get("/guid/{guid}")
        .then()
            .statusCode(200);

    }

    @Test
    @DataSet(value = {"alunos.xml", "enderecos.xml"})
    void deve_retornar_status_code_404_quando_nao_existir_aluno() {

        given()
            .port(port)
            .header("accept", "application/json")
            .basePath("/alunos")
            .pathParam("guid", "nao_existe")
        .when()
            .get("/guid/{guid}")
        .then()
            .statusCode(404);
    }

}
