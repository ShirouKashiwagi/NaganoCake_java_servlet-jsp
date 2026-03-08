package com.naganocake.controller.customer;

import java.io.IOException;
import java.util.List;

import com.naganocake.dao.ItemDao;
import com.naganocake.dao.ItemDaoImpl;
import com.naganocake.model.Item;
import com.naganocake.model.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerItemListForm
 */
@WebServlet("/CustomerItemListForm")
public class CustomerItemListForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerItemListForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッション情報の取得
		// session情報がなければnullを返す
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			//　セッションから会員情報を取得
			Member loginMember = (Member)session.getAttribute("loginMember");
			
			// デバッグ用
			System.out.println(session);
			
			// 会員情報をJSPへセット
			request.setAttribute("loginMember", loginMember);
		}
		
		// 商品一覧の初期化
//		Item item = new Item(); Daoの処理中に初期化を行っているため不要。
		
		// ItemDaoImplの初期化
		ItemDao selectItem = new ItemDaoImpl();
		
		// 商品一覧の取得
		List<Item> itemList = selectItem.selectAll();
		
		// リスエストに商品一覧を詰める
		request.setAttribute("items", itemList);
		
		// 商品一覧画面を表示
		request.getRequestDispatcher("/WEB-INF/views/customer/itemList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
