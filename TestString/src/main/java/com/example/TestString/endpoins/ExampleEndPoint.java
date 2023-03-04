package com.example.TestString.endpoins;
import com.example.TestString.models.CustomerObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;



@RestController
public class ExampleEndPoint {

    static String lastSrt;

    @GetMapping(path = "/getString")
    public String getString() {
        return "Hello";
    }

    //Метод для возврата введенной строки, если строка не введена возвращать null

    @GetMapping(path = "/getStringTrim")
    public String getStringNotNull(@RequestParam() String str) {
        lastSrt = str;
        return str.trim();
    }

    //Метод для возврата прдыдущей отредактированной строки из метода выше

    @GetMapping(path = "/getLastStr")
    public String getLastSrt() {
        if (lastSrt != null) {
            return lastSrt;                                                                 // или так можно
        } else
            return "Строка еще не была передана";
        //return Objects.requireNonNullElse(lastSrt, "Строка еще не была передана"); // или так можно
    }

    //Метод  для очистки записи о последней отредактированной строке

    @DeleteMapping(path = "/deleteLastStr")
    public void deleteLastStr() {
        lastSrt = null;
    }

    //Метод возврата json обьекта с введенными данными

    @GetMapping(path = "/getJson")
    public JSONObject getJson() {
        JSONObject newJsonObject = new JSONObject();
        newJsonObject.put("query", "куртка");
        newJsonObject.put("page", "0");
        newJsonObject.put("size", "125");
        return newJsonObject;
    }


    //Метод  для возврата обькта класса с введенными данными

    @GetMapping(path = "/getObject")
    public CustomerObject getObject(){
        return new CustomerObject("сумка", 0, 300);
    }

    @GetMapping(path = "/getInteger")
    public String getString(@RequestParam(defaultValue = "1") Integer str) {
        return str.toString();
    }

}

