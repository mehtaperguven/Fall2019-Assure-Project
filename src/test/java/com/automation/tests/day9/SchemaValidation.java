package com.automation.tests.day9;

import com.automation.utilities.ConfigurationReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class SchemaValidation {
    @BeforeAll
    public static void beforeAll() {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        authentication = basic("admin", "admin");
    }
    @Test
    public void  schemaValidationTest(){

        //we validate if the format of the response is correct or not
        File schema =new File("spartan_schema.json");
         get("/spartans/{id}",35).
                 prettyPeek().
                 then().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

}
