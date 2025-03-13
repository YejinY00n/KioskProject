package com.sparta.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// 프로그램 순서 및 흐름 제어 담당
public class Kiosk {

  private final Scanner sc;
  private final List<Menu> menuList;
  private final ShoppingCart cart;
  private List<String> orderCategoryList;     // TODO: Order 관련 메뉴는 어떻게 관리할 것인지?


  Kiosk() {
    sc = new Scanner(System.in);
    cart = new ShoppingCart();
    menuList = new ArrayList<>();
    initCategoryList();
    orderCategoryList = new ArrayList<>();
    initOrderCategoryList();
  }

  // 키오스크 메인 실행 함수
  public void start() {
    int selectedOption = -1;        // TODO: 변수명 이게 최선인가?
    int itemIndex = -1;

    // 사용자 입력이 0 이면 종료
    while (true) {
      // 카테고리 출력
      printCategory(cart.isEmpty());
      try {
        selectedOption = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("유효하지 않은 입력입니다.");
        continue;
      }

      // 0 입력 시, 종료
      if (selectedOption == 0) {
        break;
      }
      // 메뉴 카테고리 중 선택
      else if (!menuList.isEmpty() && (selectedOption >= 1 && selectedOption <= menuList.size())) {
        // 메뉴 카테고리 출력 후 메뉴 선택
        menuList.get(selectedOption-1).printMenus();
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
        // 장바구니에 메뉴 담기
        try{
          askToAddCart(menuList.get(selectedOption-1).getMenuItem(itemIndex-1));
        } catch (NumberFormatException e) {
          System.out.println("유효하지 않은 입력입니다.");
          continue;
        }
      }
      // 장바구니 확인 후 주문
      else if (selectedOption == menuList.size()+1) {
        orderCart(selectedOption);
      }
      // 진행 중인 주문 취소 (장바구니 비우기)
      else if (selectedOption == 5) {
        cart.clearCart();
        continue;
      }
    }
  }

  // 카테고리 출력 메소드
  public void printCategory(boolean isCartEmpty) {
    // 메뉴 카테고리 출력
    System.out.println("[ \uD83C\uDF54 SHAKESHACK MAIN MENU \uD83C\uDF54 ]");
    for(int i=0; i< menuList.size(); i++) {
      System.out.printf("%d. %10s\n", i+1, menuList.get(i).getCategory());
    }

    // 장바구니에 물건이 있다면 주문 카테고리 출력
    if(!isCartEmpty) {
      System.out.println("[ \uD83E\uDDFE ORDERS \uD83E\uDDFE ]");
      for(int i=menuList.size()+1; i < menuList.size()+3; i++) {
        System.out.printf("%d. %10s\n", i, orderCategoryList.get(i-(menuList.size()+1)));
      }
    }

    System.out.println("0. 종료");
    System.out.print(">> 카테고리를 선택해주세요: ");
  }

  // 장바구니에 메뉴 담기 여부 처리 메소드
  public void askToAddCart(MenuItem menuItem) throws NumberFormatException {
    int input = -1;
    
    // 선택된 메뉴 출력
    System.out.printf("선택한 메뉴: %15s | W %.2f | %s\n\n", menuItem.getName(), menuItem.getPrice(), menuItem.getInfo());

    // 장바구니 메뉴 추가
    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
    System.out.println("1. 확인\t2. 취소");

    // 입력 받기
    try {
      input = Integer.parseInt(sc.nextLine());
    } catch (NumberFormatException e) {
      throw new NumberFormatException();
    }
    // 1 입력 시, 장바구니 메뉴 추가
    if (input == 1) {
      cart.addCart(menuItem);
    }
    // 2 입력 시, 메뉴 추가 취소
    else if (input == 2) {
      return;
    }
  }

  // 장바구니 주문 메뉴 메소드
  public void orderCart(int category) {
    int input;
    String tmpInput = null;

    while(true) {
      // 주문 확인
      System.out.println("아래와 같이 주문하시겠습니까?");
      cart.printCart();
      cart.printTotalCart();
      System.out.println("1. 주문 \t2. 장바구니 변경\t3. 메인 메뉴");
      System.out.print(">> ");
      try {
        input = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("유효하지 않은 입력입니다.");
        continue;
      }

      // 장바구니 변경: 장바구니에 담긴 메뉴 삭제
      if (input == 2) {
        System.out.println("장바구니에서 삭제할 메뉴명을 입력해주세요 (주문 확인으로 돌아가려면 x 입력)");
        cart.printCart();
        System.out.print(">> ");
        try {
          tmpInput = sc.nextLine();
        } catch (Exception e) {
          System.out.println("유효하지 않은 입력입니다.");
          continue;
        }

        // 주문 확인으로 돌아가기
        if(!tmpInput.equalsIgnoreCase("x")) {
          cart.deleteItemCart(tmpInput);
          System.out.printf("장바구니에서 %s 메뉴가 삭제 되었습니다.\n\n", tmpInput);
        }
      }
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
        // 메인 메뉴 이동
        System.out.println("유효하지 않은 입력입니다.");
        return;
      }
      System.out.printf("주문이 완료되었습니다. 금액은 W %.2f 입니다.\n\n",
          cart.getTotalPrice() * (1 - Discount.values()[input - 1].getDiscountRate()));

      // 장바구니 비우기 후 메인 메뉴로 이동
      cart.clearCart();
    }
    // 메뉴판 (메인 메뉴로 돌아가기)
    else if (input == 3) {
      return;
    }
  }

  // 주문 카테고리 리스트 초기화      // TODO : 이거 구현 부분은 알아서
  private void initOrderCategoryList() {
    orderCategoryList.add("Orders\t\t 장바구니를 확인 후 주문합니다.");
    orderCategoryList.add("Cancel\t\t 진행중인 주문을 취소합니다.");
  }

  // 카테고리 리스트 초기화
  private void initCategoryList() {
    Menu tmp = new Menu("Hamburgers");
    tmp.setMenuItems(List.of(
        new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
        new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
        new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
        new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
    ));
    menuList.add(tmp);

    tmp = new Menu("Drinks");
    tmp.setMenuItems(List.of(
        new MenuItem("Coke", 3.5, "코카콜라"),
        new MenuItem("Sprite", 3.5, "스프라이트"),
        new MenuItem("Orange Drink", 4.0, "오렌지 쥬스")
    ));
    menuList.add(tmp);

    tmp = new Menu("Side menu");
    tmp.setMenuItems(List.of(
        new MenuItem("French Fries", 1.0, "감자 튀김"),
        new MenuItem("Cheese Sticks", 3.5, "치즈 스틱"),
        new MenuItem("Cone Salad", 3.0, "콘샐러드")
    ));
    menuList.add(tmp);
  }
}
