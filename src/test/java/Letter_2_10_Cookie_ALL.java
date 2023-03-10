/* получаем конкретный заголовок */

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Letter_2_10_Cookie_ALL {

        @Test
        public void testRestAssured() {
            Map<String, String> data = new HashMap<>();
            data.put("login","secret_login");
            data.put("password","secret_pass");

            Response response = RestAssured
                    .given()
                    .body(data)
                    .when()
                    .post("https://playground.learnqa.ru/api/get_auth_cookie")
                    .andReturn();

            System.out.println("\nPretty text:");
            response.prettyPrint();

            System.out.println("\nHeaders: ");
            Headers responseHeaders = response.getHeaders();
            System.out.println(responseHeaders);

            System.out.println("\nCookies: ");
            Map<String, String> responseCookies = response.getCookies();
            System.out.println(responseCookies);



        }
    }
