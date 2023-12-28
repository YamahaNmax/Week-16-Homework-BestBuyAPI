package playground.bestbuy.products;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StoresCRUD {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllStores() {
        response = given().log().all()
                .when()
                .get("http://localhost:3030/stores");
        response.then().log().all().statusCode(200);

    }

    @Test
    public void createNew() {
        String jsonData = "{\n" +
                "  \"name\": \"Lees\",\n" +
                "  \"type\": \"Grocery\",\n" +
                "  \"address\": \"Islington\",\n" +
                "  \"address2\": \"Islington\",\n" +
                "  \"city\": \"London\",\n" +
                "  \"state\": \"string\",\n" +
                "  \"zip\": \"string\",\n" +
                "  \"lat\": 0,\n" +
                "  \"lng\": 0,\n" +
                "  \"hours\": \"string\",\n" +
                "    \"services\": {}\n" +
                "}";
        validatableResponse = given().log().all()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .post("http://localhost:3030/stores")
                .then().log().all()
                .statusCode(201)
                .body("name", equalTo("Lees"))
                .body("type", equalTo("Grocery"));

    }

    @Test
    public void deleteStoreById() {
        response = given().log().all()
                .when()
                .delete("http://localhost:3030/products/8921");
        response.then().log().all()
                .statusCode(404);
    }

    @Test
    public void getStoreByID() {
        response = given().log().all()
                .when()
                .get("http://localhost:3030/stores/8921");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void patchStoreById() {
        String jsonData = "{ \n" +
                " \"type\": \"News Agent\"\n" +
                "}";
        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .patch("http://localhost:3030/stores/8921");
        response.then().log().all().statusCode(200)
                .body("type", equalTo("News Agent"));

    }

}
