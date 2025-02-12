package com.vitalhub.automation.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class Select2Helper {

    private static final String SELECT2_SEARCH_BOX_XPATH = "//*[contains(@class,'container--open')]//input";
    private SelenideElement dropdownElement;

    // Private constructor to prevent external instantiation
    private Select2Helper() {
    }


    // Constructor to initialize WebElement and option text
    public Select2Helper(SelenideElement dropdownElement) {
        this.dropdownElement = dropdownElement;
    }


    /**
     * Selects an option from a Select2 dropdown.
     */
    public void selectOption(String optionToSelect) {

        try {
            dropdownElement.click(); // Click to open the dropdown
            SelenideElement searchBox = $x(SELECT2_SEARCH_BOX_XPATH).setValue(optionToSelect).pressEnter();
        } catch (Exception e) {

            System.out.println("Failed to select option '{}' from the Select2 dropdown." + optionToSelect);
            e.printStackTrace();
        }
    }


    public String getSelectedOption() {
        return this.dropdownElement.text().trim();
    }

}
