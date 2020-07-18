import config.TestBase;
import org.testng.annotations.Test;
import static constants.Constants.Actions.JASONPLACEHOLDER_GET;
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
}
