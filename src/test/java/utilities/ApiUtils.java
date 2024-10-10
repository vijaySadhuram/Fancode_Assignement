package utilities;



import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {
    static {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    public static Response getUsers() {
        return given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response getTodos() {
        return given()
                .when()
                .get("/todos")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
