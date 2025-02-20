package com.vitalhub.automation.demo;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrintAsPDFTest {

    private static final By PRINT_CV_BUTTON = By.id("button-print-cv");

    @BeforeMethod
    public void setUp() {

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/page_with_print.html";
        Configuration.browserSize = "1440x900";
        Configuration.browser = "chrome";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = System.getProperty("user.dir") + "/downloads/";
        Configuration.reopenBrowserOnFail = false;

        open(filePath);
        $x("//h1").shouldHave(text("Page with print"));
    }


    @Test
    public void printPdfDocument() throws IOException {

        $(PRINT_CV_BUTTON).shouldBe(clickable).shouldHave(text("Print CV"));
        File cv = $(PRINT_CV_BUTTON).download(
                DownloadOptions.file().withExtension("pdf").withTimeout(Duration.ofSeconds(10))
        );

        PDF pdf = new PDF(cv);
        assertThat(pdf, containsText("School"));
    }
}
