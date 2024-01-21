package petstore;

import static org.hamcrest.Matchers.equalTo;

import com.github.javafaker.Faker;
import data.PetStatusData;
import dto.CreatePetResponseDTO;
import dto.PetDTO;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import service.PetService;

public class Pet extends AbsPetstoreObject {

  private final PetService petService = new PetService();
  private final Faker faker = getFaker();

  public PetDTO createPetNoUrl() {
    return PetDTO
        .builder()
        .id(faker.number().randomNumber(3, true))
        .categoryId(faker.number().randomNumber(2, true))
        .categoryName(faker.animal().name())
        .name(faker.funnyName().name())
        .status(PetStatusData.AVAILABLE.getName())
        .build();
  }

  private Pet createPet(PetDTO petDTO) {
    CreatePetResponseDTO createPetResponseDTO = petService.createPet(petDTO)
        .extract().response().as(CreatePetResponseDTO.class);
    Assertions.assertAll("Create Pet assertions",
        () -> Assertions.assertEquals(petDTO.getId(), createPetResponseDTO.getId(), "Incorrect pet id."),
        () -> Assertions.assertEquals(petDTO.getName(), createPetResponseDTO.getName(), "Incorrect pet name."),
        () -> Assertions.assertEquals(petDTO.getStatus(), createPetResponseDTO.getStatus(), "Incorrect pet status.")
    );

    return this;
  }

  private Pet updatePetById(PetDTO petDTO, String newName) {
    petDTO.setName(newName);
    petService.updatePet(petDTO)
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body("name", equalTo(newName));

    return this;
  }

  private Pet deletePetById(PetDTO petDTO) {
    petService.deletePet(petDTO.getId())
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/DeletePetStoreItem.json"))
        .body("code", equalTo(200))
        .body("message", equalTo(String.valueOf(petDTO.getId())));

    return this;
  }

  private Pet deletePetByIdNegative(PetDTO petDTO) {
    petService.deletePet(petDTO.getId())
        .statusCode(HttpStatus.SC_NOT_FOUND);

    return this;
  }

  private Pet findPetById(PetDTO petDTO) {
    petService.getPetByPetId(petDTO.getId())
        .statusCode(HttpStatus.SC_OK)
        .body("id", equalTo(petDTO.getId().intValue()))
        .body("name", equalTo(petDTO.getName()));

    return this;
  }

  private Pet findPetByIdNegative(PetDTO petDTO) {
    petService.getPetByPetId(petDTO.getId())
        .statusCode(HttpStatus.SC_NOT_FOUND);

    return this;
  }

  public void checkCreatePet(PetDTO petDTO) {
    createPet(petDTO)
        .findPetById(petDTO)
        .deletePetById(petDTO)
        .findPetByIdNegative(petDTO);
  }

  public void checkUpdateRep(PetDTO petDTO, String newName) {
    findPetByIdNegative(petDTO)
        .createPet(petDTO)
        .findPetById(petDTO)
        .updatePetById(petDTO, newName)
        .findPetById(petDTO)
        .deletePetById(petDTO)
        .findPetByIdNegative(petDTO);
  }

  public void checkDeletePet(PetDTO petDTO) {
    deletePetByIdNegative(petDTO)
        .createPet(petDTO)
        .findPetById(petDTO)
        .deletePetById(petDTO)
        .findPetByIdNegative(petDTO);

  }

}
