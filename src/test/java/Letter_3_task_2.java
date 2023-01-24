/* Написать тест, который делает запрос на метод: https://playground.learnqa.ru/api/homework_cookie
Этот метод возвращает какую-то cookie с каким-то значением. Необходимо понять что за cookie и с каким значением,
и зафиксировать это поведение с помощью assert.
 */

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Letter_3_task_2 {

    @Test

    public void getCookie() {

        Response response = RestAssured.get("https://playground.learnqa.ru/api/homework_cookie").andReturn();


        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);
        String cookieHomeWork = response.getCookie("HomeWork");
        System.out.println(cookieHomeWork);

        assertTrue(responseCookies.containsKey("HomeWork"), "Response do not key 'HomeWork'");
        assertEquals("hw_value", response.getCookie("HomeWork"), "Response do not key 'HomeWork'");

    }

}

