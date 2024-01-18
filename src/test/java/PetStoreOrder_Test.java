import dto.OrderDTO;
import org.junit.jupiter.api.Test;
import petstore.Order;

public class PetStoreOrder_Test {

  private final Order order = new Order();
  OrderDTO orderDTO = new Order().createOrderAllFields();

  @Test
  public void orderCreateTest() {
    order.placeOrder(orderDTO);
  }
  @Test
  public void orderFindTest() {
    order.findPlacedOrder(orderDTO);
  }
  @Test
  public void orderDeleteTest() {
    order.deleteOrder(orderDTO);
  }
}
