
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
public class PetDTO {

  private Long id;
  private Long categoryId;
  private String categoryName;
  private String name;
  private String photoUrls;
  private Long tagId;
  private String tagName;
  private String status;

}
