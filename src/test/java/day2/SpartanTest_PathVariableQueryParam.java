package day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import test_util.SpartanNoAuthBaseTest;

@DisplayName("Spartan Test with Path variable and query param")
public class SpartanTest_PathVariableQueryParam extends SpartanNoAuthBaseTest {

    @Test
    public void getOneSpartan() {

        //get("/spartans/16").prettyPeek();

        Response r1 =
                given()
                        .header("Accept", "application/json")
                        .pathParam("spartan_id", 16).
                when()
                        .get("/spartans/{spartan_id}")
                        .prettyPeek();

        Response r2 =
                given()
                        .accept("application/json").
                when()
                        .get("/spartans/{spartan_id}", 16)
                        .prettyPeek();

    }

    @DisplayName("Logging the request")
    @Test
    public void getOneSpartanWithLog(){

        Response response =
                given()
                       .log().all()
                       .accept("application/json")
                       .pathParam("id",16).
                when()
                       .get("/spartans/{id}")
                       .prettyPeek()
                ;

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), is("application/json"));
        assertThat(response.path("name"), is("Sinclair"));




    }


}
