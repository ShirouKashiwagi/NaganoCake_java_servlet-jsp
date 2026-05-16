package com.naganocake.controller.customer;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.naganocake.dao.MemberDao;
import com.naganocake.dao.MemberDaoImpl;
import com.naganocake.model.CartItem;
import com.naganocake.model.Member;
import com.naganocake.util.SystemConstants;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/customer/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッションから会員ID、カート情報を取得
		HttpSession session = request.getSession(false);
		int memberId = (int) session.getAttribute(SystemConstants.SESSION_MEMBER_ID);
		
		List<CartItem> cartList = (List<CartItem>) session.getAttribute(SystemConstants.SESSION_CART);
		
		
		// TODO 改善の余地あり：コントローラではなくロジックを Service に移す
		// // MemberDaoImplの呼び出し
		MemberDao memberDao = new MemberDaoImpl();

		// IDをもとに会員情報を取得
		Member member = memberDao.selectById(memberId);

		// セッションから取得した会員情報、定数から送料をリクエストスコープに詰める
		request.setAttribute("member", member);
		request.setAttribute("shippingFee", SystemConstants.SHIPPING_FEE);
		
		// セッションにカート情報を詰める
		session.setAttribute("cartList", cartList);
		
		// セッションからログインIDが取得できた場合
		if (memberId != 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/orderConfirm.jsp");
			dispatcher.forward(request, response);
		} else {
			// 上記以外の場合
			response.sendRedirect(request.getContextPath() + "CustomerLoginForm");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}