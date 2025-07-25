package com.automation.tests.api;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class APIValidationTest {
    @Test(enabled = false)
    public void validateGetPostById()
    {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";

        Response response=given()
                .contentType(ContentType.JSON)
        .when()
                .get("/posts/1");

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId",equalTo(1))
                .body("id",equalTo(1))
                .body("title",notNullValue());

        System.out.println("Full repsonse is "+response.asPrettyString());

        int userid=response.jsonPath().getInt("userId");
        String title=response.jsonPath().getString("title");
        int id=response.jsonPath().getInt("id");

        System.out.println("userId: "+userid);
        System.out.println("id: "+id);
        System.out.println("title: "+title);
    }
}
