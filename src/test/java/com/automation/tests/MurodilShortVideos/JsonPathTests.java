package com.automation.tests.MurodilShortVideos;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JsonPathTests {

    @BeforeAll
    public static void setUp(){
        baseURI="http://54.152.21.73:8000";

    }

    @Test
    public void jsonPath(){

        Response response=given().auth().basic("admin","admin").contentType(ContentType.JSON).
                when().pathParam("id",18).get("/api/spartans/{id}");

        System.out.println("id :: "+response.path("id"));
        System.out.println("name::  "+response.path("name"));

        JsonPath jsonData= response.jsonPath();

        int id= jsonData.getInt("id");
        System.out.println(id);
        String name=jsonData.getString("name");
        System.out.println(name);
       long phone=jsonData.getLong("phone");
        System.out.println(phone);

    }
    


}


