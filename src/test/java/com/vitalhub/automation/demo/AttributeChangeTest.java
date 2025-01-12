package com.vitalhub.automation.demo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AttributeChangeTest {

    private static final By BUTTON = By.tagName("button");
    private static final By IMAGE = By.id("image");

    private static final String SRC_ATTRIBUTE_VALUE_BEFORE = "https://via.placeholder.com/300?text=Original";
    private static final String SRC_ATTRIBUTE_VALUE_AFTER = "https://via.placeholder.com/300?text=Changed";


    @BeforeTest
    public void setUp() {

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/src-change.html";
        //Configuration.browserSize = "2560x1214";
        Configuration.browserSize = "1440x900";
        open(filePath);
    }


    @Test
    public void printAttributeChange() {

        $x("//h1").shouldHave(text("Change Image Source"));
        System.out.println("SRC Attribute Value Before Click = " + $(IMAGE).getDomAttribute("src"));

        $(BUTTON).click();
        System.out.println("SRC Attribute Value After Click = " + $(IMAGE).getDomAttribute("src"));

        sleep(5000);
        System.out.println("SRC Attribute Value After 5 seconds = " + $(IMAGE).getDomAttribute("src"));
    }

    @Test
    public void verifyAttributeChange() {

        $x("//h1").shouldHave(text("Change Image Source"));
        Assert.assertEquals($(IMAGE).getDomAttribute("src"), SRC_ATTRIBUTE_VALUE_BEFORE);

        $(BUTTON).click();
        waitForAttributeToChange(IMAGE, "src", "https://via.placeholder.com/300?text=Original");

        Assert.assertEquals($(IMAGE).getDomAttribute("src"), SRC_ATTRIBUTE_VALUE_AFTER);
        System.out.println("SRC Attribute Value = " + $(IMAGE).getDomAttribute("src"));
    }

    public void waitForAttributeToChange(By by, String attribute, String currentAttributeValue) {

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(by, attribute, currentAttributeValue)));
    }
}
