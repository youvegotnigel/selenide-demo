package com.vitalhub.automation.demo;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileDownloadRemoteTest {


    @BeforeMethod
    public void setUp() {

        Configuration.remote = "http://localhost:4444";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = System.getProperty("user.dir") + "/downloads";
        Configuration.browserCapabilities.setCapability("se:downloadsEnabled", true);

        Configuration.browser = "chrome";
        open("https://the-internet.herokuapp.com/download");
    }


    @Test
    public void testFileDownloadWithHrefRemote() throws IOException {

        File downloadedFile = $(byText("samplePDF.pdf")).download();

        Assert.assertTrue(downloadedFile.exists());
        System.out.println("path:: " + downloadedFile.getAbsoluteFile());

        PDF pdf = new PDF(downloadedFile);
        assertThat(pdf, containsText("Why do bonds work this way?"));
        downloadedFile.getParentFile().deleteOnExit();
    }
}
