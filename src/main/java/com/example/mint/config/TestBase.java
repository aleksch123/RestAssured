package com.example.mint.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import static com.example.mint.constants.Constants.RunVariable.path;
import static com.example.mint.constants.Constants.RunVariable.server;
import static com.example.mint.constants.Constants.Servers.*;

public class TestBase {



    protected RequestSpecification requestSpecificationXMLH = new RequestSpecBuilder()
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addFormParam("v","2.5")
            .setBaseUri(ILEDECASINO_URL)
            .build();


@BeforeClass
    public void setUp(){
        RestAssured.baseURI= server;
        RestAssured.basePath= path;





        //RestAssured.requestSpecification = requestSpecificationXMLH;
    }

}
