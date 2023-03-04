package com.example.TestString;

import com.example.TestString.models.CustomerObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestStringApplicationTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    void contextLoads() {

    }

    @Test
    void shouldGetStringDefault() {
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/getStringTrim");
        assertThat(response)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "application/json",
                        400
                );
        //assertThat(response.body().print()).isEqualTo("null");
        //Assertions.assertEquals(400, response.statusCode());
    }

    @Test
    void shouldGetStringWithOutSpaces() {
        final String str = "  text test  ";
        RequestSpecification request = RestAssured.given();
        Response response = request
                .param("str", str)
                .get("/getStringTrim");
        assertThat(response)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response.body().print()).isEqualTo(str.trim());
        //Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void shouldGetStringSpaces() {
        final String str = " ";
        RequestSpecification request = RestAssured.given();
        Response response = request
                .param("str", str)
                .get("/getStringTrim");

        RequestSpecification request2 = RestAssured.given();
        Response response1 = request2
                .get("/getLastStr");
        assertThat(response1)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response1.body().print()).isEqualTo(" ");
        //Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void shouldGetStringEmpty() {
        final String str = "";
        RequestSpecification request = RestAssured.given();
        Response response = request
                .param("str", str)
                .get("/getStringTrim");

        RequestSpecification request2 = RestAssured.given();
        Response response1 = request2
                .get("/getLastStr");
        assertThat(response1)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response1.body().print()).isEqualTo("");
        //Assertions.assertEquals(200, response.statusCode());
    }


//кейс когда в параметры /getStringTrim передаем значение без пробелов
    @Test
    void shouldGetStringLastStrWithOutSpaces() {
        final String str = "some text";
        RequestSpecification request = RestAssured.given();
        Response response = request
                .param("str", str)
                .get("/getStringTrim");

        RequestSpecification request2 = RestAssured.given();
        Response response2 = request2
                .get("/getLastStr");
        assertThat(response2)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response2.body().print()).isEqualTo(response.body().print());
        //Assertions.assertEquals(200, response.statusCode());
    }


    //кейс когда в параметры /getStringTrim передаем значение с пробелами и ожидаем с пробелами
    @Test
    void shouldGetStringLastStrWithSpaces() {
        final String str = " some text ";
        RequestSpecification request = RestAssured.given();
        Response response = request
                .param("str", str)
                .get("/getStringTrim");

        RequestSpecification request2 = RestAssured.given();
        Response response2 = request2
                .get("/getLastStr");
        assertThat(response2)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response2.body().print()).isEqualTo(str);
        //Assertions.assertEquals(200, response.statusCode());
    }


    //кейс когда в  /getStringTrim не передаем параметры и получаем "Строка еще не была передана"
    @Test
    void shouldGetStringLastStrMessage() {
        RequestSpecification request = RestAssured.given();
        Response response = request
                .get("/getStringTrim");

        RequestSpecification request2 = RestAssured.given();
        Response response2 = request2
                .get("/deleteLastStr");

        RequestSpecification request3 = RestAssured.given();
        Response response3 = request3
                .get("/getLastStr");
        assertThat(response3)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response3.body().print()).isEqualTo("Строка еще не была передана");
        //Assertions.assertEquals(200, response.statusCode());
    }


    @Test
    void shouldGetObject() {
        RequestSpecification request = RestAssured.given();
        Response response = request
                .get("/getObject");
        assertThat(response)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "application/json",
                        200
                );
        final var body = response.body().peek().as(CustomerObject.class);
        assertThat(body.query).isEqualTo("сумка");
        assertThat(body.page).isEqualTo(0);
        assertThat(body.size).isEqualTo(300);
    }

    @Test
    void shouldGetJson() {
        RequestSpecification request = RestAssured.given();
        Response response = request
                .get("/getJson");
        assertThat(response)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "application/json",
                        200
                );
        final var body = response.body().as(JSONObject.class);


    }

    @Test
    void shouldGetInt() {
        RequestSpecification request = RestAssured.given();
        Response response = request
                .get("/getInteger");
        assertThat(response)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response.body().print()).isEqualTo("1");
        //Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void shouldGetIntStr() {
        final Integer str = 123;
        RequestSpecification request = RestAssured.given();
        Response response = (Response) request
                .param("str", str)
                .get("/getInteger");
        assertThat(response)
                .extracting(
                        Response::getContentType,
                        Response::getStatusCode
                ).containsExactly(
                        "text/plain;charset=UTF-8",
                        200
                );
        assertThat(response.body().print()).isEqualTo((str.toString()));
        //Assertions.assertEquals(200, response.statusCode());
    }
}
