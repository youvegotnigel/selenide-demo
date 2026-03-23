package com.vitalhub.knowledge.group;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ConditionTest {


    @Test
    public void test() {

        // Set Selenide configuration for browser and timeout
        Configuration.browser = "chrome";
        Configuration.timeout = 5 * 1000L;

        // Open the application URL
        open("http://vhvmdvtrtqaapp7.vh.local:8080/treat/logon#");

        // Verify the TREAT logo is displayed as an image
        $("[alt='TREAT Logo']").shouldBe(Condition.image);

        // Verify the TREAT version label is visible on the page
        $(byText("TREAT Version 5.32.0.0")).shouldBe(Condition.visible);

        // Check that the "Remember Organization" checkbox is initially unchecked
        $("#rememberOrg").shouldNotBe(Condition.checked);

        // Click the "Remember Organization" checkbox to enable it
        $("#rememberOrg").click();

        // Verify the checkbox is now checked
        $("#rememberOrg").shouldBe(Condition.checked);

        // Enter the Organization ID and press Enter
        $("#orgUserID").setValue("NSDCS").pressEnter();

        // Verify the Organization ID field is now read-only
        $("#orgUserID").shouldBe(Condition.readonly);

        // Verify the Username field is now visible
        $("#username").shouldBe(Condition.visible);

        // Click the "Change Organization ID" link
        $(byTagAndText("a", "Change Organization ID")).click();

        // Verify the Organization ID field is now editable
        $("#orgUserID").shouldBe(Condition.editable);

        // Verify the Username field is now hidden
        $("#username").shouldBe(Condition.hidden);

    }
}
