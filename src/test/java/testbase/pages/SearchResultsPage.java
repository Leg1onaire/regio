package testbase.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.Location;
import lombok.extern.java.Log;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static helpers.Helper.validateTimeFormat;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log
public class SearchResultsPage {

    private static final SelenideElement ROUTES_LIST = $x("(//ul)[1]");
    private static final ElementsCollection DEPARTURE_CITY_LIST = $$x("//span[contains(@class,'pr-0.5')]");
    private static final ElementsCollection ARRIVAL_CITY_LIST = $$x("(//ul)/li[1]/div/div[10]");
    private static final ElementsCollection DEPARTURE_TIME_LIST = $$x("(//ul)/li[1]/div/div[not(contains(@class, 'MuiBackdrop-root'))][2]");
    private static final ElementsCollection ROUTE_CARDS_LIST = $$x("//div[starts-with(@data-id, 'connection-card')]");

    public SearchResultsPage expandAllSearchResultCards() {
        ROUTES_LIST.shouldHave(Condition.visible).scrollIntoView(true);
        log.info("-----------Search results are visible");

        ROUTE_CARDS_LIST.shouldHave(sizeGreaterThan(0));
        log.info("-----------Search results list is not empty");

        for (SelenideElement card : ROUTE_CARDS_LIST) {
            card.shouldBe(Condition.visible).scrollIntoView(true).click();
        }

        return this;
    }

    public SearchResultsPage verifyAllResultsHaveDepartureTime() {
        if (DEPARTURE_TIME_LIST.size() > 0) {
            for (SelenideElement time : DEPARTURE_TIME_LIST) {
                log.info("Departure time is : " + time.getText());
                assertTrue(validateTimeFormat(time.getText()));
            }
        }
        return this;
    }

    public SearchResultsPage verifyAllResultsHaveDepartureCity(Location city) {
        if (DEPARTURE_CITY_LIST.size() > 0) {
            for (SelenideElement departureCity : DEPARTURE_CITY_LIST) {
                log.info("Departure city is : " + departureCity.getText());
                assertTrue(departureCity.getText().contains(city.getName()));
            }
        }

        return this;
    }

    public SearchResultsPage verifyAllResultsHaveArrivalCity(Location city) {
        if (ARRIVAL_CITY_LIST.size() > 0) {
            for (SelenideElement arrivalCity : ARRIVAL_CITY_LIST) {
                log.info("Arrival city is : " + arrivalCity.getText());
                assertTrue(arrivalCity.getText().contains(city.getName()));
            }
        }

        return this;
    }
}
