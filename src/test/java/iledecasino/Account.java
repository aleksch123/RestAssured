package iledecasino;

import com.example.mint.config.TestBase;
import com.example.mint.helpers.ApiHelper;
import com.example.mint.helpers.UserHelpers;
import com.example.mint.models.User;
import org.testng.annotations.Test;

import static com.example.mint.constants.Constants.Actions.CHANGE_FAKE_NAME;
import static com.example.mint.constants.Constants.Actions.CHANGE_PASSWORD;
import static com.example.mint.constants.Constants.Path.API_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Account extends TestBase {

    ApiHelper api = new ApiHelper();
    @Test
    public void getAuthCookies(){
        User user = UserHelpers.getRandomValidUser();
        //api.getSessionId(user);

    }
    @Test
    public void noAuthResponse(){

                given().
                header("X-Requested-With", "XMLHttpRequest").
                formParam("fakename","test").
                formParam("v","2.5").
                log().uri().
                when().get("https://development-iledecasino.com/api/Account.changeFakename")
                        .then().assertThat().body(matchesJsonSchemaInClasspath("noAuth.json"))
                        .log().body();

    }

    @Test
    public void changeFakeName(){

        User user = UserHelpers.getRandomValidUser();
        api.registerUser(user);
        String sessionId=api.userLogin(user);

        given().spec(requestSpecificationXMLH).
                header("cookie","PHPSESSID="+sessionId).
                formParam("fakename","test").
                log().uri().
                basePath(API_PATH+ CHANGE_FAKE_NAME).
                when().get()
                .then().assertThat().body(matchesJsonSchemaInClasspath("successResponse.json"))
                .log().body();

    }

    @Test
    public void changePass(){

        User user = UserHelpers.getRandomValidUser();
        api.registerUser(user);
        String sessionId=api.userLogin(user);

        given().spec(requestSpecificationXMLH).
                header("cookie","PHPSESSID="+sessionId).
                formParam("old_password",user.getPassword()).
                formParam("new_password","FgsddS").
                log().uri().
                basePath(API_PATH+ CHANGE_PASSWORD).
                when().get()
                .then().assertThat().body(matchesJsonSchemaInClasspath("successResponse.json"))
                .log().body();

    }




}
