package com.naganocake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naganocake.entity.CartItemEntity;
import com.naganocake.model.CartItem;
import com.naganocake.util.ConnectionBase;

public class CartItemDaoImpl implements CartItemDao {

	// カート情報を登録
	@Override
	public boolean insert(CartItem cartItem) {
		
		String sql = "INSERT INTO cart_items (member_id, item_id, amount) VALUES (?, ?, ?)";

		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, cartItem.getMemberId());
			pstmt.setInt(2, cartItem.getItemId());
			pstmt.setInt(3, cartItem.getAmount());

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
	public List<CartItemEntity> selectByMemberId(int memberId) {

		// CartItem を格納するリスト（ArrayListで実装）
		List<CartItemEntity> cartList = new ArrayList<>();
		// カートエンティティの初期化
		CartItemEntity cartItemEntity = new CartItemEntity();
		// 件数カウンタ
		int count = 0;
		
		// SQL文をsql変数に代入
		String sql = "SELECT * FROM cart_items WHERE member_id = ?;";
		
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			// プレースホルダーでidを挿入
			pstmt.setInt(1, memberId);
					
			// SQL文の確認
			System.out.println(pstmt);

			// SQL実行と検索結果の取得
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// 取得した値をを商品カートモデルにセット
				cartItemEntity.setId(rs.getInt("id"));
				cartItemEntity.setMemberId(rs.getInt("member_id"));
				cartItemEntity.setItemId(rs.getInt("item_id"));
				cartItemEntity.setAmount(rs.getInt("amount"));
				cartItemEntity.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				cartItemEntity.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

				// itemをitemListに格納
				cartList.add(cartItemEntity);

				count++;
			}
			
			System.out.println(count + "件取得しました");
			
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return cartList;
	}
	
	
	
	// TODO カート商品の存在有無と特定の商品の個数を取得する処理を実装する。
	public Integer countAmount(int memberId, int itemId) {
		
		String sql = "SELECT amount FROM cart_items WHERE member_id = ? AND item_id = ?;";
		
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			// プレースホルダーでidを挿入
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, itemId);
			
			ResultSet rs = pstmt.executeQuery();
			
			// レコードなしの場合、nullを返却
			if (!rs.next()) {
			    return null;
			}
			
			// 上記以外の場合、商品個数を取得
			Integer amount = rs.getInt("amount");
			
			// 2 行目が存在したら異常
			if (rs.next()) {
			    throw new IllegalStateException("cart_items に重複レコードが存在します (memberId=" + memberId + ", itemId=" + itemId + ")");
			}
			
			return amount;
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void update(CartItem cartItem) {
		
		String sql = "UPDATE cart_items SET amount = ? WHERE member_id = ? AND item_id = ?;";
		
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			// SET情報
			pstmt.setInt(1, cartItem.getAmount());
			
			// 条件情報
			pstmt.setInt(2, cartItem.getMemberId());
			pstmt.setInt(3, cartItem.getItemId());
			
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount != 1) {
				throw new IllegalStateException("cart_items が更新できませんでした。 (更新件数：" + updateCount + ")");
			} else {
				return;
			}
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return;
		}
	}
}
