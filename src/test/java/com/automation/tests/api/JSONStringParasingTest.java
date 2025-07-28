package com.automation.tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JSONStringParasingTest {

    @Test(enabled = false)
    public void jsonParsingString() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .when()
                .get("/users/1")
                .then()
                .extract().response();

        // Step 2: Parse response body as JSON
        JSONObject jsonObject = new JSONObject(response.getBody().asString());

        // Step 3: Validate values
        Assert.assertEquals(jsonObject.getString("name"), "Leanne Graham");
        Assert.assertEquals(jsonObject.getString("username"), "Bret");

        //validate nested response
        String city = jsonObject.getJSONObject("address").getString("city");
        Assert.assertEquals(city, "Gwenborough");

        String lat=jsonObject.getJSONObject("address").getJSONObject("geo").getString("lat");
        Assert.assertEquals(lat,"-37.3159","lat mismatch");

        //key presence
        Assert.assertTrue(jsonObject.has("name"), "Name attribute is missing in json");
        Assert.assertTrue(jsonObject.get("email") instanceof String, "Email value is not a string in response");

        System.out.println("All validations passed");
    }
}
