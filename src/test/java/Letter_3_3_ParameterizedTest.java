// параметризованные тесты

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Letter_3_3_ParameterizedTest {

    @ParameterizedTest // тег означает, что в данный тест передаются параметры и он запускается столько раз, сколько наборов параметров у нас существует
    @ValueSource(strings = {"", "Joht", "Pete"}) // один набор параметров, и 3 значения в нем

    public void testHelloMethodWithoutName(String name){ // в параметр name будут передаваться значения из набора
        Map<String, String> queryParams = new HashMap<>(); // создаем пустой HashMap в котором будут храниться параметры

        if (name.length() > 0){
            queryParams.put("name", name);
        } // обрабатываем ситуацию с пустой строкой. Если имя больше 0 символов, то мы передаем параметр, в противном случае не передаем и HashMap остается пустым

        JsonPath response  = RestAssured
                .given()
                .queryParams(queryParams) //
                .get("http://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.getString("answer");
        String expectedName = (name.length() > 0) ? name : "someone"; // тернарный оператор
        assertEquals("Hello, " + expectedName, answer, "The answer is not expected");

    }

}
