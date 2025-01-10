package com.vitalhub.automation.demo;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AttributeChangeTest {

    public void waitForAttributeToChange() {


        WebDriverWait wait = new WebDriverWait (WebDriverRunner.getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(By.id(""), "class", "loading")));

    }
}
