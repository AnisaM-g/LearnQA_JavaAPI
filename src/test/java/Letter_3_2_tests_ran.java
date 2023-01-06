// тестовый прогон нескольких тестов

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Letter_3_2_tests_ran {

    @Test
    public void testPast(){
        Response response = RestAssured
                .get("http://playground.learnqa.ru/api/map")
                .andReturn();
        assertEquals(200, response.statusCode(), "Unexpected status code");// проверка  кода ответа.
        // Если код ответа в данном случае равен 200, то тест пройдет, в противном случае выведется текст "nexpected status code"



    }

    @Test
    public void testFailed(){
        Response response = RestAssured
                .get("http://playground.learnqa.ru/api/map2")
                .andReturn();
        assertEquals(200, response.statusCode(), "Unexpected status code");// проверка  кода ответа.
        // Если код ответа в данном случае равен 200, то тест пройдет, в противном случае выведется текст "nexpected status code"



    }
}
