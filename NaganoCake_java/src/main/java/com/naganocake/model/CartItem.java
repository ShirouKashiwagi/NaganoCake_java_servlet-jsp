package com.naganocake.model;

import java.time.LocalDateTime;

public class CartItem {
  private int id;
	private int memberId;
	private int itemId;
	private String name;
	private int price;
  private int amount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

	// 不要 - カートに追加するのはCartItemではなくコントローラーの責務
//  public void add(CartItem cartItem) {
//  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMemberId() {
    return memberId;
  }

  public void setMemberId(int memberId) {
    this.memberId = memberId;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  } 

  public void setPrice(int price) {
    this.price = price;
  }
  
  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}