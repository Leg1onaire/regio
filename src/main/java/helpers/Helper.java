package helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Helper {

    /**
     * Get next date for specified day of the week
     *
     * @param day day of the week from {@link java.time.DayOfWeek} enum
     * @return - String in format yyy-mm-dd
     */
    public static String nextDayOfTheWeek(DayOfWeek day) {
        return LocalDate.now(ZoneId.of("Europe/Prague"))
                .with(TemporalAdjusters.nextOrSame(day)).toString();

    }

    /**
     * Validate if specified time matches the format hh:mm
     *
     * @param time String in format hh:mm
     * @return - boolean
     */
    public static boolean validateTimeFormat(final String time) {
        String TIME24HOURS_PATTERN =
                "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
        Matcher matcher = pattern.matcher(time);

        return matcher.matches();
    }

    /**
     * Build URL with specified query parameters
     *
     * @param url basic URL
     * @param queryParams Map<String, ?> with query parameters
     * @return - String - e.g. https://regiojet.sk/?departureDate=2022-08-29&tariffs=REGULAR&fromLocationId=10202000
     */
    public static String uriParamsBuilder(String url, Map<String, ?> queryParams) {
        return url + "?" + queryParams
                .entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }
}
