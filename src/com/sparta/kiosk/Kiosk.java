package com.sparta.kiosk;

import java.util.Scanner;

// 프로그램 순서 및 흐름 제어 담당
public class Kiosk {
  Scanner sc;
  Menu menu;

  Kiosk() {
    sc = new Scanner(System.in);
    menu = new Menu();
  }

  // 키오스크 메인 실행 함수
  public void run() {
    int input = -1;

    while(input != 0) {
      menu.printAllMenu();
      input = sc.nextInt();
    }
  }
}
