package petstore;

import com.github.javafaker.Faker;

public abstract class AbsPetstoreObject {

  private Faker faker = new Faker();

  public Faker getFaker() {
    return faker;
  }
}
