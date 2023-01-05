/*
Необходимо написать тест, который создает GET-запрос на адрес из предыдущего задания: https://playground.learnqa.ru/api/long_redirect
На самом деле этот URL ведет на другой, который мы должны были узнать на предыдущем занятии. Но этот другой URL тоже куда-то редиректит.
И так далее. Мы не знаем заранее количество всех редиректов и итоговый адрес.
Наша задача — написать цикл, которая будет создавать запросы в цикле, каждый раз читая URL для редиректа из нужного заголовка.
И так, пока мы не дойдем до ответа с кодом 200.
Ответом должна быть ссылка на тест в вашем репозитории и количество редиректов.
*/

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class Letter_2_task_3 {

        @Test
        public void testRestAssured() {
            int k = 0;
            String locationHeader = "https://playground.learnqa.ru/api/long_redirect";
            int statusCode = 0;

            do{
                Response response = RestAssured
                        .given()
                        .redirects()
                        .follow(false)
                        .when()
                        .get(locationHeader)
                        .andReturn();

                locationHeader = response.getHeader("Location"); // получили URL

                System.out.println(locationHeader); // вывожу на печать урл, но можно и закоментить

                statusCode = response.getStatusCode();

                k = k+1;
           }
           while(statusCode != 200);

           System.out.print("Status Code = ");
           System.out.println(statusCode);

           System.out.print("Количество редиректов = ");
           System.out.println(k-1);

        }
    }
