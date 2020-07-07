package com.automation.tests.MurodilShortVideos;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class ChainingHamcrest {
    @BeforeAll
    public static void setUp(){

        baseURI="http://54.236.33.73:8000";

    }

    @Test
    public void mathcersEqualTo(){

        Response response=  given().auth().basic("admin","admin").
                accept(ContentType.JSON).when().pathParam("id",15)
                .get("/api/spartans/{id}").prettyPeek();

                response.then().statusCode(200).
                and().contentType("application/json;charset=UTF-8");

    }
    @Test
    public void mathcers(){
        given().auth().basic("admin","admin").accept(ContentType.JSON).
                when().pathParam("id",15).get("/api/spartans/{id}").
                then().statusCode(200)
                .and().assertThat().body("id", Matchers.equalTo(15),
                                    "name", equalTo("Meta"),
                                          "gender", Matchers.equalTo("Female"),
                                           "phone",equalTo(1938695106))
        ;
    }
}
