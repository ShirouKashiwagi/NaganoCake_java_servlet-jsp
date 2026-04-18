package com.naganocake.dao;

import java.util.List;

import com.naganocake.model.CartItem;

public interface CartItemDao {
      boolean insert(CartItem cartItem); // insert
      
      List<CartItem> selectByMemberId(int id); // select * from where id = ?;
      
      Integer countAmount(int memberId, int itemId); // select count(*) from cart_items where member_id = ? AND item_id = ?;
      
      void update(CartItem cartItem); // UPDATE cart_items SET item_id, amount WHERE member_id = ? AND item_id = ?;
}