package com.automation.tests.day7;
import com.automation.pojos.SpartanVasyl;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
public class BasicAuthentication {

    @Test
    public void spartanAuthentication(){
        baseURI= ConfigurationReader.getProperty("SPARTAN.URI");


        given().auth().basic("admin","admin").
               when().get("/spartans").prettyPeek().
                then().statusCode(200);


    }
    @Test
    public void authorizationTest(){
        baseURI= ConfigurationReader.getProperty("SPARTAN.URI");
        SpartanVasyl spartan=new SpartanVasyl("Erva","Female",87654321901L);
  given().auth().basic("user","user").body(spartan).
          contentType(ContentType.JSON).when().post("/spartans").prettyPeek().
          then().statusCode(403);
//user does not have right to add, delete or edit any user. User can only read.
//403=Forbidden access, you logged in and you try to do something that you are not allowed
//Authentication problem==403==>you can log but ou can not do any action
//Authentication problem, may be you could not log in
    }
    @Test
    public void authenticationTest(){
        baseURI=ConfigurationReader.getProperty("SPARTAN.URI");
        get("/spartans").prettyPeek().then().statusCode(401);
        //if we don't provide credentials we get 401 status code
    }

    @Test
    public void authenticationTest2(){
        baseURI="http://practice.cybertekschool.com";
        given().auth().basic("admin","admin").
                when().get("/basic_auth").prettyPeek().
                then().statusCode(200).contentType(ContentType.HTML);

    }
}
