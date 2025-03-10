package com.sparta.kiosk;

// 세부 메뉴 속성
public class MenuItem {
  private String name;            // 메뉴명
  private double price;           // 가격
  private String info;     // 메뉴 설명

  public MenuItem(String name, double price, String info) {
    this.name = name;
    this.price = price;
    this.info = info;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public String getInfo() {
    return info;
  }
}
