package com.naganocake.controller.customer;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.naganocake.dao.MemberDao;
import com.naganocake.dao.MemberDaoImpl;
import com.naganocake.model.Member;

/**
 * Servlet implementation class CustomerLoginForm
 */
@WebServlet("/CustomerMypageForm")
public class CustomerMypageForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションオブジェクトの生成or取得
		HttpSession session = request.getSession(false);

		int loginId = (int) session.getAttribute("memberId");

		// // MemberDaoImplの呼び出し
		MemberDao memberDao = new MemberDaoImpl();

		// IDをもとに会員情報を取得
		Member member = memberDao.selectById(loginId);

		// 取得した会員情報をリクエストスコープに保存
		request.setAttribute("member", member);

		// セッションからログインIDが取得できた場合
		if (loginId != 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/myPage.jsp");
			dispatcher.forward(request, response);
		} else {
			// 上記以外の場合
			response.sendRedirect(request.getContextPath() + "CustomerLoginForm");
		}
	}
}