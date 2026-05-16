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
import com.naganocake.dao.ItemDao;
import com.naganocake.dao.ItemDaoImpl;
import com.naganocake.entity.CartItemEntity;
import com.naganocake.entity.ItemEntity;
import com.naganocake.model.CartItem;
import com.naganocake.util.SystemConstants;

/**
 * Servlet implementation class CustomerCartController
 */
@WebServlet("/customer/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// JSPからGet送信されたactionを取得
		String action = request.getParameter("action");

		if (action == null || action.equals("index")) {
			indexItem(request, response);
			return;
		}

		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// JSPからGet送信されたactionを取得
		String action = request.getParameter("action");

		// 各機能への振分処理
		switch (action) {
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

	
	// list：カート画面表示
	private void indexItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ListインターフェースにArrayListを代入
		List<CartItem> cartList = new ArrayList<>();
		List<CartItemEntity> cartItemEntity = new ArrayList<>();
		
		
		// セッションから会員IDを取得
		HttpSession session = request.getSession(false);
		int memberId = (int) session.getAttribute("memberId");
		
		
		// セッションから取得した会員IDでカート情報を取得
		CartItemDao cartItemDao = new CartItemDaoImpl();
		cartItemEntity = cartItemDao.selectByMemberId(memberId);
		
		
		for (CartItemEntity entity : cartItemEntity) {

			// CartItemEntityからitemテーブルの情報を取得する
			ItemDao itemSelect = new ItemDaoImpl();
			ItemEntity itemSelectResult = itemSelect.selectById(entity.getItemId());
			
			// cartItemモデルにセットする
			CartItem item = new CartItem();
			item.setItemId(entity.getItemId());
			item.setAmount(entity.getAmount());
			item.setCreatedAt(entity.getCreatedAt());
			item.setName(itemSelectResult.getName());
			item.setPrice(itemSelectResult.getPrice());
			cartList.add(item);
		}
		
		// DBから取得したカート情報をセッションに詰める
		session.setAttribute("cartList", cartList);

		// リクエスト情報をカート画面に送信及び画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/cartList.jsp");
		dispatcher.forward(request, response);
	}

	
	// add：商品追加
	private void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("カートに商品に追加する処理を開始します。");

		HttpSession session = request.getSession(false);

		// セッションから会員IDを取得
		int memberId = (int) session.getAttribute(SystemConstants.SESSION_MEMBER_ID);
		
		// 商品詳細画面からPOST送信された情報を取得
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		// カートDaoの変数に実装クラスのインスタンスを代入
		CartItemDao cartItemDao = new CartItemDaoImpl();
		
		// カートテーブルから追加する商品の個数を取得
		Integer countAmount = cartItemDao.countAmount(memberId, itemId);
		
		// 追加する商品の個数が null の場合、商品を追加する。
		if(countAmount == null) {
			// 商品追加処理
	        cartItemDao.insert(amount, memberId, itemId);
		} else {
			// 追加する商品の個数が 1 の場合、商品数を更新する。
			// 画面から取得した個数に、DBから取得した個数を追加する。
			int newAmount = amount + countAmount;
			
			// 個数を更新
			cartItemDao.update(newAmount, memberId, itemId);
		}
		
		// カート画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/customer/cart?action=index");
	}

	
	// カート商品削除処理
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO 自動生成されたメソッド・スタブ

		System.out.println("カートの商品を削除します。");
	}
}
