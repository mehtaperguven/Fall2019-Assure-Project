package com.automation.tests.MurodilShortVideos;

import  static io.restassured.RestAssured.*;

import com.automation.pojos.SpartanVasyl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

public class De_Serialization {

    @BeforeAll
    public static void  setup(){
        baseURI="http://54.236.33.73:8000";
    }


 @Test
    public void testGson(){

     Response response=given().auth().basic("admin","admin").
             when().pathParam("id",15).get("/api/spartans/{id}");
             response.then().statusCode(200);
             response.prettyPrint();
//DESERIALIZATION   JSON---> JAVA COLLECTION
     /*{
    "id": 15,
    "name": "Meta",
    "gender": "Female",     //JSON BODY
    "phone": 1938695106
}
JAVA MAP COLLECTION
MAP OBJECT==={id=15, name=Meta, gender=Female, phone=1938695106}

     * */
    Map<String,Object>  deserializationMap= response.body().as(Map.class);
     System.out.println();
     System.out.println(deserializationMap.get("name"));

     System.out.println(deserializationMap);


 }
 @Test
    public void deSerialization(){

        Response response=given().auth().basic("admin","admin").accept(ContentType.JSON).
                when().get("/api/spartans");
        //response.prettyPrint();

     //we convert response body that is jSON to Java Collection (List)
     //de-serialization
        List<Map<String,Object>> listOfSpartans=response.body().as(List.class);
     System.out.println(listOfSpartans.get(0));
     int count=1;
     for (Map<String, Object> each:listOfSpartans){
         System.out.println(count +"   "+each);
         count++;
     }

 }

 @Test
    public void jsonToPojoClass(){
        Response response=given().auth().basic("admin","admin").pathParam("id",15).
                when().get("/api/spartans/{id}");

        SpartanVasyl spartan=response.body().as(SpartanVasyl.class);
        assertEquals(spartan.getId(),15);
        assertEquals(spartan.getName(),"Meta");
        assertEquals(spartan.getGender(),"Female");
        assertEquals(spartan.getPhoneNumber(),123456789l);
        //assertEquals(spartan.getPhoneNumber(),new Long(123456789));


 }
 @Test
    public void gsonMethods(){

        String jsonBody="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";


        Gson gson=new Gson();
//we have deserialization below
     //we converted our json object to the Java Collection
     //we converted json to POJO
        SpartanVasyl spartanClassObj=gson.fromJson(jsonBody, SpartanVasyl.class);

        System.out.println(spartanClassObj);

        SpartanVasyl spartanNew=new SpartanVasyl(1,"Erva","Female",123456789l);
//Below is serialization
     //we converted POJO to JSon object
        String jsonObj=gson.toJson(spartanNew);

     System.out.println(jsonObj);

 }



}
