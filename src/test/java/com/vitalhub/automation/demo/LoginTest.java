package com.vitalhub.automation.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.logging.Level;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


/*
    ChatGPT Public Link
    https://chatgpt.com/share/67909f0f-bf78-8012-95e4-cfe4bd312801

 */

public class LoginTest {

    @Test
    public void verifyUserCanLoginChrome() {
        Configuration.browser = "chrome";
        open("https://www.saucedemo.com/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);

        System.out.println("browser :: " + ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserName());
        System.out.println("browserVersion :: " + ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserVersion());
    }

    @Test
    public void verifyUserCanLoginEdge() {
        Configuration.browser = "edge";
        open("https://www.saucedemo.com/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);

        System.out.println("browser :: " + ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserName());
        System.out.println("browserVersion :: " + ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserVersion());
    }

    @Test
    public void verifyUserCanLoginFireFox() {
        Configuration.browser = "firefox";
        open("https://www.saucedemo.com/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);

        System.out.println("browser :: " + ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserName());
        System.out.println("browserVersion :: " + ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserVersion());
    }

    @Test
    public void verifyUserCanNavigate() {
        Configuration.browser = "chrome";
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", logPrefs);

        Configuration.baseUrl = "https://www.saucedemo.com";
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserVersion = "128";

        open("/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter();
        $(byTagAndText("div","Swag Labs")).shouldBe(Condition.visible);

        open("/cart.html");
        $(byTagAndText("span","Your Cart")).shouldBe(Condition.visible);


        Logs logs = getWebDriver().manage().logs();
        printLog(logs.get(LogType.BROWSER));
    }


    public void printLog(LogEntries entries) {
        System.out.printf("%s log entries found\n\n", entries.getAll().size());
        for (LogEntry entry : entries) {
            System.out.printf("%s %s %s\n",
                    new Date(entry.getTimestamp()), entry.getLevel(), entry.getMessage()
            );
        }
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWindow();
    }
}
