/*
нужно создать тест, который будет делать GET-запрос на адрес https://playground.learnqa.ru/api/get_json_homework
Полученный JSON необходимо распечатать и изучить.
Мы увидим, что это данные с сообщениями и временем, когда они были написаны.
Наша задача вывести текст второго сообщения.
*/

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;


public class Letter_2_task_1 {

    @Test
    public void testRestAssured() {

        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath(); // получили объект json

        response.prettyPrint(); // вывели json

        String massage2 = response.get("messages.message[1]"); // записали второе сообщение в переменную

        System.out.println(massage2); // вывели второе сообщение





    }
}
