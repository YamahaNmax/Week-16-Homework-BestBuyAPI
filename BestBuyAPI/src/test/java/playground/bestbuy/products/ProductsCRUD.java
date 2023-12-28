package playground.bestbuy.products;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductsCRUD {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getProducts() {

        RestAssured.baseURI = "http://localhost:3030/products";
        requestSpecification = given().log().all();
        response = requestSpecification.get();
        response.then().log().all(); //for log purpose you need to use then
        //System.out.println(response.prettyPrint());
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void postProduct() {

        String jsonData = "{  \n" +
                "   \"name\": \"Parle\", \n" +
                "   \"type\": \"Biscuit\", \n" +
                "   \"price\": 10, \n" +
                "   \"shipping\": 5, \n" +
                "   \"upc\": \"dpd\",\n" +
                "   \"description\": \"Best Buscuit\",\n" +
                "   \"manufacturer\": \"Parle\",\n" +
                "   \"model\": \"1969\", \n" +
                "   \"url\": \"string\",  \n" +
                "   \"image\": \"string\" \n" +
                " }";
        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .post("http://localhost:3030/products");
        response.then().log().all().statusCode(201)
                .body("name", equalTo("Parle"))
                .body("type", equalTo("Biscuit"));

    }

    @Test
    public void deleteProductById() {
        response = given().log().all()
                .when()
                .delete("http://localhost:3030/products/9999684");
        response.then().log().all()
                .statusCode(404);
    }

    @Test
    public void getProductById() {

        response = RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/products/48530");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void patchProductById() {

        String jsonData = "{ \n" +
                " \"type\": \"Crisp\"\n" +
                "}";
        response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .patch("http://localhost:3030/products/9999686");
        response.then().log().all().statusCode(200)
                .body("type", equalTo("Crisp"));

    }

}
