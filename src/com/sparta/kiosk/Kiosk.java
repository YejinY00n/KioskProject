package com.sparta.kiosk;

import java.util.InputMismatchException;
import java.util.Scanner;

// 프로그램 순서 및 흐름 제어 담당
public class Kiosk {
  private Scanner sc;
  private Menu menu;
  private ShoppingCart cart;


  Kiosk() {
    sc = new Scanner(System.in);
    menu = new Menu();
    cart = new ShoppingCart();
  }

  // 키오스크 메인 실행 함수
  public void start() {
    int category = -1;
    int itemIndex = -1;
    int cartAdd = -1;
    int input = -1;
    MenuItem selectedMenu = null;

    // 사용자 입력이 0 이면 종료
    while(true) {
      try {
        // 카테고리 입력
        menu.printCategory();
        if(cart != null) {
          System.out.println("4. Orders\t\t 장바구니를 확인 후 주문합니다.");
          System.out.println("5. Cancel\t\t 진행중인 주문을 취소합니다.");
        }

        category = sc.nextInt();
        if(category == 0) {
          break;
        }
        // 장바구니 확인 후 주문
        else if(category == 4) {
          System.out.println("아래와 같이 주문하시겠습니까?");
          cart.printCart();
          System.out.println("1. 주문 \t2. 메뉴판");
          input = sc.nextInt();

          // 주문 후 장바구니 비우기
          if(input == 1) {
            System.out.printf("주문이 완료되었습니다. 금액은 W %.2f 입니다.\n\n", cart.totalCart());
            cart.clearCart();
            continue;
          }
          // 메인 메뉴로 돌아가기
          else if(input == 2) {
            continue;
          }
        }

        // 메뉴 입력
        menu.printMenu(category);
        itemIndex = sc.nextInt();

        // 0 입력 시, 뒤로 가기 (메인 메뉴로)
        if(itemIndex == 0) {
          continue;
        }

        selectedMenu = menu.getMenuItems(category, itemIndex);
        System.out.printf("선택한 메뉴: %15s | W %.2f | %s\n\n", selectedMenu.getName(), selectedMenu.getPrice(), selectedMenu.getInfo());

        // 장바구니 메뉴 추가
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인\t2. 취소");

        // 1 입력 시, 장바구니 메뉴 추가
        cartAdd = sc.nextInt();
        if(cartAdd == 1) {
          cart.addCart(selectedMenu);
        }
        // 2 입력 시, 메뉴 추가 취소
        else if(cartAdd == 2) {
          continue;
        }

      } // TODO: 예외 처리 추가
      catch (InputMismatchException e) { // 유효하지 않은 입력 예외 처리
        System.out.println("유효하지 않은 입력입니다.");
        continue;
      }
    }
  }

}
