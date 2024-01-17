package data;

import lombok.Getter;

@Getter
public enum PetStatusData {
  AVAILABLE("available"),
  PENDING("pending"),
  SOLD("sold");

  private final String name;

  PetStatusData(String name) {
    this.name = name;
  }
}
