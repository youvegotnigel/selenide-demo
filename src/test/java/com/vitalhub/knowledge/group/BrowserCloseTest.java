package com.vitalhub.knowledge.group;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BrowserCloseTest {

    @BeforeMethod
    public void before() {
        open("https://google.com");
        Configuration.timeout = 10 * 1000L;
    }


    @Test
    public void test1() {
        $("[name=q]").shouldBe(visible).setValue("fish").pressEnter();

    }


    @Test
    public void test2() {
        switchTo().newWindow(WindowType.TAB);
        open("https://google.com");
        $("[name=q]").shouldBe(visible).setValue("biscuit").pressEnter();

        switchTo().newWindow(WindowType.TAB);
        open("https://google.com");
        $("[name=q]").shouldBe(visible).setValue("munchies").pressEnter();

        WebDriverRunner.getWebDriver().quit();
    }


    @Test
    public void test3() {
        System.out.println("this is test 3");
    }
}
