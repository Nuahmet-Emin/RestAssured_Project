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

public class SpartanPostingData_Test extends SpartanNoAuthBaseTest {

    @DisplayName("POST /spartans with String")
    @Test
    public void testPostDataWithStringBody() {

        /*
           {
                "name" : "Abigale",
                "gender" : "Female",
                "phone" : 1800233232
            }
         */

        String postStrBody = "{\n" +
                "                \"name\" : \"Abigale\",\n" +
                "                \"gender\" : \"Female\",\n" +
                "                \"phone\" : 1800233232\n" +
                "            }";

        given()
                .log().all()
          //    .header("Content-Type","application/json")
                .contentType(ContentType.JSON) // this is providing header for request
                .body(postStrBody).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON) // this is asserting response header is json
                .body("success",is("A Spartan is Born!") )
                .body("data.name",is("Abigale"))
        ;

    }

    @DisplayName("POST /spartans with external file")
    @Test
    public void testPostDataWithJsonFileAsBody() {

        // singleSpartan.json with below content
        /*
           {
                "name" : "Abigale",
                "gender" : "Female",
                "phone" : 1800233232
            }
         */

        File jsonFile = new File("singleSpartan.json");
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonFile).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                ;

    }

    @DisplayName("POST /spartans with Map Object")
    @Test
    public void testPostDataWithMapObjectAsBody() {
/*
           {
                "name" : "Abigale",
                "gender" : "Female",
                "phone" : 1800233111
            }
* */

        Map<String,Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name","Abigale");
        bodyMap.put("gender","Female");
        bodyMap.put("phone",1800233232L);

        System.out.println("bodyMap = " + bodyMap);
        // We are expecting this Java Map object to be converted into Json String and send as body
        // initially it failed , RestAssured can no find any serializer to convert java object to json
        // We added Jackson-data-bind dependency in pom , so RestAssured can used it to make it conversion happen
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(   bodyMap   ).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
        ;

    }

    @DisplayName("POST /spartans with POJO")
    @Test
    public void testPostDataWithPOJOAsBody(){

         Spartan sp = new Spartan("Abigale","Female",1800233232L);
        // turn into below
        /*
           {
                "name" : "Abigale",
                "gender" : "Female",
                "phone" : 1800233232
            }
         */

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(   sp   ).
        when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
        ;

    }

    @DisplayName("PATCH /spartans/{id} body as String")
    @Test
    public void testPartialUpdateDataWithString(){

        String patchBody = " {\"phone\":1234567890 } ";
        System.out.println(patchBody); // {"phone":1234567890 }



        given()
                   .log().all()
                   .pathParam("id",33)
                   .contentType(ContentType.JSON)
                   .body(patchBody).
           when()
                   .patch("spartans/{id}").
           then()
                   .statusCode(204)
                   ;

    }

    @DisplayName("Test DELETE /spartans/{id} ")
    @Test
    public void testDeleteOne(){



        given()
                .log().uri()
                .pathParam("id" , 4).
        when()
                .delete("/spartans/{id}").
        then()
                .statusCode( equalTo(204) ) ;


    }



    }
