package common;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseURI {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://ndosiautomation.co.za/APIDEV"; //For ALL requests, use this as the default base URL
    }
}
