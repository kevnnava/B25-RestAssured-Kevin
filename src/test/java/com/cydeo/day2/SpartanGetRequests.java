package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String url = "http://18.232.174.189:8000";

    /*
    Given Accept type is application/json
    When user sends GET request to /api/spartans end point
    The status code must be 200
    And response content type must be application/json
     */

    @DisplayName("GET all Spartans")
    @Test
    public void test1(){

        Response response = RestAssured.
                given().accept(ContentType.JSON)
                .when().get(url + "/api/spartans");

        // prints out status code
        System.out.println("response.statusCode() = " + response.statusCode());
        // prints out content type
        System.out.println("response.contentType() = " + response.contentType());

        // how to test API
        // verify status code is 200
        Assertions.assertEquals(200, response.statusCode());
        // verify content type is application/json
        Assertions.assertEquals("application/json", response.contentType());


    }

    /*
    Given Accept header is application/json
    When users send a GET request to /api/spartans/3
    Then status code must be 200
    And Content type must be application/json
    And json body should contain 'Fidole'
     */

    @DisplayName("GET individual Spartan")
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(url + "/api/spartans/3");

        // verify status code is 200
        Assertions.assertEquals(200, response.getStatusCode());
        // verify content type is application/json
        Assertions.assertEquals("application/json", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Fidole"));


    }

    /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @DisplayName("GET request to /api/hello endpoint")
    @Test
    public void test3(){

        Response response = RestAssured.when().get(url + "/api/hello");

        response.prettyPrint();

        // verify status code is 200
        Assertions.assertEquals(200, response.getStatusCode());

        // verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        // verify Date header exists in Response headers
        // we use hasHeaderWithName method to verify header exists or not - it returns boolean
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        // to get header value we use header() method which accepts header name as parameter and returns value as string
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));

        // verify content length is 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        // verify body is "hello from sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }


}
