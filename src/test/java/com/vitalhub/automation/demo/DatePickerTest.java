package com.vitalhub.automation.demo;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DatePickerTest {


    @BeforeMethod
    public void setUp() {
        Configuration.browserSize = "2560x1214";
        open("https://practice.expandtesting.com/inputs");
    }


    @Test
    void test1() {

        //$("#input-date").shouldBe(clickable).click();
        String setDate = "2009-11-22";
        setDateByName("input-date", setDate);
        $("#btn-display-inputs").click();
        $("#output-date").shouldHave(text(setDate));
    }


    @Test
    void test2() {
        String setDate = "2023-01-01";
        setDateByName("input-date", setDate);
        $("#btn-display-inputs").click();
        $("#output-date").shouldHave(text(setDate));
    }

    @Test
    void test3() {
        String setDate = "01/01/2024";
        $("#input-date").shouldBe(visible).setValue(setDate);
        $("#btn-display-inputs").click();
        $("#output-date").shouldHave(text("2024-01-01"));
    }

    void setDateByName(String id, String date) {
        executeJavaScript(
                String.format("document.querySelector('#%s').value = '%s'", id, date)
        );
    }
}
