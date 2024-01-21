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
   *
   * Create pet in pet store and place it in system,
   * Find pet in system,
   * Delete pet,
   * Check that pet does not exist in system.
   */
  @Test
  public void createPetTest() {
    pet.checkCreatePet(petDTO);
  }

  /* Swagger pet store pet UPDATE test.
   *
   * Check that pet does not exist in system,
   * Create pet in pet store and place it in system,
   * Find pet in system,
   * Update pet name in system,
   * Find pet with new name in system,
   * Delete pet,
   * Check that pet does not exist in system.
   */
  @Test
  public void updatePet() {
    pet.checkUpdateRep(petDTO, "ZHOOOZHAA");
  }

  /*
   * Swagger pet store pet DELETE test.
   *
   * Delete pet that does not exist,
   * Create pet in pet store and place it in system,
   * Find pet in system,
   * Delete pet,
   * Check that pet does not exist in system.
   */
  @Test
  public void deletePet() {
    pet.checkDeletePet(petDTO);
  }

}
