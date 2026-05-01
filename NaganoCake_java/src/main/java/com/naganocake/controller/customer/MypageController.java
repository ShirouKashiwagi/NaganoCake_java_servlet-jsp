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
import com.naganocake.util.MemberUtil;
import com.naganocake.util.SystemConstants;

/**
 * Servlet implementation class CustomerLoginForm
 */
@WebServlet("/customer/mypage")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションオブジェクトの生成or取得
		HttpSession session = request.getSession(false);

		int memberId = (int) session.getAttribute(SystemConstants.SESSION_MEMBER_ID);

		// // MemberDaoImplの呼び出し
		MemberDao memberDao = new MemberDaoImpl();

		// IDをもとに会員情報を取得
		Member member = memberDao.selectById(memberId);

		// 取得した会員情報をリクエストスコープに詰める
		request.setAttribute("member", member);
		
		// ユーザ名はセッションスコープに詰める
		session.setAttribute("memberFirstName", member.getFirstName());
		session.setAttribute("memberLastName", member.getLastName());

		// セッションからログインIDが取得できた場合
		if (memberId != 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/myPage.jsp");
			dispatcher.forward(request, response);
		} else {
			// 上記以外の場合
			response.sendRedirect(request.getContextPath() + "CustomerLoginForm");
		}
	}
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 会員情報をJSPから取得する
		Member member = MemberUtil.createMemberFromRequest(request);
		
	  	// セッションオブジェクトの生成or取得
	    HttpSession session = request.getSession(false);
	    
	    // セッションから会員情報を取得
	    int sessionId = (int)session.getAttribute(SystemConstants.SESSION_MEMBER_ID);
		
	    // member変数にセッションで取得した会員IDをセット
	    member.setId(sessionId);
	    
		//MemberDaoの変数に、実装クラスのインスタンスを代入
		MemberDao memberUpdate = new MemberDaoImpl();
		
		// DBの更新処理を実施
		memberUpdate.update(member);
		
		// TODO エラー画面処理
		//if()
		
		// マイページ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/customer/mypage");
	}
}