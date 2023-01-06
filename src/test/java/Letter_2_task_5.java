// кейс на авторизацию пользователя

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Letter_2_task_5 {

    @Test

    public void testAuthUser() {
        String[] password = {"123456", "12345679", "qwerty", "12345678", "111111", "1234567890", "1234567", "password", "123123", "987654321",
                "qwertyuiop", "mynoob", "123321", "666666", "18atcskd2w", "7777777", "1q2w3e4r", "654321", "555555", "3rjs1la7qe",
                "google","1q2w3e4r5t","123qwe","zxcvbnm","1q2w3e"};

        int N = password.length;

        for (int i = 0; i<=N-1; i++){

            Map<String, String> authData = new HashMap<>();
            authData.put("login", "super_admin");
            authData.put("password", password[i]);

            System.out.println(password[i]);

            Response responseGetAuth = RestAssured
                    .given()
                    .body(authData)
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();

            Map<String, String> cookies = responseGetAuth.getCookies();

            //assertEquals(200, responseGetAuth.statusCode(), "Expected status code"); // проверяем, что пришел статус код 200
            assertTrue(cookies.containsKey("auth_cookie"), "Response doesn't have 'auth_cookie' cookie"); // проверяем, что во всем списке, полученных куки содержится куки "auth_cookie"

            //String auth_cookie = ("auth_cookie"); // получаем и сохряняем нужную cookie
            System.out.println("вот   " + responseGetAuth.getCookie("auth_cookie"));

            Response responseCheckAuth = RestAssured
                    .given()
                    .cookie("auth_cookie", responseGetAuth.getCookie("auth_cookie"))
                    .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();

            responseCheckAuth.prettyPrint();

            String message = "You are authorized";

            if(message.equals(responseCheckAuth)){
                System.out.println(password[i]);
                System.out.println(responseCheckAuth);
                break;

            }
            else {
                System.out.println(i);
            }

        }


    }

}
