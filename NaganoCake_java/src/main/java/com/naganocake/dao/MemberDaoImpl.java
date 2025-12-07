package com.naganocake.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.naganocake.model.Member;
import com.naganocake.util.ConnectionBase;

public class MemberDaoImpl implements MemberDao {
	
	// 会員登録
	public boolean insertMember(Member member) {
		// 会員を登録するSQL
		String sql = 
			"INSERT INTO members (email, password, last_name, first_name, " +
			"last_name_kana, first_name_kana, postal_code, address, phone_number) " + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, member.getEmail());
	        pstmt.setString(2, member.getPassword());
	        pstmt.setString(3, member.getLastName());
	        pstmt.setString(4, member.getFirstName());
	        pstmt.setString(5, member.getLastNameKana());
	        pstmt.setString(6, member.getFirstNameKana());
	        pstmt.setString(7, member.getPostalCode());
	        pstmt.setString(8, member.getAddress());
	        pstmt.setString(9, member.getPhoneNumber());
			// SQL文を表示
			System.out.println(pstmt.toString());
			
			// SQL実行と更新行の取得
			int affectedRows = pstmt.executeUpdate();
			
			System.out.println("更新行：" + affectedRows);
			
			// 更新行が0行ではない場合、trueを返す
			return affectedRows != 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("会員情報の登録に失敗しました。");
			return false;
		}
	}
	
	// 一人分の会員情報取得
	// 社員IDから1社員を取得
	public Member selectMember(String email, String password) {

		// HIT件数格納用
		int count = 0;
		
		//Memberの初期化
		Member member = new Member();

		// idで絞った特定の社員を検索するSQL文を格納
		String sql = "select id, last_name, first_name, email, password from members where email =? and password =?";

		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			// where句のidを指定
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			// SQL文をコンソールへ表示
			System.out.println(pstmt.toString());
			
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 取得した値をを社員Beanにセット
				member.setId(rs.getInt("id"));
				member.setLastName(rs.getString("last_name"));
				member.setFirstName(rs.getString("first_name"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				
				// HITするごとに 1 プラス
				// count = count + 1;の省略型
				count++;
				// count += 1;でも良い。
			}
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("会員情報の検索に失敗しました。");
		}
		
		if(count == 1) {
			System.out.println("会員情報がHITしました。");
			return member;
			
		} else if(count == 0) {
			System.out.println("会員情報がHITしませんでした。");
			return null;
			
		} else {
			System.out.println("会員情報が複数HITしました。");
			return null;
		}
	}
}