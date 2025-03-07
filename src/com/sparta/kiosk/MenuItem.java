package com.sparta.kiosk;

// 세부 메뉴 속성
public class MenuItem {
  String name;            // 메뉴명
  double price;           // 가격
  String info;     // 메뉴 설명

  public MenuItem(String name, double price, String info) {
    this.name = name;
    this.price = price;
    this.info = info;
  }
}
