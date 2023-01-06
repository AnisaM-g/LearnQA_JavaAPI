// @BeforEach и @AfterEach - теги. код внутри них будет автоматически вызываться перед или после запуска тестов в том классе, в котором они написаны


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Letter_3_5_UserAuthTest_BeforEach {


    String cookie;
    String header;
    int userIdOnAuth;

    @BeforeEach // здесь пропишем логику с которой должны начинаться все тесты в классе. Например с подготовкой каких-то тестовых данных.

    public void loginUser(){
        Map<String, String> authData = new HashMap<>();
        authData.put("email","vinkotov@example.com");
        authData.put("password","1234");

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();

        this.cookie = responseGetAuth.getCookie("auth_sid");
        this.header = responseGetAuth.getHeader("x-csrf-token");
        this.userIdOnAuth = responseGetAuth.jsonPath().getInt("user_id");

/*
this - специальный указатель позволяющий делать переменную полем класса и как следствие передавать её значение
из одной функции в другие.
 */
    }


    @Test


    public void testAuthUser(){

        JsonPath responseCheckAuth = RestAssured
                .given()
                .header("x-csrf-token", this.header)
                .cookie("auth_sid", this.cookie)
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

        RequestSpecification spec = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/api/user/auth"); // задали урл на который будет отправлять запрос

        if(condition.equals("cookie")) { // условие: Если нам пришли куки, то подставляем только их, если пришли заголовки, то только их. В противном случае - исключение

            spec.cookie("auth_sid", this.cookie);
        } else if (condition.equals("headers")) {
            spec.header("x-csrf-token", this.header);
        } else{
            throw new IllegalArgumentException("Condition value is known: " + condition); // исключение
        }


        JsonPath responseForChek = spec.get().jsonPath(); // от переменной spec вызываем метод get, который задали выше и ответ парсим в переменную как json

        assertEquals(0, responseForChek.getInt("user_id"), "user_id should be 0 for unauth request");

    }

}
