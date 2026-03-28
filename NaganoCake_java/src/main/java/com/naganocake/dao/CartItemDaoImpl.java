package com.naganocake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.naganocake.model.CartItem;
import com.naganocake.util.ConnectionBase;

public class CartItemDaoImpl implements CartItemDao {

  // カート情報を登録
  @Override
	public CartItem insert(CartItem cartItem) {
		String sql = "INSERT INTO items (id, member_id, item_id, amount, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, cartItem.getId());
			pstmt.setInt(2, cartItem.getMemberId());
			pstmt.setInt(3, cartItem.getItemId());
			pstmt.setInt(4, cartItem.getAmount());
			pstmt.setTimestamp(5, cartItem.getCreatedAt());
			pstmt.setTimestamp(6, cartItem.getUpdatedAt());

					// SQL文を表示
			System.out.println(pstmt.toString());

			// SQL実行
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("商品情報の登録に成功しました。");
				return item;
			} else {
				System.out.println("商品情報の登録に失敗しました。");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("商品情報の登録に失敗しました。");
			return null;
		}
	}
}
