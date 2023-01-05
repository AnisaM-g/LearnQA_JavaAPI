/*  добавляем заголовки */

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Letter_2_8_Headers_ALL {

        @Test
        public void testRestAssured() {
            Map<String, String> headers = new HashMap<>();
            headers.put("MyHeader1","MyValue1");
            headers.put("MyHeader2","MyValue2");

            Response response = RestAssured
                    .given()
                    .headers(headers)
                    .when()

                    .get("https://playground.learnqa.ru/api/show_all_headers") // сеттер
                    .andReturn(); // экзекьютер

            response.prettyPrint();

            Headers responseHeaders = response.getHeaders();
            System.out.println(responseHeaders);



        }
    }
