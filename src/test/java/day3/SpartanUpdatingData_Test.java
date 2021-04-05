package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import pojo.Spartan;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class SpartanUpdatingData_Test extends SpartanNoAuthBaseTest {

    @DisplayName("PUT /spartans/{id} body as Map")
    @Test
    public void testUpdateDataWithMap(){

        Map<String,Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name","Abigale");
        bodyMap.put("gender","Female");
        bodyMap.put("phone",1800233232L);

        given()
                .log().all()
                .pathParam("id",1)
                .contentType(ContentType.JSON)
                .body(bodyMap).
        when()
                .put("/spartans/{id}").
        then()
                .statusCode(204)
        ;


    }


    @DisplayName("PUT /spartans/{id} body as POJO")
    @Test
    public void testUpdateDataWithPOJO(){

        Spartan sp = new Spartan("Dean","Male",1231231234);
        given()
                .log().all()
                .pathParam("id",1)
                .contentType(ContentType.JSON)
                .body( sp ).
                when()
                .put("/spartans/{id}").
                then()
                .statusCode(204)
                ;


    }




    }
