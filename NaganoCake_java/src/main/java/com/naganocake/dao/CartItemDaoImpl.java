package com.naganocake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.naganocake.model.CartItem;
import com.naganocake.util.ConnectionBase;

public class CartItemDaoImpl implements CartItemDao {

	// カート情報を登録
	@Override
	public boolean insert(CartItem cartItem) {
		String sql = "INSERT INTO items (id, member_id, item_id, amount, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, cartItem.getId());
			pstmt.setInt(2, cartItem.getMemberId());
			pstmt.setInt(3, cartItem.getItemId());
			pstmt.setInt(4, cartItem.getAmount());
			pstmt.setTimestamp(5, Timestamp.valueOf(cartItem.getCreatedAt()));
			pstmt.setTimestamp(6, Timestamp.valueOf(cartItem.getUpdatedAt()));

			// SQL文を表示
			System.out.println(pstmt.toString());

			// SQL実行
			int rs = pstmt.executeUpdate();

			if (rs > 0) {
				System.out.println("商品情報の登録に成功しました。");
				return true;
			} else {
				System.out.println("商品情報の登録に失敗しました。");
				throw new SQLException();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("商品情報の登録に失敗しました。");
			return false;
		}
	}

	// カート情報の取得
	@Override
	public List<CartItem> selectById(int id) {

		// CartItem を格納するリスト（ArrayListで実装）
		List<CartItem> cartList = new ArrayList<>();

		// カートモデルの初期化
		CartItem cartItem = new CartItem();

		// SQL文をsql変数に代入
		String sql = "SELECT * FROM cart_items WHERE id = ?;";

		// 件数カウンタ
		int count = 0;
		
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			// プレースホルダーでidを挿入
			pstmt.setInt(1, id);

			// SQL文の確認
			System.out.println(pstmt);

			// SQL実行と検索結果の取得
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// 取得した値をを商品カートモデルにセット
				cartItem.setId(rs.getInt("id"));
				cartItem.setMemberId(rs.getInt("member_id"));
				cartItem.setItemId(rs.getInt("item_id"));
				cartItem.setAmount(rs.getInt("amount"));
				cartItem.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				cartItem.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

				// itemをitemListに格納
				cartList.add(cartItem);

				count++;
			}
			
			System.out.println(count + "件取得しました");
			
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return cartList;
	}
}
