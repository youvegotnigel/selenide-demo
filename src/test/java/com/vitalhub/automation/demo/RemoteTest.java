package com.vitalhub.automation.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class RemoteTest {

    @BeforeTest
    public void setUp() throws MalformedURLException {
        Configuration.remote = "http://localhost:4444";
        Configuration.browser = "firefox";
        Configuration.browserVersion = "119.0";
        //Configuration.browserSize = "2560x1214";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setBrowserName("firefox");

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

        Configuration.browserCapabilities = capabilities;

        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
        WebDriverRunner.setWebDriver(driver);

        System.out.println("Session ID = " + WebDriverRunner.driver().getSessionId());
        open("https://www.saucedemo.com/");
    }


    @Test
    public void verifyUserCanLogin() {
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        sleep(5000);
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}
