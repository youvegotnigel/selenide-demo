package com.vitalhub.automation.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
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
    public void verifyNavigateToBrokenImages() {
        open("/broken_images");
        $x("(//h3|//h1)[1]").shouldHave(text("Broken Images"));

        $("[src='img/avatar-blank.jpg']").shouldBe(Condition.image);
        $("[src='asdf.jpg']").shouldNotBe(Condition.image);
    }

    @Test
    public void verifyNavigateToHover() {
        open("/hovers");
        $x("(//h3|//h1)[1]").shouldHave(text("Hovers"));
    }

    @Test
    public void verifyNavigateToShadowDom() {
        open("/shadowdom");
        $x("(//h3|//h1)[1]").shouldHave(text("Simple template"));
    }

    @Test
    public void verifyNavigateToInputs() {
        open("/inputs");
        $x("(//h3|//h1)[1]").shouldHave(text("Inputs"));
    }

    @Test
    public void verifyNavigateToCheckboxes() {
        open("/checkboxes");
        $x("(//h3|//h1)[1]").shouldHave(text("Checkboxes"));
    }


}
