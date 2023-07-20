package org.acme.rest.resource;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ClientResourceTest {

    @Test
    void findAll() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
        when().
                get("api/clients").
        then().
                statusCode(Response.Status.OK.getStatusCode())
                .body("size()", is(1000));
    }

    @Test
    void find() {
    }

    @Test
    void findById() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", 1).
        when()
                .get("/api/books/{id}").
        then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("title", is(""))
                .body("title", is(""))
                .body("title", is(""))
                .body("title", is(""))
                .body("title", is(""));

    }

    @Test
    void update() {
    }

    @Test
    void persist() {
    }

    @Test
    void delete() {
    }
}