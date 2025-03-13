package com.sparta.kiosk;

import java.util.ArrayList;
import java.util.List;

// MenuItem 클래스 관리
public class Menu {
    private String menuCategoryName;
    private List<MenuItem> menuItems;

  Menu(String menuCategoryName) {
    this.menuCategoryName = menuCategoryName;
    menuItems = new ArrayList<>();
  }

  // 메뉴 아이템 Getter
  public MenuItem getMenuItem(int menuIndex) {
    return menuItems.get(menuIndex);
  }

  // 메뉴 아이템 추가
  public void addMenuItem(MenuItem menuItem) {
    menuItems.add(menuItem);
  }


  // 메뉴 아이템 리스트 Getter
  public List<MenuItem> getMenuItems() {
    return menuItems;
  }

  // 메뉴 아이템 리스트 Setter
  public void setMenuItems(List<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  // 메뉴 아이템 리스트를 출력하는 메소드
  public void printMenus() {
    System.out.printf("=== %s ===\n", menuCategoryName.toUpperCase());
    for(int i=0; i<menuItems.size(); i++) {
      System.out.printf("%d. %15s | W %.2f | %s\n", i+1, menuItems.get(i).getName(), menuItems.get(i).getPrice(), menuItems.get(i).getInfo());
    }
  }

  // 메뉴 카테고리 이름을 반환하는 메소드
  public String getCategory() {
      return menuCategoryName;
  }
}
