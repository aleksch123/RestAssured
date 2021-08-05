import com.example.mint.config.TestBase;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.example.mint.constants.Constants.Actions.SWAPI_GET_PEOPLE;
import static com.example.mint.constants.Constants.Path.SWAPI_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Test1 extends TestBase {
  @Test
    public void firstTest(){

        given().
        log().uri().
        when().get(SWAPI_GET_PEOPLE+"1").
        then().log().body().statusCode(200);
    }

  @Test
  public void GetSomeFieldInResponseAssertion(){
      given().spec(requestSpecificationForSwapiTests)
             .log().uri()
             .when().get(SWAPI_PATH)
             .then().body("people",equalTo("http://swapi.dev/api/people/"))
             .log().body();
  }
   @Test
    public void GetSomeFieldInResponseWithIndexAssertion(){
        given().spec(requestSpecificationForSwapiTests)
                .log().uri()
                .when().get(SWAPI_PATH+SWAPI_GET_PEOPLE)
                .then()
                .body("count",equalTo(82))
                .body("results.name[0]",equalTo("Luke Skywalker"))
                .log().body();
    }

   @Test
   public void GetAllDataFromRequest(){
       Response response =
               given().spec(requestSpecificationForSwapiTests)
               .log().uri()
               .when().get(SWAPI_PATH+SWAPI_GET_PEOPLE)
               .then().extract().response();
       String jsonResponseAsString = response.asString();
       System.out.println(jsonResponseAsString);
   }

   @Test
    public void getCookieFromResponse(){
       Response response =
               given().spec(requestSpecificationForSwapiTests)
               .log().uri()
               .when().get(SWAPI_PATH+SWAPI_GET_PEOPLE)
               .then().extract().response();
       Map<String,String> allCookie =response.getCookies();
       System.out.println("allCookie---> "+allCookie);

   }

    @Test
    public void getHeadersFromResponse(){
        Response response =
                given().spec(requestSpecificationForSwapiTests)
                        .log().uri()
                        .when().get(SWAPI_PATH+SWAPI_GET_PEOPLE)
                        .then().extract().response();
        Headers headers =response.getHeaders();
        String contentType = response.getContentType();
        System.out.println("ContetntType --> "+contentType);
        System.out.println("Headers --> "+headers);
    }

    @Test
    public void validateXmlSchema(){

      given().log().uri()
      .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/xml?key=AIzaSyC-uURG3K9tK3QgTYx3X05rJUekkHYL3X0&input=New York&inputtype=textquery&fields=business_status,formatted_address,geometry,icon,name,photos,place_id,plus_code,types&language=ru")
      .then().body(matchesXsdInClasspath("xmlSchema.xsd")).log().body();
    }

    @Test
    public void validateJsonSchema(){

        given().log().uri()
                .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=AIzaSyC-uURG3K9tK3QgTYx3X05rJUekkHYL3X0&input=New York&inputtype=textquery&fields=business_status,formatted_address,geometry,icon,name,photos,place_id,plus_code,types&language=ru")
                .then().body(matchesJsonSchemaInClasspath("jsonSchema.json")).log().body();
    }

    @Test
    public void getMapOfElementsWithSomeKey(){

        Response response =
                given().spec(requestSpecificationForSwapiTests)
                        .log().uri()
                        .when().get(SWAPI_PATH+SWAPI_GET_PEOPLE);
        Map<String,?> someObject = response.path("results.find {it.name = 'Luke Skywalker'}");
        System.out.println("SomeObject --> "+someObject);

    }

    @Test
    public void getSingleElementWithSomeKey(){

        Response response =
                given().spec(requestSpecificationForSwapiTests)
                .log().uri()
                .when().get(SWAPI_PATH+SWAPI_GET_PEOPLE);
        String url = response.path("results.find {it.name = 'Luke Skywalker'}.url");

        System.out.println("Url --> "+url);

    }

    @Test
    public void getAllElementWithSomeKey(){

        Response response =
                given().spec(requestSpecificationForSwapiTests)
                        .log().uri()
                        .when().get(SWAPI_PATH+SWAPI_GET_PEOPLE);
        List films = response.path("results.findAll {it.films}.name");

        System.out.println("Films --> "+films);


    }
    @Test
    public void validateImageContent(){

      given().log().uri()
              .when().get("https://httpbin.org/image/png")
              .then()
              .statusCode(200)
              .header("content-type","image/png")
              .header("content-length", "8090");


    }
}
