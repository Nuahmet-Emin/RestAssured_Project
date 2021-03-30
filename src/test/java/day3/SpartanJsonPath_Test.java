package day3;

import io.restassured.path.json.JsonPath;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class SpartanJsonPath_Test extends SpartanNoAuthBaseTest {

    @Test
    public  void testOne(){

    }



    @Test
    public void testGetAllSpartans(){

    //    Response response = get("/spartans");
    //    JsonPath jp = response.jsonPath();

        // print first id in the json array response
        JsonPath jp = get("/spartans").jsonPath();
        System.out.println("jp.getInt(\"id[0]\") = "
                  + jp.getInt("id[0]"));

        System.out.println("jp.getShort(\"name[1]\") = "
                  + jp.getString("name[1]"));

        System.out.println("jp.getString(\"[]\") = " + jp.getMap("[0]"));

        System.out.println("jp.getInt(\"[0].id\") = " + jp.getInt("[0].id"));


    }

    @DisplayName("Extract data from GET/spartans/search")
    @Test
    public void testGetSearchSpartans(){
        given()
                .queryParam("nameContains", "Abigale")
                .queryParam("gender", "Male")
                .log().all().
        when()
               .get("/spartans/search")
               .prettyPrint();



    }


}
