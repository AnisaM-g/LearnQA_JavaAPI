// использование метода assertEquals

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Letter_3_2_assertEquals {

    @Test
    public void testRestAssured(){
        Response response = RestAssured
                .get("http://playground.learnqa.ru/api/map")
                .andReturn();
        assertEquals(200, response.statusCode(), "Unexpected status code");// проверка  кода ответа.
        // Если код ответа в данном случае равен 200, то тест пройдет, в противном случае выведется текст "U    §nexpected status code"



    }
}
