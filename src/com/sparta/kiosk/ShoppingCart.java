package com.sparta.kiosk;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
// TODO: Getter/Setter
// 장바구니 클래스
public class ShoppingCart {
  private Map<MenuItem, Integer> cart;

  ShoppingCart() {
    cart = new HashMap<>();
  }

  // 장바구니 담기 메소드
  public void addCart(MenuItem item) {
    // 처음 담는다면
    if(!cart.containsKey(item)) {
      cart.put(item, 1);                  // 메뉴의 수량을 1로 대입
    } else {
      cart.put(item, cart.get(item)+1);   // 메뉴의 수량 1 증가
    }
  }

  // 장바구니 메뉴 삭제 메소드
  public void deleteItemCart(String menuName) {
    cart = cart.entrySet().stream()
        .filter(entryItem -> !entryItem.getKey().getName().equals(menuName))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }

  // 장바구니 비우기 메소드
  public void clearCart() {
    cart.clear();
  }

  // 장바구니 출력 메소드
  public void printCart() {
    MenuItem tmpItem = null;

    System.out.println("[ Orders ]");
    for(Entry<MenuItem, Integer> item : cart.entrySet()) {
      tmpItem = item.getKey();
      System.out.printf("%15s | W %.2f | %s \t x %d 개\n", tmpItem.getName(), tmpItem.getPrice(), tmpItem.getInfo(), item.getValue());
    }
  }

  // 장바구니 총 금액 계산 메소드
  public double getTotalPrice() {
    double totalPrice = 0;

    for (Entry<MenuItem, Integer> menuItem : cart.entrySet()) {
      totalPrice += (menuItem.getKey().getPrice() * menuItem.getValue());   // 메뉴 가격 * 메뉴 개수
    }
    return totalPrice;
  }

  // 장바구니 총 갯수 계산 메소드
  public int getTotalCount() {
    int totalCount = 0;

    for (Entry<MenuItem, Integer> menuItem : cart.entrySet()) {
      totalCount += menuItem.getValue();
    }
    return totalCount;
  }

  // 장바구니 금액 출력 메소드
  public void printTotalCart() {
    System.out.println("[ Total ]");
    System.out.printf("총 메뉴 수량 : \t %d 개\n", getTotalCount());
    System.out.printf("W %.2f\n", getTotalPrice());
  }

  // 장바구니 비어있는지
  public boolean isEmpty() {
    return cart.isEmpty();
  }
}
