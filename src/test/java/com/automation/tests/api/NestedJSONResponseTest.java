package com.automation.tests.api;

import com.automation.utils.SortUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class NestedJSONResponseTest {
    @Test(enabled = false)
    public void nestedJsonResponse()
    {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        Response response=given()
                .contentType(ContentType.JSON)
        .when()
                .get("/users/1");

        response.then()
                .assertThat()
                .statusCode(200)
                .body("name",equalTo("Leanne Graham"))
                .body("address.street",equalTo("Kulas Light"))
                .body("company.name",containsString("Romaguera-Crona"))
                .body("address.geo.lng",notNullValue());

        String name=response.jsonPath().getString("name");
        String street=response.jsonPath().getString("address.street");
        String companyName=response.jsonPath().getString("company.name");
        String geo=response.jsonPath().getString("address.geo.lng");
        System.out.println("name is "+name);
        System.out.println("Street is "+street);
        System.out.println("companyName is "+companyName);
        System.out.println("Geo lng is "+geo);
    }
}
