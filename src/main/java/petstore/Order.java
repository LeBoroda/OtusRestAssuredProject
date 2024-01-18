package petstore;

import static org.hamcrest.Matchers.equalTo;

import data.OrderStatusData;
import dto.OrderDTO;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import service.StoreService;
import java.time.LocalDateTime;

public class Order extends AbsPetstoreObject {

  private StoreService storeService = new StoreService();

  public OrderDTO createOrderAllFields() {
    return OrderDTO
        .builder()
        .id(faker.number().randomNumber(4, true))
        .petId(faker.number().randomNumber(3, true))
        .quantity(Long.valueOf(faker.number().digits(1)))
        .shipDate(LocalDateTime.now().toString())
        .status(OrderStatusData.PLACED.getName())
        .complete(false)
        .build();
  }

  public void placeOrder(OrderDTO orderDTO) {
    storeService.placeOrder(orderDTO)
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateOrder.json"))
        .body("id", equalTo(orderDTO.getId().intValue()))
        .body("petId", equalTo(orderDTO.getPetId().intValue()));

    storeService.deleteOrder(orderDTO.getId());
  }

  public void findPlacedOrder(OrderDTO orderDTO) {
    storeService.getOrder(orderDTO.getId())
        .statusCode(HttpStatus.SC_NOT_FOUND);
    storeService.placeOrder(orderDTO)
        .statusCode(HttpStatus.SC_OK);
    storeService.getOrder(orderDTO.getId())
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateOrder.json"));

    storeService.deleteOrder(orderDTO.getId());
  }

  public void deleteOrder(OrderDTO orderDTO) {
    storeService.placeOrder(orderDTO);
    storeService.deleteOrder(orderDTO.getId())
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/DeleteOrder.json"))
        .body("code", equalTo(200))
        .body("message", equalTo(String.valueOf(orderDTO.getId())));
  }

}
