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
 * Servlet implementation class CustomerLoginComplete
 */
@WebServlet("/CustomerLoginComplete")
public class CustomerLoginComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// member情報をJSPから取得する
		Member loginMember = MemberUtil.createMemberFromRequest(request);
		
		// MemberDaoImplの初期化
		MemberDao memberSelect = new MemberDaoImpl();
		
		// 
		Member member = memberSelect.selectMember(loginMember.getEmail(), loginMember.getPassword());
		
		// 会員情報がNULLでない場合
		if(member != null) {
			System.out.println("画面遷移処理");
		} else {
			System.out.println("画面遷移失敗");
		}
	}
}