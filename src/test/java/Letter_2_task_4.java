import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;



public class Letter_2_task_4 {

    @Test
    public void testRestAssured() throws InterruptedException {
        JsonPath response_one = RestAssured
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        response_one.prettyPrint();
        String tokenValue = response_one.get("token"); // получили и запомнили токен
        int seconds = response_one.get("seconds"); // получили и запомнили количество секунд

        JsonPath response_two = RestAssured
                .given()
                .queryParam("token", tokenValue)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        response_two.prettyPrint();

        String status_one = response_two.get("status"); // получили и запомнили первый статус

        String message1 = "Job is NOT ready";
        String message2 = "Job is ready";

        assertTrue(status_one.equals(message1)); // проверили статус ответа

        Thread.sleep(seconds * 1000);

        JsonPath response_three = RestAssured
                .given()
                .queryParam("token",tokenValue)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        response_three.prettyPrint();

        String status_two = response_three.get("status"); // получили и запомнили второй статус

        assertTrue(status_two.equals(message2)); // проверили статус второго ответа

        String result = response_three.get("result"); // получили и запомнили значение поля result


        assertNotNull(result);

    }
}
