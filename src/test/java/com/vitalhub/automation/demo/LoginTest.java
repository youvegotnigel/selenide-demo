package com.vitalhub.automation.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class LoginTest {

    @Test
    public void verifyUserCanLogin() {
        open("https://www.saucedemo.com/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);
    }

    @Test
    public void verifyUserCanNavigate() {
        Configuration.baseUrl = "https://www.saucedemo.com";
        open("/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);

        open("/cart.html");
        $(byTagAndText("span","Your Cart")).shouldBe(Condition.visible);
    }
}
