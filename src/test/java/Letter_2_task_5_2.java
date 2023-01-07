// кейс на авторизацию пользователя

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Letter_2_task_5_2 {

    @Test

    public void testAuthUser() {


            JsonPath responseCheckAuth = RestAssured
                    .given()
                    .cookie("auth_cookie", "316039")
                    .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .jsonPath();

            responseCheckAuth.prettyPrint();



    }

}
