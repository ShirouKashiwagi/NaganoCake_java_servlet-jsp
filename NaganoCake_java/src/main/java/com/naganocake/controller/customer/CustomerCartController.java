package com.naganocake.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.naganocake.dao.CartItemDao;
import com.naganocake.dao.CartItemDaoImpl;
import com.naganocake.model.CartItem;

/**
 * Servlet implementation class CustomerCartController
 */
@WebServlet("/CustomerCartController")
public class CustomerCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// JSPからGet送信されたactionを取得
		String action = request.getParameter("action");

		if(action == null || action.equals("list")) {
			listItem(request, response);
			return;
		}
		
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// JSPからGet送信されたactionを取得
		String action = request.getParameter("action");
		
		// 各機能への振分処理
		switch(action){
			case "add":
				addItem(request, response);
				return;
			case "delete":
				deleteItem(request, response);
				return;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				break;
		}
	}
	
	private void listItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ListインターフェースにArrayListを代入
		List<CartItem> cartList = new ArrayList<>();
		
		HttpSession session = request.getSession(false);
		
		// セッションから会員IDを取得
		int memberId = (int)session.getAttribute("memberId");
		
		// TODO CartItemDaoのユーザIDに紐づいたカート情報を全件取得
		CartItemDao cartItem = new CartItemDaoImpl();
		
		// セッションから取得した会員IDでカート情報を取得
		cartList = cartItem.selectById(memberId);
		
		// DBから取得したカート情報をリクエストに詰める
		request.setAttribute("cartList", cartList);
		
		// リクエスト情報をカート画面に送信及び画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/cartList.jsp");
		dispatcher.forward(request, response);
		
	}
	
	// 商品追加処理
	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("カートに商品に追加する処理を開始します。");
	}
	
	// カート商品削除処理
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO 自動生成されたメソッド・スタブ
		
		System.out.println("カートの商品を削除します。");
	}
}
