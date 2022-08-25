package ui;

import enums.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testbase.UiTestBase;
import testbase.pages.MainPage;
import testbase.pages.SearchResultsPage;

import static enums.Location.BRNO;
import static enums.Location.OSTRAVA;

public final class ConnectionSearchTest extends UiTestBase {

    private static final Location DEPARTURE_CITY = OSTRAVA;
    private static final Location ARRIVAL_CITY = BRNO;
    private static final MainPage mainpage = new MainPage();
    private static SearchResultsPage searchResultsPage;

    @BeforeAll
    public static void preconditionConnectionSearchTest(){
        searchResultsPage = mainpage.openMainPage()
                .setDepartureAndArrivalCities(DEPARTURE_CITY.getName(), ARRIVAL_CITY.getName())
                .selectNextMondayFromCalendar()
                .clickSearchButton()
                .expandAllSearchResultCards();
    }

    @Test
    public void searchResultsHaveDepartureTimeTest() {
        searchResultsPage.verifyAllResultsHaveDepartureTime();
    }

    @Test
    public void searchResultsHaveDepartureCityTest() {
        searchResultsPage.verifyAllResultsHaveDepartureCity(DEPARTURE_CITY);
    }

    @Test
    public void searchResultsHaveArrivalCityTest() {
        searchResultsPage.verifyAllResultsHaveArrivalCity(ARRIVAL_CITY);
    }
}
