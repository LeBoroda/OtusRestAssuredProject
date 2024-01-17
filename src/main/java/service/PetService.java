package service;

import dto.PetDTO;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class PetService extends AbsPetStoreApi {
  private final String petUri = "/pet";
  private final String findPetByPetIdUri = "/pet/%d";

  public ValidatableResponse createPet(PetDTO petDTO) {
    return given(requestSpec)
        .body(petDTO)
        .when()
        .post(petUri)
        .then()
        .log().all();
  }

  public ValidatableResponse getPetByPetId(Long petId) {
    return given(requestSpec)
        .when()
        .get(String.format(findPetByPetIdUri, petId))
        .then()
        .log().all();
  }

  public ValidatableResponse deletePet(Long petId) {
    return given(requestSpec)
        .when()
        .delete(String.format(findPetByPetIdUri, petId))
        .then()
        .log().all();
  }

  public ValidatableResponse updatePet(PetDTO petDTO) {
    return given(requestSpec)
        .body(petDTO)
        .when()
        .put(petUri)
        .then()
        .log().all();
  }
}
