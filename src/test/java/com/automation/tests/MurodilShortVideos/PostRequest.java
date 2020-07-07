package com.automation.tests.MurodilShortVideos;

import com.automation.pojos.Spartan;
import com.automation.pojos.SpartanVasyl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequest {


    @BeforeAll
    public static void  setup(){
        baseURI="http://54.236.33.73:8000";
    }



    @Test
    public void postRequestWithString(){

        //we sent json body as string, we posted a new json body

        Response response=given().auth().basic("admin","admin").
                accept(ContentType.JSON).contentType(ContentType.JSON).
                when().body("{\n" +
                "        \"name\": \"Erva\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 1234567898\n" +
                "    }").post("/api/spartans");
       // response.prettyPrint();
        assertEquals(response.statusCode(),201);
        assertEquals(response.path("success"),"A Spartan is Born!");
        assertEquals(response.contentType(),"application/json");

        JsonPath jsonPath=response.jsonPath();
        assertEquals(jsonPath.getString("data.name"),"Erva");
        assertEquals(jsonPath.getString("data.gender"),"Female");
        assertEquals(jsonPath.getLong("data.phone"),1234567898l);

    }
    @Test
    public void useMapToPost(){

        Map<String, Object> mapBody=new HashMap<>();
        mapBody.put("name","Dilara");
        mapBody.put("gender","Female");
        mapBody.put("phone",1234567899l);


        Response response=given().auth().basic("admin","admin").accept(ContentType.JSON).and().contentType(ContentType.JSON).
                when().body(mapBody).post("/api/spartans");

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        response.prettyPeek();
    }
    @Test
    public void postWithPojo() {
        //     Spartan spartan=new Spartan("Mike","Male", 1234567890L);
        Spartan spartan = new Spartan();
        spartan.setName("Meltem");
        spartan.setGender("Female");
        spartan.setPhone(1234567890L);

        Response response = given().auth().basic("admin", "admin").accept(ContentType.JSON).and().contentType(ContentType.JSON).
                when().body(spartan).post("/api/spartans");

        //Gson converts this spartan (POJO) to JSON object and post it

        response.prettyPeek();
        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");

// GET REQUEST
       Response response1=given().auth().basic("admin","admin").contentType(ContentType.JSON).
               when().pathParam("id",120).get("/api/spartans/{id}");

       Spartan spartan1=response1.body().as(Spartan.class);

        System.out.println(spartan1.toString());


    }
}
