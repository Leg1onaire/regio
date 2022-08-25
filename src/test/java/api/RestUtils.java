package api;

import io.restassured.RestAssured;
import testbase.models.AbstractModel;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Map;

import static helpers.Helper.uriParamsBuilder;

public final class RestUtils {

    /**
     * Validate if specified time matches the format hh:mm
     *
     * @param url String request URL
     * @param queryParams @Nullable map of query parameters
     * @param expectedStatusCode 200/201/400 etc
     * @param jsonPath JsonPath view of the response body. This will let you use the JsonPath syntax to get values from the response.
     * @param extractModel Class to be extracted
     * @return - List of extractModel classes
     */
    public static <M extends AbstractModel> List<M> sendGetRequestAssertResponseCode(String url,
                                                                                     @Nullable Map<String, ?> queryParams,
                                                                                     int expectedStatusCode,
                                                                                     String jsonPath,
                                                                                     Class<M> extractModel) {

        return RestAssured.given()
                .when()
                .get(queryParams != null ? uriParamsBuilder(url, queryParams) : url)
                .then()
                .statusCode(expectedStatusCode)
                .log().all()
                .extract().body().jsonPath().getList(jsonPath, extractModel);
    }
}
