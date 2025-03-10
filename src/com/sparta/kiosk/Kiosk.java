package com.sparta.kiosk;

import java.util.InputMismatchException;
import java.util.Scanner;

// 프로그램 순서 및 흐름 제어 담당
public class Kiosk {
  private Scanner sc;
  private Menu menu;


  Kiosk() {
    sc = new Scanner(System.in);
    menu = new Menu();
  }

  // 키오스크 메인 실행 함수
  public void start() {
    int category = -1;
    int itemIndex = -1;
    MenuItem selectedMenu = null;

    // 사용자 입력이 0 이면 종료
    while(true) {
      // 카테고리
      menu.printCategory();
      try {
        category = sc.nextInt();
        if(category == 0) {
          break;
        }
      }
      // 유효하지 않은 입력 예외 처리
      catch (InputMismatchException e) {
        System.out.println("유효하지 않은 입력입니다.");
        continue;
      }
      // 메뉴 입력
      menu.printMenu(category);
      try {
        itemIndex = sc.nextInt();
        if(itemIndex == 0) {
          continue;
        }
      }
      // 유효하지 않은 입력 예외 처리
      catch (InputMismatchException e) {
        System.out.println("유효하지 않은 입력입니다.");
        continue;
      }
      selectedMenu = menu.getMenuItems(category, itemIndex);
      System.out.printf("선택한 메뉴: %15s | W %.2f | %s\n\n", selectedMenu.name, selectedMenu.price, selectedMenu.info);
    }
  }

}
