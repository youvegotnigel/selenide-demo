package com.vitalhub.knowledge.group;

import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class NeverUseImplicitWaitTest {

    @Test
    void is_waits_and_returns_false() {
        open("https://selenide.org/2024/02/07/selenide-7.1.0/#if-with-timeout");

        //WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        System.out.println("currentTimeMillis: " + System.currentTimeMillis());
        System.out.println(LocalDateTime.now());
        boolean isDisplayed = $("#nope").is(exist, Duration.ofSeconds(9));
        System.out.println("currentTimeMillis: " + System.currentTimeMillis());
        System.out.println(LocalDateTime.now());

        assertThat(isDisplayed).as("Expect element #nope to not exist").isFalse();

        $$("#force-click").shouldHave(sizeGreaterThanOrEqual(2), Duration.ofSeconds(20));
    }
}
