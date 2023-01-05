/* передаем полученные cookie в последующие запросы */

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Letter_2_13_Cookie {

        @Test
        public void testRestAssured() {
            Map<String, String> data = new HashMap<>();
            data.put("login","secret_login2");
            data.put("password","secret_pass");

            Response responseForGet = RestAssured // получаем куки
                    .given()
                    .body(data)
                    .when()
                    .post("https://playground.learnqa.ru/api/get_auth_cookie")
                    .andReturn();

            String responseCookie = responseForGet.getCookie(("auth_cookie"));

            Map<String, String> cookies = new HashMap<>();
            if(responseCookie != null) {
                cookies.put("auth_cookie", responseCookie);
            }

            Response responseForChek = RestAssured
                    .given()
                    .body(data)
                    .cookies(cookies)
                    .when()
                    .post("https://playground.learnqa.ru/api/check_auth_cookie")
                    .andReturn();


            responseForChek.print();


        }
    }
