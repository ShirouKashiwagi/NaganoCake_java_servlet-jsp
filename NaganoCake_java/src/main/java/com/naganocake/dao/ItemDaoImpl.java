package com.naganocake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naganocake.model.Item;
import com.naganocake.util.ConnectionBase;

public class ItemDaoImpl implements ItemDao {

	public Item selectById(int id) {
		
		String sql = "select * from items where id = ?";
		
		// SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, id);
			
			// SQL文を表示
			System.out.println(pstmt.toString());

			// SQL実行と検索結果の取得
			ResultSet rs = pstmt.executeQuery();
			
			// ItemModelの初期化
			Item item = new Item();
			
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getInt("price"));
			item.setImagePath(rs.getString("imagePath"));

			return item;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("会員情報の登録に失敗しました。");

			return null;
		}
	}
	
	public List<Item> selectAll() {
		String sql = "select * from items;";
		
		// List<Item>のオブジェクト化
		List<Item> itemList = new ArrayList<>();
		
		// SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			// SQL文を表示
			System.out.println(pstmt.toString());
			
			// SQL実行と検索結果の取得
			ResultSet rs = pstmt.executeQuery();
			
			// itemテーブルのレコード分以下の処理を行う。
			while (rs.next()) {
				
				// itemの初期化
				Item item = new Item();
	
				//取得した値をItemModelにセット
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setImagePath(rs.getString("imagePath"));
				
				// itemをitemListに格納
				itemList.add(item);		
			}
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
			return null;
		}
		return itemList;
	}
}