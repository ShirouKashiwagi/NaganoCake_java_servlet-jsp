package com.naganocake.dao;

import java.util.List;

import com.naganocake.model.CartItem;

public interface CartItemDao {
      boolean insert(CartItem cartItem); // insert
      
      List<CartItem> selectById(int id); // select * from where id = ?;
}