package com.vitalhub.automation.utils;

import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Select2Test {

    @BeforeMethod
    public void setUp() {

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/select2.html";
        //Configuration.browserSize = "2560x1214";
        Configuration.browserSize = "1440x900";
        open(filePath);

        $x("//h1").shouldHave(text("Select a Time Zone:"));
    }


    @Test
    public void testSelect2() {

        Select2Helper select2Helper = new Select2Helper($("#select2-timezone-container"));
        select2Helper.selectOption("Nevada");

        Assert.assertEquals(select2Helper.getSelectedOption(), "Nevada");
    }
}
