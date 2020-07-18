import config.TestBase;
import org.testng.annotations.Test;
import static constants.Constants.Actions.SWAPI_GET_PEOPLE;
import static io.restassured.RestAssured.given;

public class Test1 extends TestBase {
  @Test
    public void firstTest(){

        given().
        log().uri().
        when().get(SWAPI_GET_PEOPLE+"1").
        then().log().body().statusCode(200);
    }
}
