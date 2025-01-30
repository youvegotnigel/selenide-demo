package com.vitalhub.automation.demo;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class KedndoDropdownTest {

    @Test
    public void verifyUserCanLogin() {

        open("https://electronicmedicalrecord.hicom.local");
        WebDriverRunner.getWebDriver().manage().window().maximize();

        $("#Input_Email").setValue("**********");
        $("#Input_Password").setValue("*******").pressEnter();
        $("#toggle-user-menu").shouldBe(visible);

        open("https://electronicmedicalrecord.hicom.local/Patient/RecentlyViewed");
        $x("(//*[@class='icon-settings'])[1]").shouldBe(visible).click();
        $x("(//*[@title='Edit'])[1]").shouldBe(visible).click();
        $("#headingEpisodeList").shouldBe(visible, Duration.ofSeconds(30));

        $x("(//*[@class='icon-settings'])[1]").shouldBe(visible, Duration.ofSeconds(30)).scrollIntoCenter().click();
        $(byTagAndText("a", "Open")).shouldBe(visible).click();

        $(byTagAndText("li", "Edit Episode Detail")).shouldBe(visible).click();


        String inputID = "Episode_ClinicID";
        String setValue = "MALAY";

        String xpath = String.format("(//input[@id=\"%s\"])[1]", inputID);

        SelenideElement input = $x(xpath);

        input.parent().shouldBe(exist).click();
        sleep(2000);
        setKendoDropDownValue($(By.id(inputID)), setValue);
        input.parent().pressEscape();


        $("#btnSave").shouldBe(enabled).click();
    }


    void setKendoDropDownValue1(WebElement id, String value) {

        String script = "var dropdown = $(arguments[0]).data('kendoDropDownList');\n" +
                "var dataItems = dropdown.dataSource.view(); // Get all items in the dropdown\n" +
                "// Find the item with the desired text and get its corresponding value\n" +
                "var item = dataItems.find(function(i) {\n" +
                "    return i.Text === arguments[0]; // Match based on Text\n" +
                "});\n" +
                "if (item) {\n" +
                "    dropdown.value(item.Value); // Set the dropdown value using the corresponding Value\n" +
                "    dropdown.trigger(\"change\");" +
                "}\n";

        executeJavaScript(script, id, value);
    }

    void setKendoDropDownValue(WebElement dropdownElement, String textToSelect) {
        String script = String.format(
                        "var dropdown = $(arguments[0]).data('kendoDropDownList');\n" +
                        "var dataItems = dropdown.dataSource.view(); // Get all items in the dropdown\n" +
                        "\n" +
                        "// Find the item with the desired text and get its corresponding value\n" +
                        "var item = dataItems.find(x => x.Text === '%s');\n" +
                        "\n" +
                        "if (item) {\n" +
                        "    dropdown.value(item.Value); // Set the dropdown value using the corresponding Value\n" +
                        "}", textToSelect);

        executeJavaScript(script, dropdownElement);
    }


    void setKendoDropDownValue2(WebElement id, String value) {

        String script1 =
                "var dropdown = $(arguments[0]).data('kendoDropDownList');\n" +
                "    dropdown.value('2');\n" +
                "    dropdown.trigger(\"change\");\n";

        executeJavaScript(script1, id);

        String script2 = String.format(
                "var dropdown = $(arguments[0]).data('kendoDropDownList');\n" +
                        "    dropdown.text('%s');\n"+
                        "    dropdown.trigger(\"change\");"
                , value);

        executeJavaScript(script2, id);
    }
}
