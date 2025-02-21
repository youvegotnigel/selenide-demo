package com.vitalhub.automation.demo;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;

public class DownloadAsPDFTest {

    private static final By PRINT_CV_BUTTON = By.id("button-print-cv");
    private static final String FILE_DOWNLOAD_PATH = System.getProperty("user.dir") + "/downloads/";


    @BeforeMethod
    public void setUp() {

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/page_with_pdf.html";
        Configuration.browserSize = "1440x900";
        Configuration.browser = "chrome";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = FILE_DOWNLOAD_PATH;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-print-preview"); // Disables print preview
        options.addArguments("plugins.always_open_pdf_externally=true"); // Forces PDF download

        HashMap<String, Object> prefs = new HashMap<>();

        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", FILE_DOWNLOAD_PATH);
        prefs.put("savefile.default_directory", FILE_DOWNLOAD_PATH);
        prefs.put("download.directory_upgrade", true);
        prefs.put("download.extensions_to_open", "pdf");
        prefs.put("safebrowsing.enabled", true);

        // unknown error: DevToolsActivePort file doesn't exist
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        // setting prefs to chrome options
        options.setExperimentalOption("prefs", prefs);

        Configuration.browserCapabilities = options;

        open(filePath);
        $x("//h2").shouldHave(text("Click the button to generate and open a PDF"));
    }


    @Test
    public void printPdfDocument() throws IOException {

        $(PRINT_CV_BUTTON).shouldBe(clickable).shouldHave(text("Open PDF"));
        File cv = $(PRINT_CV_BUTTON).download(
                DownloadOptions.file().withExtension("pdf").withTimeout(Duration.ofSeconds(10))
        );

        PDF pdf = new PDF(cv);
        assertThat(pdf, containsText("Hello World"));
    }


}
