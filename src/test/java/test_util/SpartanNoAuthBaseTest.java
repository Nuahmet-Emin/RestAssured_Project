package test_util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanNoAuthBaseTest {


    @BeforeAll
    public static void init(){
        //set URL at RestAssured
        RestAssured.baseURI = "http://52.23.99.232:8000";
        RestAssured.basePath = "/api/";
    }

    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }
}
