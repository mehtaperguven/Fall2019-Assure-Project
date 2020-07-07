package com.automation.tests.MurodilShortVideos;

import static io.restassured.RestAssured.*;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathMethod {

    @BeforeAll
    public static void setUp(){
        baseURI="http://54.152.21.73:8000";

    }


    @Test
    public void usingPathMethod(){

        Response response=given().auth().basic("admin","admin").
                contentType(ContentType.JSON).
                and().
                pathParam("id",10).
                when().get("/api/spartans/{id}").prettyPeek();

        System.out.println(response.body().path("id").toString());
        System.out.println(response.body().path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id= response.path("id");
        String name=response.body().path("name");
        String gender=response.body().path("gender");
        long phone=response.path("phone");

        System.out.println("id ::"+id);
        System.out.println("name:: "+name);
        System.out.println("gender:: "+gender);
        System.out.println("phone:: "+phone);
    }


    @Test
    public void pathMethod(){
        Response response=given().auth().basic("admin","admin").
                and().contentType(ContentType.JSON).when().get("/api/spartans").prettyPeek();


        int firstId=response.path("id[0]");
        System.out.println("firstId is:: "+firstId);
        String firstName=response.path("name[0]");
        System.out.println("first name is :: "+firstName);
        String lastName=response.path("name[-1]");
        System.out.println("last name is :: "+lastName);
        List<String> names=response.path("name");
        System.out.println(names);
        System.out.println("size of names list "+names.size());
        System.out.println("@@@@@@@@@@@@@@" +
                "@@");
        List<Object> phones=response.path("phone");
        for (Object each:phones){
            System.out.println(each);
        }


    }

}
