package com.vitalhub.automation.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class NavigationTest {

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
    }

    @Test
    public void verifyBrokenImages() {
        open("/broken_images");
        $x("//h3").shouldHave(text("Broken Images"));

        $("[src='img/avatar-blank.jpg']").shouldBe(Condition.image);
        $("[src='asdf.jpg']").shouldNotBe(Condition.image);
    }

    @Test
    public void verifyHovering() {
        open("/hovers");
        $x("//h3").shouldHave(text("Hovers"));

        $$("[alt='User Avatar']").get(1).hover();
        $(byText("name: user2")).shouldBe(Condition.visible);

        $$("[alt='User Avatar']").get(2).hover();
        $(byText("name: user3")).shouldBe(Condition.visible);

        $$("[alt='User Avatar']").get(0).hover();
        $(byText("name: user1")).shouldBe(Condition.visible);
    }

}
