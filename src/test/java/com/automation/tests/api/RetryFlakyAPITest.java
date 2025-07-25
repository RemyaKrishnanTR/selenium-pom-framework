package com.automation.tests.api;

import com.automation.tests.listeners.RetryAnalyzer;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class RetryFlakyAPITest {

    private static final WireMockServer wireMockServer = new WireMockServer(8089);

    @BeforeClass
    public void startWireMockAndStubOnce() {
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);

        // Stub once with scenario state — DO NOT reset between retries!
        stubFor(get(urlEqualTo("/flaky/api"))
                .inScenario("Flaky Scenario")
                .whenScenarioStateIs(STARTED)
                .willReturn(aResponse().withStatus(500))
                .willSetStateTo("Second Attempt"));

        stubFor(get(urlEqualTo("/flaky/api"))
                .inScenario("Flaky Scenario")
                .whenScenarioStateIs("Second Attempt")
                .willReturn(aResponse().withStatus(500))
                .willSetStateTo("Third Attempt"));

        stubFor(get(urlEqualTo("/flaky/api"))
                .inScenario("Flaky Scenario")
                .whenScenarioStateIs("Third Attempt")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"Status\":\"OK\"}")));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class,enabled = false)
    public void flakyAPITestShouldPassEventually() {
        RestAssured.baseURI = "http://localhost:8089";

        Response response = when().get("/flaky/api");

        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("Status", equalTo("OK"));
    }

    @AfterClass
    public void stopServer() {
        wireMockServer.stop();
    }
}
