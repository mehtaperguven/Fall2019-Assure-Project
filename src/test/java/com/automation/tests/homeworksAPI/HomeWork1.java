package com.automation.tests.homeworksAPI;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class HomeWork1 {

    @BeforeAll
    public static void setup() {
        baseURI = "https://cybertek-ui-names.herokuapp.com/api/";
    }

    @Test
    public void getNames() {
        Response response = given().get().prettyPeek();
        response.then().assertThat().statusCode(200).
                and().contentType(" application/json; charset=utf-8").
                and().
                body("name", notNullValue()).
                body("surname", notNullValue()).
                body("gender", notNullValue()).
                body("region", notNullValue());

    }

    @Test
    public void genderTest() {

        Response response = given().queryParams("gender", "female").when().get().prettyPeek();
        response.then().assertThat().statusCode(200).
                and().contentType("application/json; charset=utf-8")
                .and().body("gender", is("female"));

    }

    @Test
    public void twoGendersTest() {

        Response response = given().queryParam("gender", "female").
                queryParam("region", "Romania").
                when().get().prettyPeek();

        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8")
                .and().body("region", is("Romania")).body("gender", is("female"));


    }

    @Test
    public void invalidGenderTest() {
        Response response = given().queryParams("gender", "fmale").when().get().prettyPeek();

        response.then().assertThat().
                statusCode(400).body("error", is("Invalid gender"))
                .statusLine(containsString("Bad Request"));

    }

    @Test
    public void invalidRegionTest() {

        Response response = given().queryParam("region", "Romanian").when().get().prettyPeek();
        response.then().assertThat().
                statusLine(containsString("Bad Request")).and().
                statusCode(400)
                .and().
                body("error", is("Region or language not found"));


    }

    @Test
    public void differentName() {

        Response response = given().queryParam("region", "Romania").queryParam("amount",2).
              when().get().prettyPeek();


       List<Map<String, Objects>>  map= response.body().as(List.class);

       response.then().assertThat().statusCode(200).
               contentType("application/json; charset=utf-8").and()
               .body("size",is(map.size()));
        List<String> names=response.path("name");
        System.out.println(names);
        System.out.println("*******************");
        System.out.println(map);
        System.out.println(map.get(1).get("name"));

        }

    List<String> genders = Arrays.asList("male","female");//We will pick random gender for each execution

   public int generateNumber(){
       Random random=new Random();
       int num=random.nextInt(5);
       return num;
   }
    public String generateRandomGender() {
        Collections.shuffle(genders);
        return genders.get(0);
    }
        @Test
    public void test(){

       Response response= given().queryParam("gender",generateRandomGender()).
                queryParam("amount",5). when().get().prettyPeek();

        List<String> lst= response.body().jsonPath().get("region");

            System.out.println(lst);

        }

        //getProperty("user.dir") will provide project path: C:\Users\1\Desktop\APIHomeworks...

    File namesJson = new File(System.getProperty("user.dir") + File.separator + "names.json");
    JsonPath jsonPath = new JsonPath(namesJson);
    List<String>regions = jsonPath.getList("region");

    public String generateRandomRegion() {
        Collections.shuffle(regions);
        return regions.get(0);
    }
        @Test
public void tet2(){
        String randomRegion=generateRandomRegion();
        String randomGender=generateRandomGender();
        //int randomNumber=generateNumber();
       Response response=given().queryParam("gender",randomGender).
               queryParam("region",randomRegion).
               queryParam("amount",5).
               when().get().prettyPeek();

       response.then().assertThat().statusCode(200).
               and().contentType("application/json; charset=utf-8").
               and().body("region",everyItem(is(randomRegion))).
                     body("gender",everyItem(is(randomGender))).
                     body("size",is(5));

    List<String> regions=response.body().jsonPath().get("region");
            System.out.println(regions);

    }
    //    Amount count test
//1. Create a request by providing query parameter: amount (must be bigger than 1)
//2. Verify status code 200, content type application/json; charset=utf-8
//3. Verify that number of objects returned in the response is same as the amount passed in step 1
    @Test
    public void test3(){

        Response response=given().queryParam("amount",2).when().get().prettyPeek();

        response.then().assertThat().statusCode(200).and().
                statusLine(containsString("OK")).
                and().
                body("size",is(2));
    }

    }
