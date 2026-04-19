package common;

import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public class BaseURI {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://ndosiautomation.co.za/APIDEV"; //For ALL requests, use this as the default base URL
        RestAssured.filters(new AllureRestAssured());
    }

}
