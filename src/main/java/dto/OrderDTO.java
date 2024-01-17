
package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderDTO {

  private Long Id;
  private Long PetId;
  private Long Quantity;
  private String ShipDate;
  private String Status;
  private Boolean Complete;
}
