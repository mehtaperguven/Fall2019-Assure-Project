package com.automation.tests.MurodilShortVideos;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class QueryParams {

    @BeforeAll
    public static void Test(){
        baseURI="http://54.152.21.73:8000";
    }

    @Test
    public void QueryParam(){

        Response response= given().accept(ContentType.JSON).auth().basic("admin","admin").
                and().queryParam("gender", "Female").
                and().queryParam("nameContains","J").
                when().get("/api/spartans/search").prettyPeek();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Jamila"));

    }
    @Test
    public void usingMapForQueryParams(){

        Map<String,String> params=new HashMap<>();
        params.put("gender","Male");
        params.put("nameContains","J");

        Response response=given().accept(ContentType.JSON).
                auth().basic("admin","admin").and().queryParams(params).
                when().get("/api/spartans/search");

        response.prettyPeek();
        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Jeter"));

    }


}
