package com.sparta.kiosk;

import java.util.Scanner;


// 프로그램 순서 및 흐름 제어 담당
public class Kiosk {

  private final Scanner sc;
  private final Menu menu;
  private final ShoppingCart cart;


  Kiosk() {
    sc = new Scanner(System.in);
    menu = new Menu();
    cart = new ShoppingCart();
  }

  // 키오스크 메인 실행 함수
  public void start() {
    int category = -1;
    int itemIndex = -1;
    int input = -1;
    String tmpInput = null;
    MenuItem selectedMenu = null;

    // 사용자 입력이 0 이면 종료
    while (true) {
      // 카테고리 출력
      menu.printCategory(cart.isEmpty());
      try {
        category = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("유효하지 않은 입력입니다.");
        continue;
      }

      // 0 입력 시, 종료
      if (category == 0) {
        break;
      }
      // 메뉴 카테고리 중 선택
      else if (category >= 1 && category <= 3) {
        // 메뉴 카테고리 출력 후 메뉴 선택
        menu.printMenu(category);
        try {
          itemIndex = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("유효하지 않은 입력입니다.");
          continue;
        }

        // 0 입력 시, 뒤로 가기 (메인 메뉴로)
        if (itemIndex == 0) {
          continue;
        }
        // 메뉴 선택
        else {
          selectedMenu = menu.getMenuItems(category, itemIndex);
          System.out.printf("선택한 메뉴: %15s | W %.2f | %s\n\n", selectedMenu.getName(),
              selectedMenu.getPrice(), selectedMenu.getInfo());

          // 장바구니 메뉴 추가
          System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
          System.out.println("1. 확인\t2. 취소");

          // 1 입력 시, 장바구니 메뉴 추가
          try {
            input = Integer.parseInt(sc.nextLine());
          } catch (NumberFormatException e) {
            System.out.println("유효하지 않은 입력입니다.");
            continue;
          }
          if (input == 1) {
            cart.addCart(selectedMenu);
          }
          // 2 입력 시, 메뉴 추가 취소
          else if (input == 2) {
            continue;
          }
        }
      }
      // 장바구니 확인 후 주문
      else if (category == 4) {
        while (true) {
          System.out.println("아래와 같이 주문하시겠습니까?");
          cart.printCart();
          cart.printTotalCart();
          System.out.println("1. 주문 \t2. 장바구니 변경\t3. 메뉴판");
          System.out.print(">> ");
          try {
            input = Integer.parseInt(sc.nextLine());
          } catch (NumberFormatException e) {
            System.out.println("유효하지 않은 입력입니다.");
            continue;
          }

          // 장바구니에 담긴 메뉴 삭제
          if (input == 2) {
            System.out.println("장바구니에서 삭제할 메뉴명을 입력해주세요 (뒤로가기: x)");
            cart.printCart();
            System.out.print(">> ");
            try {
              tmpInput = sc.nextLine();
            } catch (Exception e) {
              System.out.println("유효하지 않은 입력입니다.");
              continue;
            }

            // 뒤로가기
            if(!tmpInput.equalsIgnoreCase("x")) {
              cart.deleteItemCart(tmpInput);
              System.out.printf("장바구니에서 %s 메뉴가 삭제 되었습니다.\n\n", tmpInput);
              continue;
            }
          } // 장바구니 비우기 or 메뉴판 메뉴로 이동
          else {
            break;
          }
        }
        // 주문 후 장바구니 비우기
        if (input == 1) {
          System.out.println("할인 정보를 입력해주세요");
          for (int i = 0; i < Discount.values().length; i++) {
            System.out.printf("%d. %10s :  %.1f %% \n", i + 1, Discount.values()[i].getTarget(),
                Discount.values()[i].getDiscountRate() * 100);
          }
          System.out.print(">> ");
          try {
            input = Integer.parseInt(sc.nextLine());
          } catch (NumberFormatException e) {
            System.out.println("유효하지 않은 입력입니다.");
            continue;
          }

          System.out.printf("주문이 완료되었습니다. 금액은 W %.2f 입니다.\n\n",
              cart.getTotalPrice() * (1 - Discount.values()[input - 1].getDiscountRate()));
          cart.clearCart();
          continue;           // 메인 메뉴로 이동
        }
        // 메뉴판 (메인 메뉴로 돌아가기)
        else if (input == 3) {
          continue;
        }
      }
      // 진행 중인 주문 취소 (장바구니 비우기)
      else if (category == 5) {
        cart.clearCart();
        continue;
      }
    }
  }
}
