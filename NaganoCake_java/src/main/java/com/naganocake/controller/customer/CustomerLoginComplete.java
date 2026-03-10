package com.naganocake.controller.customer;

import java.io.IOException;

import com.naganocake.dao.MemberDao;
import com.naganocake.dao.MemberDaoImpl;
import com.naganocake.model.Member;
import com.naganocake.util.MemberUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerLoginComplete
 */
@WebServlet("/CustomerLoginComplete")
public class CustomerLoginComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// member情報をJSPから取得する
		Member loginMember = MemberUtil.createMemberFromRequest(request);

		// MemberDaoImplの初期化
		MemberDao memberSelect = new MemberDaoImpl();

		// EmailとPasswordをwhere句にユーザ情報を検索
		Member member = memberSelect.selectMember(loginMember.getEmail(), loginMember.getPassword());

		// 会員情報がNULLでない場合
		if (member != null) {
			System.out.println("ログイン成功: " + member.getEmail());

			// セッションにユーザー情報を保存
			HttpSession session = request.getSession();
			session.setAttribute("memberId", member.getId());
			session.setAttribute("memberFirstName", member.getFirstName());
			session.setAttribute("memberLastName", member.getLastName());

			// 商品一覧画面へリダイレクト
			System.out.println("商品一覧画面へリダイレクト");
			response.sendRedirect(request.getContextPath() + "/CustomerItemListForm"); 
			
		} else {
			System.out.println("ログイン失敗: メールまたはパスワードが不正");

			// ログイン失敗時の処理
			request.setAttribute("errorMessage", "メールアドレスまたはパスワードが正しくありません。");
			request.getRequestDispatcher("/WEB-INF/views/customer/loginForm.jsp").forward(request, response);
		}
	}
}