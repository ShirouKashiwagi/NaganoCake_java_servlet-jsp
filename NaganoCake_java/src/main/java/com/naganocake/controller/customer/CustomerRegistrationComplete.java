package com.naganocake.controller.customer;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.naganocake.dao.MemberDao;
import com.naganocake.dao.MemberDaoImpl;
import com.naganocake.model.Member;
import com.naganocake.util.MemberUtil;

/**
 * Servlet implementation class CustomerRegistrationComplete
 */
@WebServlet("/CustomerRegistrationComplete")
public class CustomerRegistrationComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 会員Emptyの取得
		Member member = MemberUtil.createMemberFromRequest(request);
		
		// 会員登録の初期化
		MemberDao memberInset = new MemberDaoImpl();
		
		// 会員登録処理
		boolean isSuccess = memberInset.insertMember(member);
		
		if(isSuccess) {
			//response.sendRedirect(遷移先画面);
			System.out.println("画面遷移処理");
		} else {
			System.out.println("画面遷移失敗");
		}
		
//		備忘：以下は実装DAOを直指定しているため、間違っています。
//		MemberDaoImpl insert_db = new MemberDaoImpl();
//		
//		insert_db.insertMember(member);
	}
}