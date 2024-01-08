package com.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helper.CustomException;
import com.helper.Helper;
import com.modal.ContactsModal;
import com.modal.request.EditContactRequest;
import com.modal.response.EditContactResponse;
import com.modal.response.Response;
import com.services.ContactServiceImpl;

@WebServlet("/editContactConfirm")
public class EditContactConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditContactConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ContactServiceImpl contactService = new ContactServiceImpl();
		
		EditContactRequest editContactRequest = new EditContactRequest();
		
		System.out.println("Request Groupid..."+request.getParameter("groupId"));

		editContactRequest.setContactInfo(new ContactsModal(request.getParameter("id"),request.getParameter("name"),request.getParameter("phoneNumber"),
				"",request.getParameter("groupId")));	
		
		

		PrintWriter out = response.getWriter();

		Helper helper = new Helper();

		StringBuilder contents = new StringBuilder();
		
		StringBuilder styles = new StringBuilder();

		StringBuilder error = new StringBuilder();

		try {
			

		Response<EditContactResponse> allContactRes = contactService.updateContact(editContactRequest);

		EditContactResponse responseBody = allContactRes.getResponseBody();

		String message = responseBody.getStatus();

		contents.append("<div class=\"card border-dark mb-3\">\n"
				+ "  <h5 class=\"card-header\">Confirmation screen</h5>\n" + "  <div class=\"card-body\">\n"
//		+ "    <h6 class=\"card-title\">"+contactDetail.getName()+"Contact Deleted Successfully</h5>\n"

				+ "<div class=\"alert alert-success\" role=\"alert\">\n" + message + "</div>"
				+ "    <a href=\"#\" class=\"btn btn-primary\" onClick=\"goBackToList()\">Go back to List</a>\n"
				+ "  </div>\n" + "</div>");

		contents.append("<script>\n" + "        function goBackToList(id){\n"
				+ "           window.location.href = 'http://localhost:8085/contactmanagement';" + "        };\n"
				+ "    </script>");
		
		
		} catch (IOException | CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			error = helper.exceptionHandler(e);
		}

		contents = helper.pageBuilder(contents, styles, error);

		out.println(contents);

		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
