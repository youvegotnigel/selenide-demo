package com.vitalhub.automation.demo;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;

public class KeyPressTest {

    @BeforeTest
    public void setUp() {

        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        open("/key_presses");
    }


    @Test
    public void testPressKeysDown() {
        $("#target").press(Keys.DOWN);
        $("#result").shouldHave(text("You entered: DOWN"));
    }

    @Test
    public void testPressKeysEnter() {
        $("#target").press(Keys.ENTER);
        $("#result").shouldHave(text("You entered: ENTER"));
    }

    @Test
    public void testPressEscape() {
        $("#target").pressEscape();
        $("#result").shouldHave(text("You entered: ESCAPE"));
    }


    @Test
    public void testPresEnter() {
        $("#target").pressEscape();
        $("#result").shouldHave(text("You entered: ESCAPE"));

        $("#target").pressEnter();
        $("#result").shouldHave(text("You entered: ENTER"));
    }
}
