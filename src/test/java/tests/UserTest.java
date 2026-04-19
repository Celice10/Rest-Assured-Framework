package tests;

import api.Endpoints;
import com.github.javafaker.Faker;
import common.BaseURI;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TokenManager;
import utils.apiRequestBuilder;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

@Listeners({AllureTestNg.class})
public class UserTest extends BaseURI {

    static String registeredEmail;
    static String userId;


    @Test
    public void testAdminLogin() {

        String payload = utils.PayloadBuilder.loginUserPayload("username1@gmail.com", "test@123").toString();

        Response response = apiRequestBuilder.buildRequest()
                .body(payload)
                .post(Endpoints.LOGIN)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response();

        String tokenValue = response.jsonPath().getString("data.token");
        TokenManager.setToken(tokenValue);
    }

    @Test(dependsOnMethods = "testAdminLogin")
    public void testUserRegistration() {

        registeredEmail = Faker.instance().internet().emailAddress(); //creates dynamic email address for registration

        String payload = utils.PayloadBuilder.registerUserPayload(
                "Register",
                "Jsonapi",
                registeredEmail,
                "@87654321",
                "@87654321", "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526").toString();

        Response response = apiRequestBuilder.buildRequest()
                .body(payload)                      //sets the request body to the JSON payload created by the PayloadBuilder
                .post(Endpoints.REGISTER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract().response();

        userId = response.jsonPath().getString("data.id"); // Extracting user ID from the response

    }
    @Test(dependsOnMethods = "testUserRegistration")
    public void testApproveUser() {

        String payload = utils.PayloadBuilder.approveUserPayload(userId, "approved").toString();

        apiRequestBuilder.buildRequest()
                .header("Authorization", "Bearer " + TokenManager.getToken()) //adds the admin token to the request header for authentication
                .pathParam("newUserId", userId)
                .body(payload)
                .put(Endpoints.APPROVE_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "testApproveUser")
    public void testUpdateUserRole() {
        String payload = utils.PayloadBuilder.updateUserRolePayload(userId, "admin").toString();

        apiRequestBuilder.buildRequest()
                .header("Authorization", "Bearer " + TokenManager.getToken()) //adds the admin token to the request header for authentication
                .pathParam("userId", userId)
                .body(payload)
                .put(Endpoints.UPDATE_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("data.role", equalTo("admin"));
    }

    @Test(dependsOnMethods = "testApproveUser")
    public void testUserLogin() {

        String payload = utils.PayloadBuilder.loginUserPayload(registeredEmail, "@87654321").toString();

        apiRequestBuilder.buildRequest()
                .body(payload)
                .post(Endpoints.LOGIN)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);


    }

    @Test(dependsOnMethods = "testUpdateUserRole")
    public void testVerifyUserIsAdmin() {

        String payload = utils.PayloadBuilder.loginUserPayload(registeredEmail, "@87654321").toString();

        apiRequestBuilder.buildRequest()
                .body(payload)
                .post(Endpoints.LOGIN)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("data.user.role", equalTo("admin"));
    }
    @Test(dependsOnMethods = "testVerifyUserIsAdmin")
    public void testDeleteUser(){

        apiRequestBuilder.buildRequest()
                .header("Authorization", "Bearer " + TokenManager.getToken()) //adds the admin token to the request header for authentication
                .pathParam("userId", userId)
                .delete(Endpoints.DELETE_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
//    @AfterMethod
//    public void takeScreenshotOnFailure(ITestResult result) {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File dest = new File("target/screenshots/" + result.getName() + ".png");
//            FileUtils.copyFile(src, dest);
//        }

}
