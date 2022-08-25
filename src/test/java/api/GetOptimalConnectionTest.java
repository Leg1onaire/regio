package api;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testbase.models.RouteModel;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


import static api.RestUtils.sendGetRequestAssertResponseCode;
import static enums.Location.BRNO;
import static helpers.Helper.nextDayOfTheWeek;

import static testbase.Services.Routes.Search.Simple.SIMPLE_URL;
import static enums.Location.OSTRAVA;

@Log
public final class GetOptimalConnectionTest {

    private static List<RouteModel> routes;

    @BeforeAll
    public static void getRoutes() {

        //Build URL parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("tariffs", "REGULAR");
        queryParams.put("toLocationType", "CITY");
        queryParams.put("toLocationId", BRNO.getId().toString());
        queryParams.put("fromLocationType", "CITY");
        queryParams.put("fromLocationId", OSTRAVA.getId().toString());
        queryParams.put("departureDate", nextDayOfTheWeek(DayOfWeek.MONDAY));

        routes = sendGetRequestAssertResponseCode(
                SIMPLE_URL,
                queryParams,
                200,
                "routes",
                RouteModel.class);

        if (routes.isEmpty()) {
            Assertions.fail("PRECONDITION FAILED: No results were returned");
        }
    }

    @Test
    public void fastestArrivalTimeTest() {
        RouteModel fastestArrival = routes.stream()
                .min(Comparator.comparing(RouteModel::getArrivalTime))
                .orElseThrow(NoSuchElementException::new);

        log.info("Fastest arrival time is: " + fastestArrival);
    }

    @Test
    public void shortestTravelTimeSpentTest() {
        RouteModel shortestTravelTime = routes.stream()
                .min(Comparator.comparing(RouteModel::getTravelTime))
                .orElseThrow(NoSuchElementException::new);

        log.info("Shortest travel time is: " + shortestTravelTime);
    }

    @Test
    public void lowestPriceTest() {
        RouteModel lowestPrice = routes.stream()
                .min(Comparator.comparing(RouteModel::getPriceFrom))
                .orElseThrow(NoSuchElementException::new);

        log.info("Lowest route price is: " + lowestPrice);
    }
}
