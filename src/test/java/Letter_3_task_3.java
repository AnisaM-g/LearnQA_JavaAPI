/* Необходимо написать тест, который делает запрос на метод: https://playground.learnqa.ru/api/homework_header
Этот метод возвращает headers с каким-то значением.
Необходимо понять что за headers и с каким значением, и зафиксировать это поведение с помощью assert
 */

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Letter_3_task_3 {

    @Test

    public void getHeaders() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();

        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);
        String headerHomeWork = response.getHeader("x-secret-homework-header");
        System.out.println(headerHomeWork);

        assertTrue(responseHeaders.hasHeaderWithName("x-secret-homework-header"), "Response does not have 'headerHomeWork' header");
        assertEquals("Some secret value", response.getHeader("x-secret-homework-header"), "Response does not have 'headerHomeWork'");




    }

}

