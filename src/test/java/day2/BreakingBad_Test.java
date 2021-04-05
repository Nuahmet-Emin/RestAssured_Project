package day2;

import io.restassured.http.ContentType;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class BreakingBad_Test {

    @BeforeAll
    public static void init(){
        baseURI     = "https://www.breakingbadapi.com";
        basePath    = "/api" ;
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }

    @DisplayName("GET /characters with name query param")
    @Test
    public void searchCharacter(){
        given()
                .log().uri()
                .queryParam("name","Walter").
        when()
                .get("/characters").
        then()
                .assertThat() // this is coming from restassured, it's just for readability
                .log().all()
                //and() // this is just for readability
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .contentType("application/json; charset=utf-8")
                ;


    }


    @DisplayName("Test GET /characters/{char_id}")
    @Test
    public void testOneCharacter(){

        given()
                .log().uri()
                .pathParam("char_id",1).
        when()
                .get("/characters/{char_id}").
        then()
                .log().all()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .contentType("application/json; charset=utf-8")
        ;


    }

    // /episodes/60
    @DisplayName("Test GET /episodes/{episode_id}")
    @Test
    public void test1Episode(){

        given()
                .log().uri()
                .pathParam("episode_id",60).
        when()
                .get("/episodes/{episode_id}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                ;


    }


    }
