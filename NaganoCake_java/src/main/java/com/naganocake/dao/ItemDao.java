package com.naganocake.dao;

import java.util.List;

import com.naganocake.model.Item;

public interface ItemDao {
	
	/**
     * 商品情報を取得する
     * @param member 初期化した商品情報
     * @return データベースに存在する商品情報
     */
    Item selectById(int id); // select
    
    List<Item> selectAll(); // select * from Item;
}