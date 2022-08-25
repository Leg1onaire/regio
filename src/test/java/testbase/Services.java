package testbase;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Services {

    public static final String BASE_URL = "https://brn-ybus-pubapi.sa.cz/restapi";

    public static class Routes {

        public static final String ROUTES_URL = BASE_URL + "/routes";

        public static class Search {
            public static final String SEARCH_URL = ROUTES_URL + "/search";

            public static class Simple {
                public static final String SIMPLE_URL = SEARCH_URL + "/simple";
            }
        }
    }
}
