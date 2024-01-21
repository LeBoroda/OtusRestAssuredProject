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
   *
   * Create order and place it in system,
   * Find order in system and check it,
   * Delete created order,
   * Check that order does not exist in system.
   */
  @Test
  public void orderCreateTest() {
    order.checkOrderCreate(orderDTO);
  }

  /*
   * Swagger pet store FIND order testing.
   *
   * Find order that does not exist,
   * Create order and place it in system,
   * Find order in system and check it
   * Delete created order,
   * Check that order does not exist.
   */
  @Test
  public void orderFindTest() {
    order.checkOrderFind(orderDTO);
  }

  /*
   * Swagger pet store DELETE order testing.
   *
   * Delete order that does not exist
   * Create order and place it in system,
   * Find order in system and check it
   * Delete created order,
   * Check that order does not exist.
   */
  @Test
  public void orderDeleteTest() {
    order.checkOrderDelete(orderDTO);
  }
}
