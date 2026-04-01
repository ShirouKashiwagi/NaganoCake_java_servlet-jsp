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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ListインターフェースにArrayListを代入
		List<CartItem> cartList = new ArrayList<>();
		
		// TODO CartItemDaoのユーザIDに紐づいたカート情報を全件取得
		CartItemDao cartItem = new CartItemDaoImpl();
		
		// cartList = cartItem.カート情報取得実装クラス
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/cartList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
