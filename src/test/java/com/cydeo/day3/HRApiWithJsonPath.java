package com.cydeo.day3;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiWithJsonPath extends HRTestBase {

    @Test
    public void test1(){
        Response response = get("/countries");

        // items[3].country_name

        // we want to use JsonPath to get that value
        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getString("items[3].country_name"));

        // get all country names
        // items.country_name
        List<String> list = jsonPath.getList("items.country_name");

        // print all countries one by one
        for (String s : list) {
            System.out.println(s);
        }

        // get all country names where the region_id is 2
        List<Object> countryListWithID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryListWithID2);
    }

    @DisplayName("GET request to /employees with param")
    @Test
    public void test2(){
        Response response = given()
                .queryParam("limit", 150)
                .when().get("/employees");

        // get json object
        JsonPath jsonPath = response.jsonPath();

        // get me all emails who is working as IT_PROG
        List<String> emailList = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(emailList);

        // get first name of employees who is making more than 10000
        List<String> nameList = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(nameList);

        // get the first name of who is making the highest salary
        String maxFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String minFirstName = jsonPath.getString("items.min {it.salary}.first_name");
        System.out.println(maxFirstName);
        System.out.println(minFirstName);

        // another way to print maxFirstName
        System.out.println(response.path("items.max {it.salary}.first_name").toString());
    }
}
