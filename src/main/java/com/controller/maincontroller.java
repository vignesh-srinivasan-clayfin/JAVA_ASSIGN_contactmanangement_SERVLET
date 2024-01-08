package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class maincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;

	public maincontroller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		System.out.println("Action Servlet..." + action);

		switch (action) {
		
		case "/deleteContactDetail":
			dispatcher = request.getRequestDispatcher("/deleteContactDetail");
			dispatcher.forward(request, response);
		
		case "/deleteContactConfirm":
			dispatcher = request.getRequestDispatcher("/deleteContactConfirm");
			dispatcher.forward(request, response);
			
			
		case "/addContactDetail":
			dispatcher = request.getRequestDispatcher("/addContactDetail");
			dispatcher.forward(request, response);

		case "/addContactConfirm":
			dispatcher = request.getRequestDispatcher("/addContactConfirm");
			dispatcher.forward(request, response);
			
			
			
		case "/editContactDetail":
			dispatcher = request.getRequestDispatcher("/editContactDetail");
			dispatcher.forward(request, response);
			
		case "editContactConfirm":
			dispatcher = request.getRequestDispatcher("/editContactConfirm");
			dispatcher.forward(request, response);

		

		

		
		default:
			dispatcher = request.getRequestDispatcher("/homepage.do");
			dispatcher.forward(request, response);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
