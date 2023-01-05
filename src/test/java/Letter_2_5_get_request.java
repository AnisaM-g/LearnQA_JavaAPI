/*  посылаем get-запрос и распечатываем его.
    добавляем query-параметры */

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Letter_2_5_get_request {

    // добавление параметра к запросу
        @Test
        public void testRestAssured() {

            Response response = RestAssured
                    .given()
                    .queryParams("param1", "value1")
                    .queryParams("param2", "value2")
                    .get("https://playground.learnqa.ru/api/check_type") // сеттер
                    .andReturn(); // экзекьютер
            response.print(); // метод класса Response
        }
    }
