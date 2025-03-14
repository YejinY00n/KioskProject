package com.sparta.kiosk;

// 할인 유형 Enum
public enum Discount {
  DISABLED("장애인", 0.15),
  STUDENT("학생", 0.1),
  MILITARY("군인", 0.1),
  CITIZEN("일반", 0.0);

  private String target;
  private double discountRate;

  Discount(String target, double discountRate) {
    this.target = target;
    this.discountRate = discountRate;
  }

  // 할인 대상 Getter
  public String getTarget() {
    return target;
  }

  // 할인율 Getter
  public double getDiscountRate() {
    return discountRate;
  }
}
