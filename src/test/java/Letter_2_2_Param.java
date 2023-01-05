import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Letter_2_2_Param {

    // добавление параметра к запросу
    @Test
    public void testRestAssured() {
        Response response = RestAssured
                .given() // ключевое слово. После него перечисляется то, что будет отправлено
                .queryParam("name", "John") // добавление параметра в виде пары ключ, значение через запятую


                .get("https://playground.learnqa.ru/api/hello") // сеттер
                .andReturn(); // экзекьютер
        response.prettyPrint(); // метод класса Response
    }
}
