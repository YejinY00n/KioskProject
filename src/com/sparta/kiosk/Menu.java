package com.sparta.kiosk;

import java.util.ArrayList;
import java.util.List;

// MenuItem 클래스 관리
public class Menu {
  private List<MenuItem> burgerList;
  private List<MenuItem> drinkList;
  private List<MenuItem> sideMenuList;
  private List<String> categoryList;
  private List<String> orderCategoryList;

  Menu() {
    burgerList = new ArrayList<>();
    drinkList = new ArrayList<>();
    sideMenuList = new ArrayList<>();
    categoryList = new ArrayList<>();
    orderCategoryList = new ArrayList<>();
    initMenuItems();
    initCategoryList();
    initOrderCategoryList();
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

  // 카테고리 리스트 초기화
  private void initCategoryList() {
    categoryList.add("Burgers");
    categoryList.add("Drinks");
    categoryList.add("Side menu");
  }

  private void initOrderCategoryList() {
    orderCategoryList.add("Orders\t\t 장바구니를 확인 후 주문합니다.");
    orderCategoryList.add("Cancel\t\t 진행중인 주문을 취소합니다.");
  }

  // 카테고리 출력 메소드
  public void printCategory(boolean isCartEmpty) {
    // 메뉴 카테고리 출력
    System.out.println("[ \uD83C\uDF54 SHAKESHACK MAIN MENU \uD83C\uDF54 ]");
    for(int i=0; i<categoryList.size(); i++) {
      System.out.printf("%d. %10s\n", i+1, categoryList.get(i));
    }

    // 장바구니에 물건이 있다면 주문 카테고리 출력
    if(!isCartEmpty) {
      System.out.println("[ \uD83E\uDDFE ORDERS \uD83E\uDDFE ]");
      for(int i=categoryList.size()+1; i < categoryList.size()+orderCategoryList.size()+1; i++) {
        System.out.printf("%d. %10s\n", i, orderCategoryList.get(i-(categoryList.size()+1)));
      }
    }

    System.out.println("0. 종료");
    System.out.print(">> 카테고리를 선택해주세요: ");
  }

  // 메뉴 출력 메소드
  public void printMenu(int category) {
    System.out.println("[ \uD83D\uDC81 SHAKESHACK MAIN MENU \uD83D\uDC81 ]");
    if(category == 1) {
      System.out.println("\n=== \uD83C\uDF54 HAMBURGER \uD83C\uDF54 ===");
      for(int i=0; i<burgerList.size(); i++) {
        System.out.printf("%d. %15s | W %.2f | %s\n", i+1, burgerList.get(i).getName(), burgerList.get(i).getPrice(), burgerList.get(i).getInfo());
      }
    }
    else if(category == 2) {
      System.out.println("\n=== \uD83E\uDD64 DRINKS \uD83E\uDD64 ===");
      for(int i=0; i<drinkList.size(); i++) {
        System.out.printf("%d. %15s | W %.2f | %s\n", i+1, drinkList.get(i).getName(), drinkList.get(i).getPrice(), drinkList.get(i).getInfo());
      }
    }
    else if(category == 3) {
      System.out.println("\n=== \uD83C\uDF5F SIDE MENU \uD83C\uDF5F ===");
      for(int i=0; i< sideMenuList.size(); i++) {
        System.out.printf("%d. %15s | W %.2f | %s\n", i+1, sideMenuList.get(i).getName(), sideMenuList.get(i).getPrice(), sideMenuList.get(i).getInfo());
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

  // 메뉴 카테고리 이름을 반환하는 메소드
  public String getCategory(int category) {
    return categoryList.get(category-1);
  }
}
