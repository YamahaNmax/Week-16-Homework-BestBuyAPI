package playground.bestbuy.products;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Utillites {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getVersion() {

        response = RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/version");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void getHealthCheck() {

        response = RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/healthcheck");
        response.then().log().all().statusCode(200);
    }

}