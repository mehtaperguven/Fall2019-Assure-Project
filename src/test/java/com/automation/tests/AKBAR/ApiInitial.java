package com.automation.tests.AKBAR;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class ApiInitial {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://52.205.194.10";
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api";
    }

    @Test
    public void test1(){
        //log().all() or prettyPeek() !!!!
        given().log().uri().when().get("spartans/{id}",5).
                then().log().all().statusCode(200).body("name", is("Blythe"));
    }
}
