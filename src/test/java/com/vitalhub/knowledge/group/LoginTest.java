package com.vitalhub.knowledge.group;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginTest {

    @Test
    public void verifyUserCanLogin() {
        open("https://www.saucedemo.com/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);
        $("").shouldBe(not(Condition.visible));
    }
}
