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

	// 商品IDから商品情報を取得
	public Item selectById(int id) {

		String sql = "select * from items where id = ?;";

		// SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, id);

			// SQL文を表示
			System.out.println(pstmt.toString());

			// SQL実行と検索結果の取得
			ResultSet rs = pstmt.executeQuery();

			// 取得した値をItemModelにセット
			Item item = new Item();

			if (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setImagePath(rs.getString("image_path"));
				item.setIntroduction(rs.getString("introduction"));
				item.setGenreId(rs.getInt("genre_id"));
				item.setActive(rs.getBoolean("is_active"));

				return item;

			} else {

				// TODO 商品情報が取得できない場合のエラー処理
				System.out.println("ERROR : 2件以上のデータを取得しています。");
				throw new SQLException();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("商品情報の取得に失敗しました。");

			return null;
		}
	}

	@Override
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

				// 取得した値をItemModelにセット
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setImagePath(rs.getString("image_path"));
				item.setIntroduction(rs.getString("introduction"));
				item.setGenreId(rs.getInt("genre_id"));

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