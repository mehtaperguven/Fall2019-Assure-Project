package com.automation.tests.day3;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
//hamcrest.Matchers this matcher is advanced assertion
//no only assert.AssertEquals, AssertTrue,
//there are many comparisons hasItem, greaterThan,

public class ExchangeRatesAPITests {

    @BeforeAll
    public static void setup(){
        baseURI="http://api.openrates.io";
    }

    //get latest currency rates

    @Test
    public void getLatestRates(){

        Response response=get("/latest").prettyPeek();
        //verify that get request is done to the endpoint successful
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void getLatestUSDRate(){
        Response response=given().
                queryParam("base","USD")
                .when().get("/latest").prettyPeek();

        Headers headers=response.getHeaders();
        String contentType=headers.getValue("Content-Type");
        System.out.println(contentType);
        response.then().statusCode(200);
        //base is key parameter
        response.then().assertThat().body("base",is("USD"));
        //import static org.hamcrest.Matchers.*;
        //infect it can be written as below , since we used static import no need to write Matchers.is
        //we write directly is("USD")...
        //response.then().assertThat().body("base",Mathchers.is("USD"));
        //response.then().assertThat().body("base",equalTo("USD"));
        //date is key parameter

        String date= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().assertThat().body("date",containsString(date));
    }
    @Test
    public void getHistoryOfRates(){

        Response response=given().queryParam("base","USD")
                .when().get("/2008-01-02").prettyPeek();

        Headers headers=response.getHeaders();
        response.then().assertThat().statusCode(200).
               and()
                .body("date", is("2008-01-02")).
               and()
                .body("rates.USD",is(1.0f));

        float property=response.jsonPath().get("rates.USD");
        System.out.println(".............."+property);
        System.out.println(headers);

        //rates is an object,
        //all currencies are like instance variables
        //to get any instance variable(property)>>>>get(" objectName.propertyName");
        float param=response.jsonPath().get("rates.EUR");
        assertEquals(param,0.6808278867f);
        System.out.println("................."+param);


    }
}
