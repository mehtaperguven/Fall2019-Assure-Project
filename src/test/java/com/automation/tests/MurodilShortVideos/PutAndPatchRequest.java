package com.automation.tests.MurodilShortVideos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;

public class PutAndPatchRequest {


    @BeforeAll
    public static void setup(){
        baseURI="http://54.236.33.73:8000";
    }
    @Test
    public void putRequest(){

        Map<String, Object> mapPut=new HashMap<>();
        mapPut.put("name","Cabir");
        mapPut.put("gender","Male");
        mapPut.put("phone",1244567890L);

        given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .pathParam("id",121).body(mapPut).when().put("/api/spartans/{id}")
        .then().assertThat().statusCode(204);

        Response response=given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .pathParam("id",121).when().get("/api/spartans/{id}");
        response.prettyPeek();


    }
    @Test
    public void patchRequest(){

        Map<String, Object> mapPatch=new HashMap<>();
        mapPatch.put("name","Texas");

        given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .pathParam("id",121).body(mapPatch).when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);


        Response response=given().given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .pathParam("id",121).when().get("/api/spartans/{id}");
        response.prettyPrint();



    }
}
