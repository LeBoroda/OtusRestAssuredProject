package service;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class AbsPetStoreApi {
  protected final RequestSpecification requestSpec;
  private final static String BASE_URI = System.getProperty("base.uri", "https://petstore.swagger.io/v2/");

  AbsPetStoreApi() {
    requestSpec = given()
        .baseUri(BASE_URI)
        .contentType(ContentType.JSON)
        .log().all();
  }

}
