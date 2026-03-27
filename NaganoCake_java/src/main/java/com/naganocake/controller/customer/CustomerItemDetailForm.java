package com.naganocake.controller.customer;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.naganocake.dao.ItemDao;
import com.naganocake.dao.ItemDaoImpl;
import com.naganocake.model.Item;

/**
 * Servlet implementation class CustomerItemDetailForm
 */
@WebServlet("/CustomerItemDetailForm")
public class CustomerItemDetailForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// JSPからidを取得
		int id = Integer.parseInt(request.getParameter("id"));

		// MemberDaoの変数に、実装クラスのインスタンスを代入
		ItemDao itemById = new ItemDaoImpl();

		// 取得したIDから商品情報を取得
		Item item = itemById.selectById(id);

		// リクエストスコープに商品情報を詰める
		request.setAttribute("item", item);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/itemDetail.jsp");
		dispatcher.forward(request, response);
	}
}