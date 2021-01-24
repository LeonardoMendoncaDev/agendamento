package br.gov.rj.proderj.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AgendamentoResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/agendamentos")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}