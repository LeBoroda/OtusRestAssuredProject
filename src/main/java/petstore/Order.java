package petstore;

import static org.hamcrest.Matchers.equalTo;

import com.github.javafaker.Faker;
import data.OrderStatusData;
import dto.OrderDTO;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import service.StoreService;
import java.time.LocalDateTime;

public class Order extends AbsPetstoreObject {

  private final StoreService storeService = new StoreService();
  private final Faker faker = getFaker();

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

  private Order placeOrder(OrderDTO orderDTO) {
    storeService.placeOrder(orderDTO)
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateOrder.json"))
        .body("id", equalTo(orderDTO.getId().intValue()))
        .body("petId", equalTo(orderDTO.getPetId().intValue()));

    return this;
  }

  private Order findPlacedOrder(OrderDTO orderDTO) {
    storeService.getOrder(orderDTO.getId())
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateOrder.json"))
        .body("id", equalTo(orderDTO.getId().intValue()))
        .body("petId", equalTo(orderDTO.getPetId().intValue()));

    return this;
  }

  private Order findPlacedOrderNegative(OrderDTO orderDTO) {
    storeService.getOrder(orderDTO.getId())
        .statusCode(HttpStatus.SC_NOT_FOUND);
    return this;
  }

  private Order deleteOrder(OrderDTO orderDTO) {
    storeService.deleteOrder(orderDTO.getId())
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/DeletePetStoreItem.json"))
        .body("code", equalTo(200))
        .body("message", equalTo(String.valueOf(orderDTO.getId())));

    return this;
  }

  private Order deleteOrderNegative(OrderDTO orderDTO) {
    storeService.deleteOrder(orderDTO.getId())
        .statusCode(HttpStatus.SC_NOT_FOUND);
    return this;
  }

  public void checkOrderCreate(OrderDTO orderDTO) {
    placeOrder(orderDTO)
        .findPlacedOrder(orderDTO)
        .deleteOrder(orderDTO)
        .findPlacedOrderNegative(orderDTO);
  }

  public void checkOrderFind(OrderDTO orderDTO) {
    findPlacedOrderNegative(orderDTO)
        .placeOrder(orderDTO)
        .findPlacedOrder(orderDTO)
        .deleteOrder(orderDTO)
        .findPlacedOrderNegative(orderDTO);
  }

  public void checkOrderDelete(OrderDTO orderDTO) {
    deleteOrderNegative(orderDTO)
        .placeOrder(orderDTO)
        .findPlacedOrder(orderDTO)
        .deleteOrder(orderDTO)
        .findPlacedOrderNegative(orderDTO);
  }
}
