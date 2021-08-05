import com.example.mint.helpers.ApiHelper;
import com.example.mint.helpers.UserHelpers;
import com.example.mint.models.User;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiTests {

    ApiHelper api = new ApiHelper();
    @Test
    public void getAuthCookies(){
        User user = UserHelpers.getRandomValidUser();
        //api.getSessionId(user);

    }
    @Test
    public void changeFakeMane(){

        User user = UserHelpers.getRandomValidUser();
        api.registerUser(user);
        String sessionId=api.userLogin(user);

                given().
                //header("cookie","PHPSESSID="+sessionId).
                header("X-Requested-With", "XMLHttpRequest").
                formParam("fakename","test").
                formParam("v","2.5").
                log().uri().
                when().get("https://development-iledecasino.com/api/Account.changeFakename")
                        .then().assertThat().body(matchesJsonSchemaInClasspath("noAuth.json"))
                        .log().body();

      //  System.out.println(response.prettyPrint());

    }


}
