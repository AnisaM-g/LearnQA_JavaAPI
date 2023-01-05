/*  получаем Status Code */

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class Letter_2_7_Status_code_200 {

        @Test
        public void testRestAssured() {
            Response response = RestAssured
                    .get("https://playground.learnqa.ru/api/check_type") // сеттер
                    .andReturn(); // экзекьютер

            int statusCode = response.getStatusCode();
            System.out.println(statusCode);
        }
    }
