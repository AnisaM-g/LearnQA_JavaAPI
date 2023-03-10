// тестовый прогон нескольких тестов

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Letter_3_2_tests_ran_HelloWorld {

    @Test
    public void testHelloMethodWithoutName(){
        JsonPath response = RestAssured
                .get("http://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.getString("answer");
        assertEquals("Hello, someone", answer, "The answer is not expected");



    }

    @Test
    public void testHelloMethodWithName(){
        String name = "Username";

        JsonPath response = RestAssured
                .given()
                .queryParam("name", name)
                .get("http://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.getString("answer");
        assertEquals("Hello, "+ name, answer, "The answer is not expected");



    }
}
