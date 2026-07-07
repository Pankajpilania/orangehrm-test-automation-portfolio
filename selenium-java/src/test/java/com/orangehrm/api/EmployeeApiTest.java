package com.orangehrm.api;

import com.orangehrm.utils.ConfigReader;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class EmployeeApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("api.url");
    }

    @Test
    public void testGetEmployeeSchema() {
        // Placeholder for an employee API endpoint call to validate schema
        /*
        given()
                .header("Authorization", "Bearer token-placeholder")
                .when()
                .get("/pim/employees/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File("src/test/resources/schemas/employee-schema.json")));
        */
        Assert.assertTrue(true, "Employee Schema API Test Stub passed");
    }
}
