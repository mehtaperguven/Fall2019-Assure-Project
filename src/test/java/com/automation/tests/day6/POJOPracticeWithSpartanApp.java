package com.automation.tests.day6;
import com.automation.pojos.SpartanVasyl;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class POJOPracticeWithSpartanApp {



    @BeforeAll
    public static void beforeAll() {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        authentication = basic("admin", "admin");
    }

    @Test
    public void addSpartanTest() {
        /**
         * {
         *   "gender": "Male",
         *   "name": "Nursultan",
         *   "phone": "123112312312"
         * }
         */

        Map<String, String> spartan = new HashMap<>();
        spartan.put("gender", "Male");
        spartan.put("name", "Nursultan");
        spartan.put("phone", "123112312312");

        RequestSpecification requestSpecification = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(spartan);

        Response response = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(spartan).
                when().
                post("/spartans").prettyPeek();

        response.then().statusCode(201);
        response.then().body("success", is("A Spartan is Born!"));

        //deserialization
        SpartanVasyl spartanResponse = response.jsonPath().getObject("data", SpartanVasyl.class);
        Map<String, Object> spartanResponseMap = response.jsonPath().getObject("data", Map.class);

        System.out.println(spartanResponse);//prints as a spartan
        //Spartan{id=175, name='Nursultan', gender='Male', phoneNumber=123112312312}
        System.out.println(spartanResponseMap);//prints as a map
        //{id=175.0, name=Nursultan, gender=Male, phone=1.23112312312E11}

        //spartanResponse is a Spartan
        System.out.println(spartanResponse instanceof SpartanVasyl);// must be true
    }

    @Test
    @DisplayName("Retrieve exiting user, update his name and verify that name was updated successfully.")
    public void updateSpartanTest(){
        int userToUpdate = 101;
        String name = "Nursultan";

        //HTTP PUT request to update exiting record, for example exiting spartan.
        //PUT - requires to provide ALL parameters in body

        SpartanVasyl spartan = new SpartanVasyl(name, "Male", 123112312312L);

        //get spartan from web service
        SpartanVasyl spartanToUpdate = given().
                auth().basic("admin", "admin").
                accept(ContentType.JSON).
                when().
                get("/spartans/{id}", userToUpdate).as(SpartanVasyl.class);
        //update property that you need without affecting other properties
        System.out.println("Before update: "+spartanToUpdate);
        spartanToUpdate.setName(name);//change only name
        System.out.println("After update: "+spartanToUpdate);

        //request to update existing user with id 101
        Response response = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                body(spartanToUpdate).
                when().
                put("/spartans/{id}", userToUpdate).prettyPeek();

        //verify that status code is 204 after update
        response.then().statusCode(204);
        System.out.println("##############################################");
        //to get user with id 101, the one that we've just updated
        given().
                auth().basic("admin", "admin").
                when().
                get("/spartans/{id}", userToUpdate).prettyPeek().
                then().
                statusCode(200).body("name", is(name));
        //verify that name is correct, after update

    }
    @Test
    @DisplayName("Verify that user can perform PATCH request")
    public void patchUserTest1() {
        //PATCH - partial update of existing record

        int userId = 21;//user to update, make user with this id exist

        //let's pu the code to take random user
        //get all spartans
        Response response0 = given().accept(ContentType.JSON).when().get("/spartans");
        //I can save them all in the array list
        List<SpartanVasyl> allSpartans = response0.jsonPath().getList("", SpartanVasyl.class);
        //Spartan.class - data type of collection
        //getList - get JSON body as a list

        //generate random number
        Random random = new Random();
        int randomNum = random.nextInt(allSpartans.size());

        int randomUserID = allSpartans.get(randomNum).getId();
        System.out.println("NAME BEFORE: " + allSpartans.get(randomNum).getName());

        userId = randomUserID;//to assign random user id
        System.out.println(allSpartans);

        Map<String, String> update = new HashMap<>();
        update.put("name", "Nursultan");
        //this is a request to update user
        Response response = given().
                contentType(ContentType.JSON).
                body(update).
                when().
                patch("/spartans/{id}", userId);

        response.then().assertThat().statusCode(204);

        //after we sent PATCH request, let's make sure that name is updated
        //this is a request to verify that name was updated and status code is correct as well
        given().
                accept(ContentType.JSON).
                when().
                get("/spartans/{id}", userId).prettyPeek().
                then().
                assertThat().statusCode(200).body("name", is("Nursultan"));
    }


    }


