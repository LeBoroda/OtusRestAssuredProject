import dto.OrderDTO;
import org.junit.jupiter.api.Test;
import petstore.Order;

/*
 * Automated tests for Swagger PetStore Order create, find and delete methods.
 */
public class PetStoreOrder_Test {

  //Java Order object that will create DTO object and run test methods
  private final Order order = new Order();

  //OrderDTO object with all fields filled
  private final OrderDTO orderDTO = order.createOrderAllFields();


  /*
   * Swagger pet store order CREATE testing.
   * Check for response HTTP code, contentType, validity of response json schema, presence of fields ID and PetId
   * Delete order after test
   */
  @Test
  public void orderCreateTest() {
    order.placeOrder(orderDTO);
  }

  /*
   * Swagger pet store FIND order testing.
   * Check is order exists. Should be negative. Check for code 404.
   * Place order in system. Should be positive. Check for code 200.
   * Find order by id. Check for response HTTP code, contentType, validity of response json schema.
   * Delete order after test.
   */
  @Test
  public void orderFindTest() {
    order.findPlacedOrder(orderDTO);
  }

  /*
   * Swagger pet store DELETE order testing.
   * Delete order that does not exist. Should be negative. Check for code 404.
   * Place order in system. Should be positive. Check for code 200.
   * Delete order by id. Check for response HTTP code, contentType, validity of response json schema.
   * Check response body for code 200 and message equal to order ID number present.
   */
  @Test
  public void orderDeleteTest() {
    order.deleteOrder(orderDTO);
  }
}
