package com.sparta.kiosk;

import java.util.ArrayList;

// MenuItem 클래스 관리
public class Menu {
  private ArrayList<MenuItem> burgerList;
  private ArrayList<MenuItem> drinkList;
  private ArrayList<MenuItem> sideMenuList;

  Menu() {
    burgerList = new ArrayList<>();
    drinkList = new ArrayList<>();
    sideMenuList = new ArrayList<>();
    initMenuItems();
  }

  // 메뉴 아이템 초기화
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
    sideMenuList.add(new MenuItem("French Fries", 1.0, "감자 튀김"));
    sideMenuList.add(new MenuItem("Cheese Sticks", 3.5, "치즈 스틱"));
    sideMenuList.add(new MenuItem("Cone Salad", 3.0, "콘샐러드"));
  }

  // 카테고리 출력 메소드
  public void printCategory() {
    System.out.println("[ SHAKESHACK MAIN MENU ]");
    System.out.println("1. Bugers");
    System.out.println("2. Drinks");
    System.out.println("3. Side menu");
    System.out.println("0. 종료");
    System.out.print(">> 카테고리를 선택해주세요: ");
  }

  // 메뉴 출력 메소드
  public void printMenu(int category) {
    System.out.println("[ SHAKESHACK MAIN MENU ]");
    if(category == 1) {
      System.out.println("\n=== HAMBURGER ===");
      for(int i=0; i<burgerList.size(); i++) {
        System.out.printf("%d. %15s | W %.2f | %s\n", i+1, burgerList.get(i).name, burgerList.get(i).price, burgerList.get(i).info);
      }
    }
    else if(category == 2) {
      System.out.println("\n=== DRINKS ===");
      for(int i=0; i<drinkList.size(); i++) {
        System.out.printf("%d. %15s | W %.2f | %s\n", i+1, drinkList.get(i).name, drinkList.get(i).price, drinkList.get(i).info);
      }
    }
    else if(category == 3) {
      System.out.println("\n=== SIDE MENU ===");
      for(int i=0; i< sideMenuList.size(); i++) {
        System.out.printf("%d. %15s | W %.2f | %s\n", i+1, sideMenuList.get(i).name, sideMenuList.get(i).price, sideMenuList.get(i).info);
      }
    }
    System.out.println("0. 뒤로가기");
    System.out.print(">> 메뉴를 선택해주세요: ");
  }

  // 메뉴를 반환하는 메소드
  public MenuItem getMenuItems(int category, int menuIndex) {
    if(category == 1) {
      return burgerList.get(menuIndex-1);
    }
    else if(category == 2) {
      return drinkList.get(menuIndex-1);
    }
    else if(category == 3) {
      return sideMenuList.get(menuIndex-1);
    }
    else {
      return null;
    }
  }
}
