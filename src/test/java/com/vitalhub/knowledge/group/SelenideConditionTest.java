package com.vitalhub.knowledge.group;

import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideConditionTest {

    @BeforeMethod
    public void setUp() {

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/selenide-conditions-demo.html";
        Configuration.browserSize = "1440x900";
        open(filePath);

        $x("//h1").shouldHave(text("\uD83C\uDF1F Selenide Conditions Demo Page"));
    }

    @Test
    public void testAnimated() {
        $("#loadingSpinner").shouldBe(animated);
    }


    @Test
    public void testClickable() {
        $("#submitButton").shouldBe(clickable).click();
        String alertText = switchTo().alert().getText();
        System.out.println("Alert Text ::: " + alertText);
        switchTo().alert().accept();
    }




}
