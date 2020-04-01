package br.com.valhala.academia.alunos.interfaces.rest;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.valhala.academia.alunos.interfaces.rest.dto.AlunoResource;
import br.com.valhala.academia.alunos.interfaces.rest.dto.EnderecoResource;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DBRider
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AlunoControllerTest {

    @LocalServerPort
    int port;

    @BeforeAll
    static void setupTest() {
        basePath="/alunos";
        FixtureFactoryLoader.loadTemplates("br.com.valhala.academia.alunos.interfaces.rest.templates");
    }

    @Test
    @DataSet(value = {"alunos.xml"})
    void deve_retornar_aluno_e_status_code_200() {

        final String guid = "5e9f7532-c505-4ec0-8fe3-187b5baee778";

        final String nomeEsperado = "Bruno";

        expect()
            .log().all()
            .body("nome", equalTo(nomeEsperado))
            .body("endereco", not(nullValue()))
        .given()
            .port(port)
            .header("accept", "application/json")
            .pathParam("guid", guid)
        .when()
            .get("/{guid}")
        .then()
            .statusCode(200);

    }

    @Test
    @DataSet(value = {"alunos.xml"})
    void deve_retornar_status_code_404_quando_nao_existir_aluno() {

        given()
            .log().all()
            .port(port)
            .header("accept", "application/json")
            .pathParam("guid", "nao_existe")
        .when()
            .get("/{guid}")
        .then()
            .statusCode(404);

    }

    @Test
    @DataSet(value = {"alunos.xml"})
    void deve_cadastrar_e_retornar_status_code_201() {

        final String nomeEsperado = "Douglas";

        final AlunoResource resource = Fixture.from(AlunoResource.class).gimme("novo");

        expect()
            .log().all()
            .body("guid", not(nullValue()))
            .body("nome", equalTo(nomeEsperado))
            .body("endereco", not(nullValue()))
            .header("Location", not(nullValue()))
        .given()
            .port(port)
            .accept("application/json")
            .contentType("application/json")
            .body(resource)
        .when()
            .post()
        .then()
            .statusCode(201);

    }

    @Test
    @DataSet(value = {"alunos.xml"})
    @ExpectedDataSet(value = {"alunos.xml"})
    void deve_retornar_400_e_mensagens_de_erro_quando_dados_invalidos() {

        final AlunoResource resource = Fixture.from(AlunoResource.class).gimme("alunoInvalido");

        expect()
            .log().all()
            .body("codigo", equalTo(400))
            .body("mensagensValidacao", not(empty()))
        .given()
            .port(port)
            .accept("application/json")
            .contentType("application/json")
            .body(resource)
        .when()
            .post()
        .then()
            .statusCode(400);

    }

    @Test
    @DataSet(value = {"alunoAntesAlteracao.xml"})
    @ExpectedDataSet(value = {"alunoPosAlteracao.xml"})
    void deve_alterar_aluno_e_retornar_status_code_204() {

        final AlunoResource resource = Fixture.from(AlunoResource.class).gimme("alunoAlterado");

        final String guid = "5e9f7532-c505-4ec0-8fe3-187b5baee778";

        expect()
            .log().all()
        .given()
            .port(port)
            .pathParam("guid", guid)
            .contentType("application/json")
            .body(resource)
        .when()
            .put("/{guid}")
        .then()
            .statusCode(204);

    }

    @Test
    @DataSet(value = {"alunoAntesAlteracao.xml"})
    @ExpectedDataSet(value = {"alunoPosAlteracaoEndereco.xml"})
    void deve_alterar_endereco_e_retornar_status_code_204() {

        final EnderecoResource resource = Fixture.from(EnderecoResource.class).gimme("enderecoAlterado");

        final String guid = "5e9f7532-c505-4ec0-8fe3-187b5baee778";

        expect()
            .log().all()
        .given()
            .port(port)
            .pathParam("guid", guid)
            .contentType("application/json")
            .body(resource)
        .when()
            .patch("/{guid}/endereco")
        .then()
            .statusCode(204);

    }

    @Test
    @DataSet(value = {"alunos.xml"})
    @ExpectedDataSet(value = {"alunosPosDelete.xml"})
    void deve_deletar_aluno_e_retornar_status_code_204() {

        final String guid = "5e9f7532-c505-4ec0-8fe3-187b5baee778";

        expect()
            .log().all()
        .given()
            .port(port)
            .pathParam("guid", guid)
        .when()
            .delete("/{guid}")
        .then()
            .statusCode(204);

    }

}
