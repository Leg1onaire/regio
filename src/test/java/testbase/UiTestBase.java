package testbase;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;

public abstract class UiTestBase {
    public static final String URL = "https://novy.regiojet.cz/sk";

    @AfterAll
    public static void closeSession(){
        Selenide.closeWebDriver();
    }
}
