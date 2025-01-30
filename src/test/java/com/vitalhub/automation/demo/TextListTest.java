package com.vitalhub.automation.demo;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextListTest {

    @Test
    public void test() {

        open("https://the-internet.herokuapp.com/tables");
        $$x("//table[@id='table1']/tbody/tr/td[2]").forEach(e -> e.shouldNotHave(text("Jason")));
    }
}
