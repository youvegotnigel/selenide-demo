package com.vitalhub.knowledge.group;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class CoffeeCartTest {

    @BeforeMethod
    public void setup() {

        Configuration.browser = "chrome";
        Configuration.timeout = 10 * 1000L;
        Configuration.baseUrl = "https://coffee-cart.netlify.app";
        open("/");
    }

    @Test
    public void testCoffeeCart() {

        $("[data-test=\"checkout\"]").shouldHave(text("Total: $0.00"));

        $("[data-test=\"Espresso\"]").shouldBe(visible).click();
        $("[data-test=\"Cappuccino\"]").shouldBe(visible).click();
        $("[data-test=\"Americano\"]").shouldBe(visible).click();

        $("[data-test=\"checkout\"]").shouldHave(text("Total: $36.00"));

        $(byTagAndText("button", "Nah, I'll skip.")).shouldBe(visible).click();

        $("[href=\"/cart\"]").shouldHave(text("cart (3)")).click();

        $("[data-test=\"checkout\"]").shouldHave(text("Total: $36.00"));

        var rows = $$x("//*[@class=\"list-item\"]").filter(visible);

        for (var row : rows) {
            System.out.println(row.getText());
        }
    }

}
