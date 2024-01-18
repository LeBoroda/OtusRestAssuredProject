import dto.PetDTO;
import org.junit.jupiter.api.Test;
import petstore.Pet;

/*
 * Automated tests for Swagger PetStore Order create, find and delete methods.
 */
public class PetStorePet_Test {

  //Java Pet object that will create DTO object and run test methods
  private final Pet pet = new Pet();

  //OrderDTO object with SOME fields filled
  private final PetDTO petDTO = pet.createPetNoUrl();

  /*
   * Swagger pet store pet CREATE test.
   * Check for pet id, name and status with responseDTO object.
   * Delete pet after test. Check for OK code.
   */
  @Test
  public void createPetTest() {
    pet.createPet(petDTO);
  }

  /* Swagger pet store pet UPDATE test.
   * Get pet that does not exist by ID. Should be negative. Check for code 404.
   * Create pet in system. Should be positive. Check for code 200.
   * Update pet name in system. Should be positive.
   * Check for response HTTP code, contentType, new pet name.
   * Delete pet after test. Check for OK code.
   */
  @Test
  public void updatePet() {
    pet.updatePetById(petDTO, "ZHOOOZHAA");
  }

  /*
   * Swagger pet store pet DELETE test.
   * Delete pet that does not exist. Should be negative. Check for code 404.
   * Create pet in system. Should be positive. Check for code 200.
   * Delete pet by id. Check for response HTTP code, contentType, validity of response json schema.
   * Check response body for code 200 and message equal to pet ID number present.
   */
  @Test
  public void deletePet() {
    pet.deletePetById(petDTO);
  }

}
