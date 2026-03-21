package com.naganocake.controller.customer;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerLoginForm
 */
@WebServlet("/CustomerMypageForm")
public class CustomerMypageForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

  @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		int loginId = (int)session.getAttribute("memberId");

		if(loginId != 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/myPage.jsp");
			dispatcher.forward(request, response);
		}

		

		// TODO DaoImplのselectByIdを呼びだして、会員情報を取得

		else {
			response.sendRedirect("CustomerLoginForm");
		}
	}
}