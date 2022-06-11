package com.cydeo.day3;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiWithPath extends HRTestBase {

    @DisplayName("GET request to contries with Path method")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        // response.prettyPrint();

        // prints limit result
        System.out.println(response.path("limit").toString());

        // prints hasMore
        System.out.println(response.path("hasMore").toString());

        // prints second country
        System.out.println(response.path("items[1].country_id").toString());

        // prints 4th element country
        System.out.println(response.path("items[3].country_id").toString());

        // get all country names
        List<String> countryName= response.path("items.country_name");
        System.out.println(countryName);

        // assert that in the response all region_ids are 2
        // save all the region IDs inside the list
        List<Integer> allRegionIDs = response.path("items.region_id");

        // assert one by one that they are equal to 2
        for (Integer regionID: allRegionIDs) {
            assertEquals(2, regionID);
        }
    }

    /*
        send a GET request o employees endpoint, filter only Job_id IT_PROG
        then assert that all job_ids are IT_PROG
     */
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT.PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());

        // assert all the job ids are IT_PROG
        List<String> allJobIDs = response.path("items.job_id");

        // verify each of them are IT_PROG
        for (String jobID : allJobIDs) {
            assertEquals("IT_PROG", jobID);
        }
    }


}
