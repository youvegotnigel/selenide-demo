package com.vitalhub.automation.moreorless;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class PopulationByCountryTest {

    Map<String, Integer> populationByCountry = new HashMap<>();

    private static final SelenideElement COUNTRY_ON_THE_LEFT = $$x("//h2").get(0);
    private static final SelenideElement COUNTRY_ON_THE_RIGHT = $$x("//h2").get(1);
    private static final SelenideElement HIGHER_BUTTON = $(byTagAndText("button","Higher"));
    private static final SelenideElement LOWER_BUTTON = $(byTagAndText("button","Lower"));

    private static final SelenideElement SUCESS_ICON = $(".transition-colors.bg-green-600");
    private static final SelenideElement VS_ICON = $(byTagAndText("p", "vs"));
    private static final SelenideElement TRY_AGAIN_BUTTON = $(byTagAndText("button", "Try again"));

    private static final SelenideElement HIGH_SCORE = $(byTagAndText("p", "Highscore")).preceding(0);
    private static final SelenideElement MY_SCORE = $(byTagAndText("p", "Score")).preceding(0);


    @BeforeTest
    public void setUp() {
        open("https://www.worldometers.info/world-population/population-by-country/");
        storeDataInHashMap();
    }

    @Test
    public void printHashMap() {

        System.out.println("MAP VALUES:\n");
        System.out.println("************************************");
        for (Map.Entry<String, Integer> entry : populationByCountry.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private void storeDataInHashMap() {
        String countryXpath = "//table/tbody/tr/td[2]";
        String populationXpath = "//table/tbody/tr/td[3]";
        $x("//table/tbody").shouldBe(visible);

        List<String> countryList = $$x(countryXpath).texts();
        List<Integer> populationList = convertToIntList($$x(populationXpath).texts());

        populationByCountry = IntStream.range(0, countryList.size())
                .boxed()
                .collect(Collectors.toMap(i -> countryList.get(i).toLowerCase()
                                .replace("-", " ")
                                .replace("&", "and")
                                .replace("é", "e"),
                        populationList::get));
    }

    private static List<Integer> convertToIntList(List<String> numbersWithCommas) {
        List<Integer> result = new ArrayList<>();
        for (String number : numbersWithCommas) {
            try {
                // Remove commas and parse as an integer
                result.add(Integer.parseInt(number.replace(",", "")));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format: " + number, e);
            }
        }
        return result;
    }

    @Test
    public void gameTest() {

        open("https://moreorless.io/game/population-by-country");
        HIGHER_BUTTON.shouldBe(visible);
        LOWER_BUTTON.shouldBe(visible);

        System.out.println("Game has started");
        int counter = 0;
        while (VS_ICON.is(visible)) {

            playGame();

            if(TRY_AGAIN_BUTTON.is(visible)) {
                Assert.fail("Game Failed !! ");
            }

            if(counter > 200) {
                System.out.println("You won 60 rounds!");
                break;
            }
            counter++;
        }

        screenshot("my_score");
        System.out.println("High score: "+ HIGH_SCORE.text());
        System.out.println("My score: "+ MY_SCORE.text());
        sleep(5000);
    }


    private void playGame() {

        String countryOnLeft = COUNTRY_ON_THE_LEFT.text().toLowerCase();
        System.out.println("Country on the left: " + countryOnLeft);
        int populationOfCountryOnLeft = findPopulationByCountry(populationByCountry, countryOnLeft);
        System.out.printf("Population of %s: %d\n", countryOnLeft, populationOfCountryOnLeft);

        String countryOnRight = COUNTRY_ON_THE_RIGHT.text().toLowerCase();
        System.out.println("Country on the right: " + countryOnRight);
        int populationOfCountryOnRight = findPopulationByCountry(populationByCountry, countryOnRight);
        System.out.printf("Population %s: %d\n", countryOnRight, populationOfCountryOnRight);


        SelenideElement buttonToClick = (populationOfCountryOnRight > populationOfCountryOnLeft) ? HIGHER_BUTTON : LOWER_BUTTON;
        buttonToClick.click();
        System.out.println(buttonToClick.text() + " was clicked.");
        System.out.println("------------------------------------------\n");

        VS_ICON.shouldBe(visible);
    }


    //TODO: Need to handle this a better way
    private static int findPopulationByCountry(Map<String, Integer> map, String key) {

        if (key.equals("ivory coast")) {
            return map.get("côte d'ivoire");
        }

        if (key.equals("kosovo")) {
            return 1602515;
        }

        if (key.equals("republic of the congo")) {
            return map.get("congo");
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
           if (entry.getKey().equals(key)) {
               return map.get(key);
           }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
             if (entry.getKey().matches(".*"+ key +".*")) {
                return entry.getValue();
            }
        }
        return -1; // Return -1 if no matching key is found
    }

}


/**
 * BUGS
 *
 * will not work for ivory coast, since it stored in the map as côte d'ivoire
 * kosovo is not in the list
 * republic of the congo should be from congo? or dr congo? :/
 *
 */