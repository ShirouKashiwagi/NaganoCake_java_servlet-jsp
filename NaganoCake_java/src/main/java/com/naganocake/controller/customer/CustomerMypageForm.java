package com.naganocake.controller.customer;

import java.io.IOException;

import com.naganocake.model.Member;

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
		
		Member loginId = (Member)session.getAttribute("memberId");

		if(loginId != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/mypage.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("CustomerLoginForm");
		}
	}
}