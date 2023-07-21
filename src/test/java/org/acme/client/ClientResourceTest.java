package org.acme.client;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(ClientResource.class)
class ClientResourceTest {

    @Test
    void get() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200);
    }

    @Test
    void getById() {
    }

    @Test
    void post() {
    }

    @Test
    void put() {
    }
}