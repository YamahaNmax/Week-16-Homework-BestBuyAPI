package playground.bestbuy.products;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CategoriesCRUD {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllCategories() {
        response = given().log().all()
                .when()
                .get("http://localhost:3030/categories");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void postCategory() {
        String jsonData = "{\n" +
                "  \"name\": \"Learning\",\n" +
                "  \"id\": \"zoom122\"\n" +
                "}";
        validatableResponse = given()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .post("http://localhost:3030/categories")
                .then().log().all()
                .statusCode(201)
                .body("name", equalTo("Learning"))
                .body("id", equalTo("zoom122"));

    }

    @Test
    public void deleteCategoryById() {
        response = given().log().all()
                .when()
                .delete("http://localhost:3030/categories/zoom122");
        response.then().log().all()
                .assertThat().statusCode(404);
    }

    @Test
    public void getCategoryById() {
        response = given().log().all()
                .when()
                .get("http://localhost:3030/categories/zoom122");
        response.then().log().all()
                .statusCode(200);
    }

    @Test
    public void patchCategory() {
        String jsonData = "{\n" +
                "  \"name\": \"Automation\",\n" +
                "  \"id\": \"zoom122\"\n" +
                "}";
        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .patch("http://localhost:3030/categories/zoom122");
        response.then().log().all().statusCode(200)
                .body("name", equalTo("Automation"));
    }

}