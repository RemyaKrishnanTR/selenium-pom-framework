package com.automation.tests.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class wireMockIntegrationGetTest {
    private WireMockServer wireMockServer;

    @BeforeClass
    public void setup()
    {
        wireMockServer=new WireMockServer(8089);
        wireMockServer.start();

        WireMock.configureFor("localhost", 8089);

        WireMock.stubFor(get(urlEqualTo("/api/users/2"))
                .willReturn(aResponse()
                .withHeader("Content-Type","application/json")
                .withStatus(200)
                .withBody("{\"name\":\"Mocked user\",\"email\":\"mock@reqres.in\"}")));

    }

    @Test(enabled = false)
    public void testMockedApi()
    {
        RestAssured.baseURI="http://localhost:8089";

        Response response=given()
                .contentType(ContentType.JSON)
        .when()
                .get("/api/users/2");

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("name",equalTo("Mocked user"))
                .body("email",containsString("mock@reqres.in"));

        System.out.println("Full repsonse is "+response.asPrettyString());

        String name=response.jsonPath().getString("name");
        String email=response.jsonPath().getString("email");
        System.out.println("Name is "+name);
        System.out.println("email is "+email);
    }

    @AfterClass
    public void teardown()
    {
        wireMockServer.stop();
    }
}
