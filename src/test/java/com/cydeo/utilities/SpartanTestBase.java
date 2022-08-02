package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    // BeforeAll is the same thing as beforeClass in testNG
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://34.230.89.69:8000";

        String dbUrl = "jdbc:oracle:thin:@34.230.89.69:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }

    @AfterAll
    public static void close(){
        DBUtils.destroy();
    }
}
