package com.sparta.kiosk;

import java.util.ArrayList;

// MenuItem 클래스 관리
public class Menu {
  private ArrayList<MenuItem> burgerList;
  private ArrayList<MenuItem> drinkList;
  private ArrayList<MenuItem> sideDishList;

  // 생성 시 메뉴 세팅
  Menu() {
    burgerList = new ArrayList<>();
    drinkList = new ArrayList<>();
    sideDishList = new ArrayList<>();
    initMenuItems();
  }

  private void initMenuItems() {
    // 햄버거
    burgerList.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
    burgerList.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
    burgerList.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
    burgerList.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

    // 음료
    drinkList.add(new MenuItem("Coke", 3.5, "코카콜라"));
    drinkList.add(new MenuItem("Sprite", 3.5, "스프라이트"));
    drinkList.add(new MenuItem("Orange Drink", 4.0, "오렌지 쥬스"));

    // 사이드 메뉴
    sideDishList.add(new MenuItem("French Fries", 2.5, "감자 튀김"));
    sideDishList.add(new MenuItem("Cheese Sticks", 3.5, "치즈 스틱"));
    sideDishList.add(new MenuItem("Cone Salad", 3.0, "콘샐러드"));
  }

  // 메뉴 출력 메소드
  public void printAllMenu() {
    System.out.println("[ SHAKESHACK MENU ]");
    System.out.println("\n=== HAMBURGER ===");
    for(int i=0; i<burgerList.size(); i++) {
      System.out.printf("%d. %15s | W %.2f | %s\n", i, burgerList.get(i).name, burgerList.get(i).price, burgerList.get(i).info);
    }
    System.out.println("\n=== DRINKS ===");
    for(int i=0; i<drinkList.size(); i++) {
      System.out.printf("%d. %15s | W %.2f | %s\n", i, drinkList.get(i).name, drinkList.get(i).price, drinkList.get(i).info);
    }
    System.out.println("\n=== SIDE DISHES ===");
    for(int i=0; i<sideDishList.size(); i++) {
      System.out.printf("%d. %15s | W %.2f | %s\n", i, sideDishList.get(i).name, sideDishList.get(i).price, sideDishList.get(i).info);
    }
    System.out.println("0. 종료");
  }
}
