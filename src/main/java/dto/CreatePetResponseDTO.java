package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePetResponseDTO {

  private Long id;
  private String name;
  private List<Object> photoUrls;
  private String status;
  private List<Object> tags;

}
