package testbase.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import testbase.UiTestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private static final ElementsCollection CALENDAR_MONDAYS_LIST = $$x("//td[contains(@class,'CalendarDay__firstDayOfWeek') and not(contains(@class,'CalendarDay__blocked_out_of_range'))]");
    private static final SelenideElement DEPARTURE_CITY_INPUT = $x("//input[@aria-describedby='react-select-2-placeholder']");
    private static final SelenideElement ARRIVAL_CITY_INPUT = $x("//input[@aria-describedby='react-select-3-placeholder']");
    private static final SelenideElement DEPARTURE_DATE_BUTTON = $x(".//div[@data-id='departure-date']");
    private static final SelenideElement SEARCH_BUTTON = $x("//button[@data-id='search-btn']");

    public MainPage openMainPage() {
        open(UiTestBase.URL);

        return new MainPage();
    }

    public MainPage setDepartureAndArrivalCities(String departureCity, String arrivalCity) {
        setDepartureCity(departureCity);
        setArrivalCity(arrivalCity);

        return this;
    }

    public MainPage setDepartureCity(String value) {
        DEPARTURE_CITY_INPUT.scrollIntoView(true);
        DEPARTURE_CITY_INPUT.click();
        DEPARTURE_CITY_INPUT.sendKeys(value);
        DEPARTURE_CITY_INPUT.pressEnter();

        return this;
    }

    public MainPage setArrivalCity(String value) {
        ARRIVAL_CITY_INPUT.click();
        ARRIVAL_CITY_INPUT.sendKeys(value);
        ARRIVAL_CITY_INPUT.pressEnter();

        return this;
    }

    public MainPage selectNextMondayFromCalendar() {
        DEPARTURE_DATE_BUTTON.shouldBe(Condition.visible).click();
        CALENDAR_MONDAYS_LIST.get(0).shouldBe(Condition.visible).click();

        return this;
    }

    public SearchResultsPage clickSearchButton() {
        SEARCH_BUTTON.shouldBe(Condition.visible).click();

        return new SearchResultsPage();
    }
}
