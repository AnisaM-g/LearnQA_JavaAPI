import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Letter_2_3_Params {

    // добавление параметра к запросу
        @Test
        public void testRestAssured() {
            Map<String, String> params = new HashMap<>();
            params.put("name", "John");

            Response response = RestAssured
                    .given() // ключевое слово. После него перечисляется то, что будет отправлено
                    .queryParams(params) // добавление переменной для параметров


                    .get("https://playground.learnqa.ru/api/hello") // сеттер
                    .andReturn(); // экзекьютер
            response.prettyPrint(); // метод класса Response
        }
    }
