package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
public class MovieJsonPath_Test {

    @BeforeAll
    public static void init(){
        baseURI = "http://www.omdbapi.com";

    }

    @AfterAll
    public static void cleanup(){
        reset();
    }

    @Test
    public void searchOneMovie(){
        JsonPath jp =
               given()
                .log().uri()
                .accept(ContentType.JSON)
                .queryParam("apikey","ae417808")
                .queryParam("t","John Wick").
        when()
                .get("/").
                prettyPeek().jsonPath()
        ;
        System.out.println("jp.getString(\"Title\") = "
                + jp.getString("Title"));
        System.out.println("jp.getString(\"Year\") = "
                + jp.getString("Year"));
        System.out.println("IMDB Rating = "
                + jp.getString("Ratings.Value[0]"));

        System.out.println("jp.getString(\"Ratings.Source[1]\") = "
                + jp.getString("Ratings.Source[2]"));


    }

    @Test
    public void searchMultipleMovies(){

        JsonPath jp =
           given()
                .log().uri()
                .accept(ContentType.JSON)
                .queryParam("s","Flash")
                .queryParam("type","series")
                .queryParam("apikey","ae417808").
           when()
                 .get("/")
                 .jsonPath()
                   .prettyPeek()
                ;

        System.out.println("jp.getString(\"Search[2]\") = "
                + jp.getString("Search[2]"));

        List<String> imdbID = jp.getList("Search.imdbID");
        System.out.println(imdbID);

        System.out.println("jp.getString(\"totalResults\") = "
                + jp.getString("totalResults"));

    }

}
