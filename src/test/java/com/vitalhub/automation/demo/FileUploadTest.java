package com.vitalhub.automation.demo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class FileUploadTest {

    private static final SelenideElement UPLOAD_TARGET = $x("//input[@id='fileInput' and @type='file']");
    private static final SelenideElement UPLOAD_BUTTON = $("#uploadButton");

    @BeforeMethod
    public void setUp() {

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/file_upload_without_form.html";
        Configuration.timeout = 15 * 1000L;
        Configuration.browser = "chrome";

        open(filePath);
        $x("//h1").shouldHave(text("Multi-file upload"));
    }


    @Test
    public void fileUploadTest() {

        File file1 = new File(System.getProperty("user.dir") + "/data-files/image1.png");
        File file2 = new File(System.getProperty("user.dir") + "/data-files/image2.png");

        UPLOAD_TARGET.uploadFile(file1, file2);
        UPLOAD_BUTTON.click();

        $(withText("2 files")).shouldBe(visible);
    }


    @Test
    public void fileDragAndDropUploadTest() {
        String file1 = System.getProperty("user.dir") + "/data-files/image1.png";
        String file2 = System.getProperty("user.dir") + "/data-files/image2.png";

        UPLOAD_TARGET.sendKeys(file1);
        UPLOAD_TARGET.sendKeys(file2);
        UPLOAD_BUTTON.click();

        $(withText("2 files")).shouldBe(visible);
    }

}
