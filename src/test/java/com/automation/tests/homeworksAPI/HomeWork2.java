package com.automation.tests.homeworksAPI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class HomeWork2 {

    @BeforeAll
    public static void setup() {
        baseURI = "https://cybertek-ui-names.herokuapp.com/api/";
    }
    @Test
    public static void test1(){

    }
}
