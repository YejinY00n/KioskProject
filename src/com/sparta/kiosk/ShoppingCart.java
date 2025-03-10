package com.sparta.kiosk;

import java.util.ArrayList;

// 장바구니 클래스
public class ShoppingCart {
  private ArrayList<MenuItem> cart;

  ShoppingCart() {
    cart = new ArrayList<>();
  }

  // 장바구니 담기 메소드
  public void addCart(MenuItem item) {
    cart.add(item);
  }

  // 장바구니 출력 메소드
  public void printCart() {
    System.out.println("[ Orders ]");
    cart.forEach(item -> System.out.printf("%15s | W %.2f | %s\n", item.getName(), item.getPrice(), item.getInfo()));
  }

  // 장바구니 금액 계산 메소드
  public double totalCart() {
    double total = 0;

    for(int i=0; i<cart.size(); i++) {
      total += cart.get(i).getPrice();
    }

    return total;
  }

  // 장바구니 금액 출력 메소드
  public void printTotalCart() {
    System.out.println("[ Total ]");
    System.out.printf("W %.2f\n", totalCart());
  }
}
