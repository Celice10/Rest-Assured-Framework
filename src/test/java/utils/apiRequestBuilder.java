package utils;


import common.BaseURI;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class apiRequestBuilder {

    public static RequestSpecification buildRequest(){

        return RestAssured.given()
                //.baseUri(BaseURI.baseURI) - no longer needed since we set the base URL globally in the BaseURI class using RestAssured.baseURI
                .contentType("application/json");
    }

}
