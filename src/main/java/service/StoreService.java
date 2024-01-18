package service;

import static io.restassured.RestAssured.given;

import dto.OrderDTO;
import io.restassured.response.ValidatableResponse;

public class StoreService extends AbsPetStoreApi {

  private final String orderUri = "/store/order";
  private final String findOrderByIdUri = "/store/order/%d";

  public ValidatableResponse placeOrder(OrderDTO orderDTO) {
    return given(requestSpec)
        .body(orderDTO)
        .when()
        .post(orderUri)
        .then()
        .log().all();
  }

  public ValidatableResponse getOrder(Long orderId) {
    return given(requestSpec)
        .when()
        .get(String.format(findOrderByIdUri, orderId))
        .then()
        .log().all();
  }

  public ValidatableResponse deleteOrder(Long orderId) {
    return given(requestSpec)
        .when()
        .delete(String.format(findOrderByIdUri, orderId))
        .then()
        .log().all();
  }

}
