// негативные тесты на авторизацию пользователя

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Letter_3_4_UserAuthTest_negativ {

    @Test

    public void testAuthUser(){
        Map<String, String> authData = new HashMap<>();
        authData.put("email","vinkotov@example.com");
        authData.put("password","1234");

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();

        // разберем получившуюся строку на несколько нужных нам параметров

        Map<String, String> cookies = responseGetAuth.getCookies();
        Headers headers = responseGetAuth.getHeaders();
        int userIdOnAuth = responseGetAuth.jsonPath().getInt("user_id");


        assertEquals(200, responseGetAuth.statusCode(), "Expected status code"); // проверяем, что пришел статус код 200
        assertTrue(cookies.containsKey("auth_sid"), "Response doesn't have 'auth_sid' cookie"); // проверяем, что во всем списке, полученных куки содержится куки "auth_sid"
        assertTrue(headers.hasHeaderWithName("x-csrf-token"), "Response doesn't have 'x-csrf-token' header"); // проверяем, что в хедерах присутствует хедер с определенным именем
        assertTrue(responseGetAuth.jsonPath().getInt("user_id") > 0, "User id should be greator that 0");

        JsonPath responseCheckAuth = RestAssured
                .given()
                .header("x-csrf-token", responseGetAuth.getHeader("x-csrf-token"))
                .cookie("auth_sid", responseGetAuth.getCookie("auth_sid"))
                .get("https://playground.learnqa.ru/api/user/auth")
                .jsonPath();

        int userIdOnCheck = responseCheckAuth.getInt("user_id");
        assertTrue(userIdOnCheck > 0, "Unexpected user id " + userIdOnCheck);
        assertEquals(
                userIdOnAuth,
                userIdOnCheck,
                "user id from auth request is not equal to user_id from check request");

    }

    @ParameterizedTest
    @ValueSource(strings = {"cookie", "headers"})

    public void testNegativeAuthUser(String condition){
        Map<String, String> authData = new HashMap<>();
        authData.put("email","vinkotov@example.com");
        authData.put("password","1234");

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();

        Map<String, String> cookies = responseGetAuth.getCookies();
        Headers headers = responseGetAuth.getHeaders();

        /* RequestSpecification - специальный класс, объектом которого является переменная.
        С помощью этого механизма, мы можем выполнять нужный нам запрос объявляя его по частям.

         */

        RequestSpecification spec = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/api/user/auth"); // задали урл на который будет отправлять запрос

        if(condition.equals("cookie")) { // условие: Если нам пришли куки, то подставляем только их, если пришли заголовки, то только их. В противном случае - исключение

            spec.cookie("auth_sid", cookies.get("auth_sid"));
        } else if (condition.equals("headers")) {
            spec.header("x-csrf-token", headers.get("x-csrf-token"));
        } else{
            throw new IllegalArgumentException("Condition value is known: " + condition); // исключение
        }


        JsonPath responseForChek = spec.get().jsonPath(); // от переменной spec вызываем метод get, который задали выше и ответ парсим в переменную как json

        assertEquals(0, responseForChek.getInt("user_id"), "user_id should be 0 for unauth request");

    }

}
