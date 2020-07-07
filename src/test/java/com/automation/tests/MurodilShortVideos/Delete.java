package com.automation.tests.MurodilShortVideos;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Delete {

    @BeforeAll
    public static void setup(){
        baseURI="http://54.236.33.73:8000";
    }
    @Test
    public void deleteMethod(){
        given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .pathParam("id",122).when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .pathParam("id",121).when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(404);

    }
}
