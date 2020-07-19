package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import static constants.Constants.RunVariable.path;
import static constants.Constants.RunVariable.server;
import static constants.Constants.Servers.*;

public class TestBase {

   protected RequestSpecification requestSpecificationJson = new RequestSpecBuilder()
            .addHeader("Content-Type","application/json")
            .addCookie("testCookieJson")
           .setBaseUri(JSONPLACEHOLDER_URL)
            .build();

    protected RequestSpecification requestSpecificationXml = new RequestSpecBuilder()
            .addHeader("Content-Type","application/xml")
            .addCookie("testCookieXml")
            .setBaseUri(REQUSTBIN_URL)
            .build();

    protected ResponseSpecification responseSpecificationForGet = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    protected ResponseSpecification responseSpecificationForPost = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    protected RequestSpecification requestSpecificationForSwapiTests = new  RequestSpecBuilder()
            .setBaseUri(SWAPI_URL)
            .build();

@BeforeClass
    public void setUp(){
        RestAssured.baseURI= server;
        RestAssured.basePath= path;

     RequestSpecification requestSpecificationJson = new RequestSpecBuilder()
            .addHeader("Content-Type","application/json")
            .addCookie("testCookieJson")
            .build();

        RestAssured.requestSpecification = requestSpecificationJson;
    }

}
