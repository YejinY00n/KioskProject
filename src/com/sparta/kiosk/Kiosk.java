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
    String tmpInput = null;
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
        // 종료
        category = sc.nextInt();
        if(category == 0) {
          break;
        }
        // 장바구니 확인 후 주문
        else if(category == 4) {
          while(true) {
            System.out.println("아래와 같이 주문하시겠습니까?");
            cart.printCart();
            System.out.println("1. 주문 \t2. 장바구니 변경\t3. 메뉴판");
            input = sc.nextInt();

            // 장바구니에 담긴 메뉴 삭제
            if(input == 2) {
              System.out.println("장바구니에서 삭제할 메뉴명을 입력해주세요 (뒤로가기: exit)");
              cart.printCart();
              System.out.print(">> ");
              tmpInput = sc.nextLine(); // TODO: 공백 포함 메뉴명 입력 시 오류

              // 뒤로가기
              if(tmpInput.equals("exit")) {
                continue;
              }
              else {
                cart.deleteItemCart(tmpInput);
                System.out.printf("장바구니에서 %s 메뉴가 삭제 되었습니다.\n\n", tmpInput);
                continue;
              }
            }
            else {
              break;
            }
          }

          // 주문 후 장바구니 비우기
          if(input == 1) {
            System.out.println("할인 정보를 입력해주세요");
            for(int i=0; i < Discount.values().length; i++) {
              System.out.printf("%d. %10s :  %.1f %% \n", i+1, Discount.values()[i].getTarget(), Discount.values()[i].getDiscountRate()*100);
            }
            System.out.print(">> ");
            input = sc.nextInt();

            System.out.printf("주문이 완료되었습니다. 금액은 W %.2f 입니다.\n\n", cart.totalCart()*(1-Discount.values()[input-1].getDiscountRate()));
            cart.clearCart();
            continue;
          }
          // 메뉴판 (메인 메뉴로 돌아가기)
          else if(input == 3) {
            continue;
          }
        }
        // 진행 중인 주문 취소 (장바구니 비우기)
        else if (input == 5) {
          cart.clearCart();
          continue;
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
