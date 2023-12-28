package playground.bestbuy.products;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SevicesCRUD {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllServices() {
        response = RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/services");
        response.then().log().all().statusCode(200);

    }

    @Test
    public void createService() {
        String jsonData = "{\n" +
                "  \"name\": \"Cleaning\"\n" +
                "}";
        validatableResponse = given().log().all()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .post("http://localhost:3030/services")
                .then().log().all()
                .statusCode(201)
                .body("name", equalTo("Cleaning"));

    }

    @Test
    public void deleteServiceById() {
        response = given().log().all()
                .when()
                .delete("http://localhost:3030/services/22");
        response.then().log().all()
                .statusCode(404);
    }

    @Test
    public void getServiceByID() {
        response = RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/services/22");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void patchService() {
        String jsonData = "{\n" +
                "  \"name\": \"Fixing\"\n" +
                "}";
        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .patch("http://localhost:3030/services/22");
        response.then().log().all().statusCode(200)
                .body("name", equalTo("Fixing"));
    }

}
