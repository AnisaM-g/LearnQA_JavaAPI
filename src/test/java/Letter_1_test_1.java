import io.restassured.RestAssured;
import io.restassured.response.Response; //импортируем некоторые классы фреймворка restassured
import org.junit.jupiter.api.Test;

public class Letter_1_test_1 {
    @Test
    public void test(){
        Response response = RestAssured // в данной переменной будет храниться информация об ответе на запрос
                .get("https://playground.learnqa.ru/api/hello") // создаем get-запрос на адрес
                .andReturn(); // возвращаем результат
        response.prettyPrint(); // распечатываем текст ответа в json-формате
    }
}
