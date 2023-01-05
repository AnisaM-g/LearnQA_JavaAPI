import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Letter_2_4_JsonPath {

        @Test // тег из junit, чтобы обозначить этот метод как тест
        public void testRestAssured() {
            Map<String, String> params = new HashMap<>();
            params.put("name", "John");

            JsonPath response = RestAssured
                    .given()
                    .queryParams(params)

                    .get("https://playground.learnqa.ru/api/hello") // сеттер
                    .jsonPath(); // экзекьютер
            String answer = response.get("answer");
            System.out.println(answer);
        }
    }
