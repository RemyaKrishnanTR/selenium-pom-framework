package com.automation.tests.api;

import com.automation.tests.BaseTest;
import com.automation.utils.JsonValidatorUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.keyStore;
import static net.javacrumbs.jsonunit.core.ConfigurationWhen.then;

public class JsonValidationTest extends BaseTest {

    @Test(enabled = false)
    public void jsonValidationNestedObjectTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .when()
                .get("/users/1")
                .then()
                .assertThat()
                .extract().response();

        String responseStr = response.getBody().asString();
        System.out.println(responseStr);
        JSONObject jsonObject = JsonValidatorUtil.parseJson(responseStr);

        //Key validations
        Assert.assertTrue(JsonValidatorUtil.isKeyPresent(jsonObject, "name"));
        Assert.assertTrue(JsonValidatorUtil.isKeyPresent(jsonObject, "address"));

        //Nested Object handling: response->address
        JSONObject address = JsonValidatorUtil.nestedObject(jsonObject, "address");
        String city = address.getString("city");
        System.out.println(city);

        //Deep Nested objects handling : response->address->geo->lat
        JSONObject geo = JsonValidatorUtil.nestedObject(jsonObject, "address", "geo");
        String lat = geo.getString("lat");
        System.out.println(lat);

    }

    @Test(enabled = false)
    public void validateJsonArrayTest() {
        String str = """
                {
                    "id":101,
                    "name":"Alice",
                    "email":"email@example.com",
                    "projects":[
                            {
                                "name":"QA Automation",
                                "status":"Active"
                            },
                            {
                                "name":"Devops Pipeline",
                                "status":"inactive"
                            }
                    ]
                }
                """;
        JSONObject jsonObject = new JSONObject(str);

        Assert.assertTrue(jsonObject.has("projects"));

        JSONArray projectArray = JsonValidatorUtil.getJsonArray(jsonObject, "projects");
        for (int i = 0; i < projectArray.length(); i++) {
            JSONObject project = projectArray.getJSONObject(i);
            String name = project.getString("name");
            String status = project.getString("status");

            System.out.println("Project " + (i + 1) + ": Name is " + name + " and status is " + status);
        }

    }

    @Test(enabled = false)
    public void schemaValidationTest() {
        String str = """
                {
                    "id":101,
                    "name":"Alice",
                    "email":"email@example.com",
                    "projects":[
                            {
                                "name":"QA Automation",
                                "status":"Active"
                            },
                            {
                                "name":"Devops Pipeline",
                                "status":"inactive"
                            }
                    ]
                }
                """;

        given()
                .body(str)
                .contentType("application/json")
                .when()
                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/usersSchema.json")));


    }

}




    /* --? how this works in an actual api response? get the response and check if the response body matches schema mentioned in the specified path
@Test
    public void validateActualApiResponseAgainstSchema() {
        RestAssured.baseURI = "https://your-mock-api.io"; // replace with actual domain

        Response response = given()
                .when()
                .get("/users/101")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        // Schema Validation against actual API response
        response.then().assertThat()
                .body(matchesJsonSchema(new File("src/test/resources/schema/user_schema.json")));
    }
}
 */

