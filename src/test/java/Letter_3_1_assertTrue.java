import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Letter_3_1_assertTrue {

    @Test
    public void testRestAssured(){
        Response response = RestAssured
                .get("http://playground.learnqa.ru/api/mp")
                .andReturn();
        assertTrue(response.statusCode() == 200, "Unexpected status code");// проверка  кода ответа.
        // Если код ответа в данном случае равен 200, то тест пройдет, в противном случае выведется текст "nexpected status code"



    }
}
