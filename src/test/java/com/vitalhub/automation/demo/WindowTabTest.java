package com.vitalhub.automation.demo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class WindowTabTest {

    @Test
    public void tabTest() {
        open("https://the-internet.herokuapp.com/windows");
        $("h3").shouldHave(text("Opening a new window"), Duration.ofSeconds(10));
        $(byTagAndText("a", "Click Here")).click();
        sleep(5000);
        System.out.println("CURRENT URL :: " + url());

        switchTo().window(1);
        System.out.println("CURRENT URL AFTER SWITCH :: " + url());
    }

    @Test
    public void windowTest() {

        Selenide.open("https://google.com");
        Selenide.executeJavaScript("window.open()");
        Selenide.switchTo().window(1);
        Selenide.open("https://facebook.com");
        Selenide.switchTo().window(0);
    }

    @Test
    public void windowTest2() {

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/window.html";
        Configuration.browserSize = "1440x900";
        open(filePath);

        $(byTagAndText("button", "Open New Window")).click();
        sleep(5000);
        System.out.println("TITLE :: "+ title());

        switchTo().window(1);
        System.out.println("TITLE :: "+ title());
    }
}
