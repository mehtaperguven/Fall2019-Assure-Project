package com.automation.tests.day3;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestsDay3 {

    //54.211.196.64


    @BeforeAll
    public static void setup(){
       // baseURI="http://54.211.196.64:1000/ords/hr";
        baseURI = "http://54.224.118.38:1000/ords/hr";
    }


    /**
     * given path parameter is "/regions/{id}"
     * when user makes get request
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public void verifyFirstRegion(){
        given().pathParam("id",1).
                when().
                get("/regions/{id}").prettyPeek().
                then().assertThat().statusCode(200)
        .body("region_name",is("Europe"));

    }
}
