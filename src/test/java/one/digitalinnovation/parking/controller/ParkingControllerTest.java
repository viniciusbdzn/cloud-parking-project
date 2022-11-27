package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTestIT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenfindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .body("license[0]", Matchers.equalTo("DMS-1111"));
                //.extract().response().body().prettyPrint();
    }

    @Test
    void create() {
    }
}