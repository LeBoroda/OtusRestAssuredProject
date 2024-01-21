package petstore;

import com.github.javafaker.Faker;
import lombok.Getter;

@Getter
public abstract class AbsPetstoreObject {

  private final Faker faker = new Faker();

}
