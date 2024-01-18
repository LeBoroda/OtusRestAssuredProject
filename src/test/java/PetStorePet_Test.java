import dto.PetDTO;
import org.junit.jupiter.api.Test;
import petstore.Pet;

public class PetStorePet_Test {

  private final Pet pet = new Pet();
  private final PetDTO petDTO = pet.createPetNoUrl();

  @Test
  public void createPetTest(){
    pet.createPet(petDTO);
  }

  @Test
  public void updatePet() {
    pet.updatePetById(petDTO);
  }

  @Test
  public void deletePet() {
    pet.deletePetById(petDTO);
  }
}
