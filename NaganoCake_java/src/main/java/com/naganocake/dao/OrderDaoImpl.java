package com.naganocake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.naganocake.util.ConnectionBase;

public class OrderDaoImpl implements OrderDao {

	@Override
	public boolean insert(int newAmount, int memberId, int itemId) {
		String sql = "INSERT INTO orders ("
				+ "    member_id,"
				+ "    shipping_fee,"
				+ "    payment_method,"
				+ "    postal_code,"
				+ "    recipient_name,"
				+ "    address,"
				+ "    order_status,"
				+ "    total_amount,"
				+ "    created_at,"
				+ "    updated_at"
				+ ") VALUES ("
				+ "    ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW()"
				+ ");";
		
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			// TODO テスト用
			// 会員ID
			pstmt.setInt(1, 1);
			// 送料
			pstmt.setInt(2, 800);
			// 支払い方法
			pstmt.setInt(3, 1);
			// 郵便番号
			pstmt.setString(4, "123-4567");
			// 氏名
			pstmt.setString(5, "山田 太郎");
			// 住所
			pstmt.setString(6, "埼玉県上尾市○○1-2-3");
			// 注文ステータス
			pstmt.setInt(7, 0);
			// 合計金額
			pstmt.setInt(8, 5400);
			
			// SQL文を表示
			System.out.println(pstmt.toString());
			
			// SQL実行
			int rs = pstmt.executeUpdate();

			if (rs > 0) {
				System.out.println("注文情報の登録に成功しました。");
				return true;
			} else {
				System.out.println("注文情報の登録に失敗しました。");
				throw new SQLException();
			}
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return false;

	}

}
