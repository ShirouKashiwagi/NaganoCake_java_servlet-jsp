package com.naganocake.controller.customer;

import java.io.IOException;

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

/**
 * Servlet implementation class CustomerLoginForm
 */
@WebServlet("/CustomerMypageComplete")
public class CustomerMypageComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 会員情報をJSPから取得する
		Member member = MemberUtil.createMemberFromRequest(request);
		
	  	// セッションオブジェクトの生成or取得
	    HttpSession session = request.getSession(false);
	    
	    // セッションから会員情報を取得
	    int sessionId = (int)session.getAttribute("memberId");
		
	    // member変数にセッションで取得した会員IDをセット
	    member.setId(sessionId);
	    
		//MemberDaoの変数に、実装クラスのインスタンスを代入
		MemberDao memberUpdate = new MemberDaoImpl();
		
		// DBの更新処理を実施
		memberUpdate.updateById(member);
		
		// マイページ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/CustomerMypageForm");
	}
}