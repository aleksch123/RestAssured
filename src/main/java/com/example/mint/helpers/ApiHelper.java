package com.example.mint.helpers;

import com.example.mint.models.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class ApiHelper {

    public String registerUser(User user){
        try {
            return given().log().ifValidationFails().
                    when().
                    header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                    formParam("v", "2.0").
                    formParam("email", user.getEmail()).
                    formParam("login", user.getEmail()).
                    formParam("password", user.getPassword()).
                    formParam("last_name", user.getLastName()).
                    formParam("birth_date", "1968-01-01").
                    formParam("last_name", user.getLastName()).
                    formParam("sex", "2").
                    formParam("currency", "EUR").
                    post("https://development-iledecasino.com/api/auth.register").
                    then().log().ifValidationFails().
                    assertThat().statusCode(200).
                    body(not(containsString("error"))).extract().cookie("PHPSESSID");
        } catch (Exception e) {
            return "";
        }

    }

    public String userLogin(User user){
        try {
            return given().log().ifValidationFails().
                    when().
                    header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                    formParam("v", "2.5").
                    formParam("password", user.getPassword()).
                    formParam("login", user.getEmail()).
                    post("https://development-iledecasino.com/api/auth.login").
                    then().log().ifValidationFails().
                    assertThat().statusCode(200).
                    body(not(containsString("error"))).extract().cookie("PHPSESSID");
        } catch (Exception e) {
            return "";
        }


    }
}
