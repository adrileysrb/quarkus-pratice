package org.acme.client;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

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
        Client customer = createClient();
        Client saved = given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post()
                .then()
                .statusCode(201)
                .extract().as(Client.class);
        Client got = given()
                .when()
                .get("/{clientId}", saved.getClientId())
                .then()
                .statusCode(200)
                .extract().as(Client.class);
        assertThat(saved).isEqualTo(got);
    }

    @Test
    public void getByIdNotFound() {
        given()
                .when()
                .get("/{clientId}", 987654321)
                .then()
                .statusCode(404);
    }

    @Test
    void post() {
        Client client = createClient();
        Client saved = given()
                .contentType(ContentType.JSON)
                .body(client)
                .post()
                .then()
                .statusCode(201)
                .extract().as(Client.class);
        assertThat(saved).isNotNull();
    }

    @Test
    void put() {
        Client client = createClient();
        Client saved = given()
                .contentType(ContentType.JSON)
                .body(client)
                .post()
                .then()
                .statusCode(201)
                .extract().as(Client.class);
        saved.setFirstName("Updated");
        given()
                .contentType(ContentType.JSON)
                .body(saved)
                .put("/{clientId}", saved.getClientId())
                .then()
                .statusCode(204);
    }

    //Cria um cliente fake para usar nos testes
    private Client createClient() {
        Client client = new Client();

        Random random = new Random();
        int i = random.nextInt(20000 - 1001) + 1001;
        client.setClientId((short) i);

        client.setFirstName("Eleonore");
        client.setLastName("Benford");
        client.setEmail("ebenford0@histats.com");
        client.setGender("Female");
        client.setStreetName("Dahle");
        client.setCountry("United States");
        client.setCity("Baton Rouge");
        client.setPostalCode("70826");
        client.setPhone("(225) 6769366");

        return client;
    }
}