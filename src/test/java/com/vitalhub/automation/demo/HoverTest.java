package com.vitalhub.automation.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class HoverTest {

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
    }

    @Test
    public void verifyHovering() {
        open("/hovers");
        $x("(//h3|//h1)[1]").shouldHave(text("Hovers"));

        $$("[alt='User Avatar']").get(1).hover();
        $(byText("name: user2")).shouldBe(Condition.visible);

        $$("[alt='User Avatar']").get(2).hover();
        $(byText("name: user3")).shouldBe(Condition.visible);

        $$("[alt='User Avatar']").get(0).hover();
        $(byText("name: user1")).shouldBe(Condition.visible);
    }
}
