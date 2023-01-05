import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Letter_1_task_4 {

    @Test
    public void test(){
        Response response = RestAssured // в данной переменной будет храниться информация об ответе на запрос
                .get("https://playground.learnqa.ru/api/get_text") // создаем get-запрос на адрес
                .andReturn(); // возвращаем результат
        response.prettyPrint(); // распечатываем текст ответа в json-формате
    }
}