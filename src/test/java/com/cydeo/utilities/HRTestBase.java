package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HRTestBase {

    // BeforeAll is the same thing as beforeClass in testNG
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://34.230.89.69:1000/ords/hr";
    }
}
