import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Letter_2_1_Hello {

        @Test // тег из junit, чтобы обозначить этот метод как тест
        public void testRestAssured() {
            Response response = RestAssured /* используем переменную RestAssured, которую мы импортировали на первой строке.
                                                Она имеет внутри те функции, которые понадобятся.
                                                Объявляется и инициируется переменная response класса Response*/

                    .get("https://playground.learnqa.ru/api/hello") // сеттер
                    .andReturn(); // экзекьютер

            response.prettyPrint(); // метод класса Response
        }
    }
