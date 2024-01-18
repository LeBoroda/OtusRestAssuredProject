package petstore;

import data.PetStatusData;
import dto.PetDTO;

public class Pet extends AbsPetstoreObject {
  public PetDTO createPetNoUrl() {
    return PetDTO
        .builder()
        .id(faker.number().randomNumber(3, true))
        .categoryId(faker.number().randomNumber(2, true))
        .categoryName(faker.animal().name())
        .name(faker.funnyName().name())
        .status(PetStatusData.AVAILABLE.getName())
        .tagId(faker.number().randomNumber(2, true))
        .tagName(faker.commerce().productName())
        .build();
  }

  public void createPet(PetDTO petDTO){}
  public void findPetById(PetDTO petDTO){}
  public void updatePetById(PetDTO petDTO){}
  public void deletePetById(PetDTO petDTO){}
}
