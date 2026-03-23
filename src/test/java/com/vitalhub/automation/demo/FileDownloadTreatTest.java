package com.vitalhub.automation.demo;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;


public class FileDownloadTreatTest {

    @Test
    public void fileDownloadTest() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", getChromiumPreferences());
        chromeOptions.setCapability("se:downloadsEnabled", true);
        chromeOptions.addArguments("--disable-features=InsecureDownloadWarnings");
        chromeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://vhvmdvtrtqaapp7.vh.local:8080");  // Allow insecure origins from TREAT

        Configuration.timeout = 10 *1000L;
        Configuration.fileDownload = FOLDER;

        Configuration.browserCapabilities = chromeOptions;
        Configuration.browser = "chrome";

        Configuration.remote = "http://10.127.91.203:4444";

        open("http://vhvmdvtrtqaapp7.vh.local:8080/treat/logon");
        $("#orgUserID").setValue("*****");
        $("#btnContinue").click();


        $("#username").setValue("******");
        $("#password").setValue("*****");
        $("#btnLogOn").click();

        $(".nav-item.dropdown").shouldBe(visible);

        $(byTagAndText("span", "Data Manager")).click();
        $(byTagAndText("a", "Submissions")).click();
        $(byTagAndText("a", "IAR - OCAN")).click();

        sleep(3000);

        switchTo().frame(0);
        $("#img0").parent().click();
        $$x("(//input[@type='radio'])").filter(clickable).first().click();

        $("select").click();
        var downloadButton = $x("//option[text()='Download Submission']");
        downloadButton.shouldBe(clickable);

        var file = downloadButton.download();
        System.out.println("File Path :: " + file.getPath());
    }


    private static Map<String, Object> getChromiumPreferences() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);  // Disable download prompt
        prefs.put("download_restrictions", 0);  // 0: No restrictions, 1: Block dangerous, 2: Block all except safe, 3: Block all downloads
        return prefs;
    }
}
