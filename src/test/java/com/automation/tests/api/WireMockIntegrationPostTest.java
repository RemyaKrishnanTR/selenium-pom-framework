package com.automation.tests.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class WireMockIntegrationPostTest {

    private WireMockServer wireMockServer;

    @BeforeClass
    public void setup()
    {
        wireMockServer=new WireMockServer(8089);
        wireMockServer.start();
        WireMock.configureFor("localhost",8089);

        WireMock.stubFor(WireMock.post(urlEqualTo("/api/users"))
                        .withHeader("Content-Type",WireMock.equalTo("application/json"))
                        .withRequestBody(matchingJsonPath("$.name"))
                        .withRequestBody(matchingJsonPath("$.Job"))
                .willReturn(aResponse()
                .withHeader("Content-Type","application/json")
                .withStatus(201)
                .withBody("{\"id\":\"101\",\"createdAt\":\"2025-07-21T10:00:00Z\"}")));

    }

    @Test(enabled = false)
    public void postmock()
    {
        RestAssured.baseURI="http://localhost:8089";
        String requestBody= """
                {
                    "name":"Remya",
                    "Job":"QA"
                }""";

        Response response=given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/api/users");
        response.then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id",equalTo("101"))
                .body("createdAt",notNullValue());

        System.out.println("Full repsonse is "+response.asPrettyString());
    }

    @AfterClass
    public void tearDown()
    {
        wireMockServer.stop();
    }
}
