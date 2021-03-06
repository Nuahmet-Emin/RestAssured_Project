package day3;

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

public class SpartanJsonPath_Test extends SpartanNoAuthBaseTest {

    @Test
    public void testOne() {

        Response response =
                given()
                        .log().all()
                        .pathParam("id", "16").
                        when()
                        .get("/spartans/{id}")
                        .prettyPeek();

        // using path method to extract data
        int myId = response.path("id");
        System.out.println("myId = " + myId);

        // Few meaning of JsonPath :
        //1. just like xpath -- it is used to provide location of certain data
        //2. JsonPath as a class coming from RestAssured to provide reusable methods to extract data
        //3. jsonPath() method of Response object to get JsonPath object
        JsonPath jp = response.jsonPath();  // number 2 for variable type , number 3 for method

        myId = jp.getInt("id");
        System.out.println(" the result is = " + myId);

        long phoneNum = jp.getLong("phone");
        System.out.println("phoneNum = " + phoneNum);

        String myName = jp.getString("name");
        System.out.println("myName = " + myName);

        System.out.println("Save whole json object " + jp.getMap(""));

        Map<String, Object> resultJsonInMap = jp.getMap("");
        System.out.println("resultJsonInMap = " + resultJsonInMap);


    }


    @Test
    public void testGetAllSpartans() {

        //    Response response = get("/spartans");
        //    JsonPath jp = response.jsonPath();

        // print first id in the json array response

        JsonPath jp = get("/spartans").jsonPath();
        System.out.println("jp.getInt(\"id[0]\") = "
                + jp.getInt("id[0]"));

        System.out.println("jp.getString(\"name[0]\") = "
                + jp.getString("name[1]"));

        System.out.println("jp.getInt(\"id[0]\") = "
                + jp.getInt("id[1]"));


        System.out.println("jp.getString(\"[0]\") = "
                + jp.getString("[0]"));

        System.out.println("jp.getInt(\"[0].id\") = "
                + jp.getInt("[0].id"));


    }

    @DisplayName("Extract data from GET/spartans/search")
    @Test
    public void testGetSearchSpartans() {

        JsonPath jp =
                given()
                        .queryParam("nameContains", "Nurahmet")
                        .queryParam("gender", "Male")
                        .log().all().
                        when()
                        .get("/spartans/search")
                        .prettyPeek()
                        .jsonPath();

        // find out first guy id, second guy name
        // content[0].id
        System.out.println("jp.getInt(\"content[0].id\") = "
                + jp.getInt("content[0].id"));

        System.out.println("jp.getString(\"content[1].name\") = "
                + jp.getString("content[1].name"));


        // store first jsonObject into a map
        Map<String, Object> firstJsonInMap =
                jp.getMap("content[0]");

        System.out.println("firstJsonInMap = " + firstJsonInMap);

    }

    @DisplayName("Saving json array fields into List")
    @Test
    public void testSavingJsonArrayFieldsIntoList() {

        JsonPath jp =
                given()
                        .queryParam("nameContains", "Nurahmet")
                        .queryParam("gender", "Male")
                        .log().all().
                        when()
                        .get("/spartans/search")
                        .prettyPeek()
                        .jsonPath();

        // save all the ids into a list
        System.out.println("jp.getList(\"content[].id\") = "
                + jp.getList("content.id"));

        System.out.println("jp.getList(\"content.name\") = "
                + jp.getList("content.name"));

        System.out.println("jp.getList(\"content.phone\") = "
                + jp.getList("content.phone"));

        // getList method has 2 overloaded versions
        // 1. jp.getList("json path here") ; -->> the type of list will be automatically determine
        List<Integer> allIds = jp.getList("content.id");

        // 2. jp.getList("json path here", class Type  you want this list to have)
        List<Integer> allIds2 = jp.getList("content.id", Integer.class);

        List<String> allNames = jp.getList("content.name");
        List<String> allNames2 = jp.getList("content.name", String.class);

        List<Long> allPhones = jp.getList("content.phone");
        List<Long> allPhones2 = jp.getList("content.phone", Long.class);


    }
        @DisplayName("Get List Practice for GET /spartans")
        @Test
        public void testGetListOutOfAllSpartans(){

        JsonPath jp = get("/spartans").jsonPath();
        // save the list into list object and assert the size

            List<Integer> allIds = jp.getList("id", Integer.class);
            assertThat(allIds.size(),is(127));

            List<String> allNames = jp.getList("name", String.class );
            assertThat(allNames.size(),is(127));

            List<Long> allPhones = jp.getList("phone", Long.class );
            assertThat(allPhones.size(),is(127));
    }
}
