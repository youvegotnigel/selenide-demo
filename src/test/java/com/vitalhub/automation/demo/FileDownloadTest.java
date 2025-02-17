package com.vitalhub.automation.demo;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileDownloadTest {


    @BeforeMethod
    public void setUp() {
        Configuration.browserSize = "1440x900";
        Configuration.timeout = 30 * 1000L;

        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;

        Configuration.browser = "chrome";
    }


    @Test
    public void testFileDownload() {

        // Login
        open("http://vhvmdvtrtqaapp7.vh.local:8080/treat/logon");
        $("#orgUserID").setValue("*****");
        $("#btnContinue").click();
        $("#username").setValue("****");
        $("#password").setValue("****").pressEnter();
        $("#treatNavigation").shouldBe(visible);


        $("#patientQuickSearch").setValue("16698").pressEnter();
        $(byTagAndText("a", "Assessments")).shouldBe(visible).click();

        $x("//h1").shouldHave(text("Assessments"));

        $("#assessmentList").selectOption("OCAN (OCAN)");
        //$("#printAssessment").click();


        File downloadedFile = $("#printAssessment").download(DownloadOptions.file().withTimeout(Duration.ofSeconds(10)));

        Assert.assertTrue(downloadedFile.exists());
        System.out.println("path:: " + downloadedFile.getAbsoluteFile());

    }


    @Test
    public void testFileDownloadWithHref() throws IOException {

        open("https://the-internet.herokuapp.com/download");

        File downloadedFile = $(byText("samplePDF.pdf")).download();

        Assert.assertTrue(downloadedFile.exists());
        System.out.println("path:: " + downloadedFile.getAbsoluteFile());
        downloadedFile.getParentFile().deleteOnExit();

        PDF pdf = new PDF(downloadedFile);
        assertThat(pdf, containsText("John Belushi"));
        assertThat(pdf, containsText("Birthday"));
    }
}
