import config.TestBase;
import org.testng.annotations.Test;

import static constants.Constants.Actions.*;
import static io.restassured.RestAssured.given;

public class JsonPlaceholderTest extends TestBase {

    @Test
    public void get(){

        given()
        .queryParam("postId",1)
        .when()
        .get(JASONPLACEHOLDER_GET)
        .then().log().body().statusCode(200);
    }

    @Test
    public void put(){
        String putBodyJson="{\n" +
                "\"id\":1,\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1\n" +
                "}";
        given()
        .body(putBodyJson)
        .log()
        .uri()
        .when().put(JASONPLACEHOLDER_PUT)
        .then().log().body().statusCode(200);
    }

    @Test
    public void delete(){

        given()
                .log()
                .uri()
                .when().delete(JASONPLACEHOLDER_DELETE)
                .then().log().body().statusCode(200);
    }

    @Test

    public void postWithJson(){

        String postBodyJson="{\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1\n" +
                "}";
        given()
                .body(postBodyJson)
                .log()
                .uri()
                .when().post(JASONPLACEHOLDER_POST)
                .then().spec(responseSpecificationForPost).log().body();

    }

    @Test
    public void PostWithXml(){
        String postXmlBody="<?xml version=\"1.0\"?>\n" +
                "<Company>\n" +
                "  <Employee>\n" +
                "      <FirstName>Tanmay</FirstName>\n" +
                "      <LastName>Patil</LastName>\n" +
                "      <ContactNo>1234567890</ContactNo>\n" +
                "      <Email>tanmaypatil@xyz.com</Email>\n" +
                "      <Address>\n" +
                "           <City>Bangalore</City>\n" +
                "           <State>Karnataka</State>\n" +
                "           <Zip>560212</Zip>\n" +
                "      </Address>\n" +
                "  </Employee>\n" +
                "</Company>";

        given().spec(requestSpecificationXml)
                .body(postXmlBody)
                .log()
                .uri()
                .when().post("")
                .then().spec(responseSpecificationForGet)
                .log().body().statusCode(200);

    }
}
