package com.cydeo.day7;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanWithAuthTest extends SpartanAuthTestBase {

    //R
    @DisplayName("GET /api/spartans as a public user(guest) expect 401")
    @Test
    public void test1(){
        // unauthenticated result (not using any credentials with admin, user, or editor)
        given().accept(ContentType.JSON)
                .when()
                .get("api/spartans")
                .then().statusCode(401)
                .log().all();
    }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        // how to provide admin admin as a username and password ?
        given()
                .auth().basic("admin","admin")
                .and().accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/spartans")
                .then().statusCode(200).log().all();
    }

    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditor(){
        // using credentials but not having authorization or not enough power to access
        given()
                .auth().basic("editor","editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",40)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(403).log().all();
    }

    /*
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all
     */

    //create spartanUtil class, create one static method that returns spartan info with faker object


}
