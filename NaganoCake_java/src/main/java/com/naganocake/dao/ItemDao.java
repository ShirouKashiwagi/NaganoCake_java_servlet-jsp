package com.naganocake.dao;

import java.util.List;

import com.naganocake.entity.ItemEntity;
import com.naganocake.model.Item;

public interface ItemDao {
	
	/**
     * 商品情報を取得する
     * @param member 初期化した商品情報
     * @return データベースに存在する商品情報
     */
    ItemEntity selectById(int id); // select

    /**
     * 商品情報を全件取得する
     * @return データベースに存在する全ての商品情報
     */
    List<Item> selectAll(); // select * from Item;
}