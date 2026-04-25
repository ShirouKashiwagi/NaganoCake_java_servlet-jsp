package com.naganocake.dao;

import java.util.List;

import com.naganocake.entity.CartItemEntity;

public interface CartItemDao {
      boolean insert(int newAmount, int memberId, int itemId); // insert
      
      List<CartItemEntity> selectByMemberId(int memberId); // select * from where id = ?;
      
      Integer countAmount(int memberId, int itemId); // select count(*) from cart_items where member_id = ? AND item_id = ?;
      
      void update(int newAmount, int memberId, int itemId); // UPDATE cart_items SET item_id, amount WHERE member_id = ? AND item_id = ?;
}