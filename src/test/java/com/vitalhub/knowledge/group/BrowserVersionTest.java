package com.vitalhub.knowledge.group;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BrowserVersionTest {

    @Test
    public void versionTest() {

        Configuration.browserVersion = "stable";
        Configuration.browser = "chrome";
        Configuration.timeout = 10 * 1000L;

        open("http://52.caseworks.vitalhub.local/");

        $("#logo").shouldBe(image).shouldBe(visible);
        sleep(4000);
    }


    @Test
    public void ts_checkbox() {

        Configuration.browser = "chrome";
        Configuration.timeout = 10 * 1000L;
        open("https://qa.eshrewd.net/admin");

        $("[placeholder=Email]").setValue("****");
        $("[placeholder=Password]").setValue("***").pressEnter();

        $x("//td[text()=\"Test Automation Contract on 27 Mar 2025 02_34_44\"]").shouldBe(visible).click();

        String xpath = "//label[normalize-space()='Activate Contract']";
        String xpathCheckbox = "//label[normalize-space()='Activate Contract']/input[@type='checkbox']";


        $$x(xpathCheckbox).filter(interactable).first().shouldBe(checked);

        sleep(3000);
        $$x(xpath).filter(interactable).first().click();
        $$x(xpathCheckbox).filter(interactable).first().shouldNotBe(checked);
        sleep(3000);
    }
}
