package day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Intro to RestAssured")
public class RestAssured_Intro {

    @DisplayName("Testing hello endpoint")
    @Test
    public void testTheHelloInput(){

        // first request to
        // http://52.23.99.232:8000/api/hello
        //save the response into a object with type Response
       Response response =  get("http://52.23.99.232:8000/api/hello");
       //extracting information from Response object

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());


        System.out.println("response.getHeader(\"Content-Type\") = "
                           + response.getHeader("Content-Type"));

        System.out.println("response.header() = " + response.header("Date"));

        System.out.println("response.contentType() = "
                           + response.contentType());

        System.out.println("response.getContentType() = "
                           + response.getContentType());

        System.out.println("response.asString() = " + response.asString());

        //getting time spent for execution
        System.out.println("response.getTime() = " + response.getTime());
        System.out.println("response.time() = " + response.time());


        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(),is("text/plain;charset=UTF-8"));
        assertThat(response.contentType(),startsWith("text/plain"));
        assertThat(response.asString(), is("Hello from Sparta"));

        //printing the result
        // prettyPrint() -->> print and return String
        // prettyPeek() -->> print and return same Response Object
        response.prettyPrint();
        response.prettyPeek();



    }
    @DisplayName("Testing Get/api/spartan/{id}")
    @Test
    public void testSingleSpartan(){
      //send request to get   http://52.23.99.232:8000/api/spartans/16
        // save the response and print out whole response

        Response response = get("http://52.23.99.232:8000/api/spartans/16").prettyPeek();

        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.contentType(),is("application/json"));
        assertThat(response.header("Connection"), equalTo("keep-alive"));

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"gender\") = " + response.path("gender"));
        System.out.println("response.path(\"phone\") = " + response.path("phone"));


        int myId = response.path("id");
        String myName = response.path("name");
        long myPhone = response.path("phone");
        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myPhone = " + myPhone);


    }



}
