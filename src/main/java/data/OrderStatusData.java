package data;

import lombok.Getter;

@Getter
public enum OrderStatusData {
  PLACED("placed"),
  APPROVED("addroved"),
  DELIVERED("delivered");

  private final String name;
  OrderStatusData(String name){
    this.name = name;
  }

}
