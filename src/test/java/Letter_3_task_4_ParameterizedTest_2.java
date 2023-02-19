/* User Agent - это один из заголовков, позволяющий серверу узнавать, с какого девайса и браузера пришел запрос.
Он формируется автоматически клиентом, например браузером.
Определив, с какого девайса или браузера пришел к нам пользователь
мы сможем отдать ему только тот контент, который ему нужен.
Наш разработчик написал метод: https://playground.learnqa.ru/ajax/api/user_agent_check

Метод определяет по строке заголовка User Agent следующие параметры:
device - iOS или Android
browser - Chrome, Firefox или другой браузер
platform - мобильное приложение или веб

Если метод не может определить какой-то из параметров, он выставляет значение Unknown.

Наша задача написать параметризированный тест.
Этот тест должен брать из дата-провайдера User Agent и ожидаемые значения,
GET-делать запрос с этим User Agent и
убеждаться, что результат работы нашего метода правильный -
т.е. в ответе ожидаемое значение всех трех полей.

Список User Agent и ожидаемых значений можно найти
1. ============

User Agent:

'Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30'

Expected values:

'platform': 'Mobile', 'browser': 'No', 'device': 'Android'


2. ============

User Agent:

'Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1'

Expected values:

'platform': 'Mobile', 'browser': 'Chrome', 'device': 'iOS'


3. ============

User Agent:

'Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)'

Expected values:

'platform': 'Googlebot', 'browser': 'Unknown', 'device': 'Unknown'



4. ============

User Agent:

'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0'

Expected values:

'platform': 'Web', 'browser': 'Chrome', 'device': 'No'


5. ============

User Agent:

'Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1'

Expected values:

'platform': 'Mobile', 'browser': 'No', 'device': 'iPhone'



На самом деле метод не всегда работает правильно.
Ответом к задаче должен быть список из тех User Agent,
которые вернули неправильным хотя бы один параметр,
с указанием того, какой именно параметр неправильный.
*/

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Letter_3_task_4_ParameterizedTest_2 {
    @ParameterizedTest
    @CsvSource({
            "'Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30', 'Mobile', 'No', 'Android'",
            "'Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1', 'Mobile', 'Chrome', 'iOS'",
            "'Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)', 'Googlebot', 'Unknown', 'Unknown'",
            "'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0', 'Web', 'Chrome', 'No'",
            "'Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1', 'Mobile', 'No', 'iPhone'"
    })
    public void testUserAgent(String User_Agent, String platform, String browser, String device){

        Map<String, String> headerUserAgent = new HashMap();
        headerUserAgent.put("User-Agent", User_Agent);

        Map<String, String> platforms = new HashMap<>();
        platforms.put("platform", platform);

        Map<String, String> browsers = new HashMap<>();
        browsers.put("brawser", browser);

        Map<String, String> devices = new HashMap<>();
        devices.put("device", device);



        JsonPath response = RestAssured
                .given()
                .headers(headerUserAgent)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/user_agent_check")
                .jsonPath();



        //response.prettyPrint();

        String responsePlatform = response.getString("platform");
        assertEquals(platforms.get("platform"), responsePlatform, headerUserAgent.get("User-Agent"));

        String responseBrawser = response.getString("brawser");
        assertEquals(browsers.get("browser"), responseBrawser, headerUserAgent.get("User-Agent"));

        String responseDevice = response.getString("device");
        assertEquals(devices.get("device"), responseDevice, headerUserAgent.get("User-Agent"));

    }
}