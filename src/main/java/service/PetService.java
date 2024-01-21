package service;

import static io.restassured.RestAssured.given;

import dto.PetDTO;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PetService extends AbsPetStoreApi {
  private final String petUri = "/pet";
  private final String findPetByPetIdUri = "/pet/%d";
  private final RequestSpecification requestSpec = getRequestSpec();

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
