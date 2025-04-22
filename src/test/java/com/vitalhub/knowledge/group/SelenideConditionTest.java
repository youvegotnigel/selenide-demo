package com.vitalhub.knowledge.group;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

/**
 Selenide Conditions
 URL: <a href="https://selenide.org/javadoc/current/com/codeborne/selenide/Condition.html">...</a>
 */
public class SelenideConditionTest {

    private static final int MAX_TIMEOUT = 10;

    @BeforeMethod
    public void setUp() {

        Configuration.timeout = 3 * 1000L;
        Configuration.browserSize = "1440x900";

        String filePath = "file:" + System.getProperty("user.dir") + "/src/test/resources/selenide-conditions-demo.html";
        open(filePath);

        $x("//h1").shouldHave(text("\uD83C\uDF1F Selenide Conditions Demo Page"));
    }

    @Test
    public void testAnimated() {
        $("#loadingSpinner").shouldBe(animated);
    }

    @Test
    public void testAppear() {

        //$(byTagAndText("button", "Show Modal")).shouldBe(clickable).click();
        $("#modalDialog").shouldBe(appear, Duration.ofSeconds(MAX_TIMEOUT));
    }

    @Test
    public void testChecked() {
        $("#subscribeCheckbox").shouldBe(checked);
    }

    @Test
    public void testClickable() {
        $("#submitButton").shouldBe(clickable).click();
        String alertText = switchTo().alert().getText();
        System.out.println("Alert Text ::: " + alertText);
        switchTo().alert().accept();
    }

    @Test
    public void testDisabled() {
        $("#disabledInput").shouldBe(disabled);
    }

    @Test
    public void testDisappear() {
        $(byTagAndText("button", "Hide Toast")).highlight().click();
        $("#toastNotification").should(disappear, Duration.ofSeconds(MAX_TIMEOUT));
    }

    @Test
    public void testEditable() {
        $("#usernameInput").shouldBe(editable);
    }

    @Test
    public void testEmpty() {
        $("#searchField").shouldBe(empty);
    }

    @Test
    public void testEnabled() {
        $("#loginButton").shouldBe(enabled).highlight();
    }

    @Test
    public void testExist() {
        $("#footer").should(exist);
    }

    @Test
    public void testFocused() {
        $("#emailInput").click();
        $("#emailInput").shouldBe(focused);
    }

    @Test
    public void testHidden() {
        $("#hiddenSection").shouldBe(hidden);
    }

    @Test
    public void testImageLoaded() {
        $("#productImage").shouldBe(image);
    }

    @Test
    public void testInteractable() {
        $("#hoverTooltip").shouldBe(interactable);
    }

    @Test
    public void testReadonly() {
        $("#readonlyField").shouldBe(readonly);
    }

    @Test
    public void testSelected() {
        $("#countryDropdown").getSelectedOption().shouldHave(exactOwnTextCaseSensitive("United Kingdom"));
    }

    @Test
    public void testVisible() {
        $("#mainBanner").shouldBe(visible);
    }

}
