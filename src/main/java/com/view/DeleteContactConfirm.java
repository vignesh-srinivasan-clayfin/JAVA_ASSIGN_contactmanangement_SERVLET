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
import com.modal.response.DeleteContactResponse;
import com.modal.response.Response;
import com.services.ContactServiceImpl;

@WebServlet("/deleteContactConfirm/*")
public class DeleteContactConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteContactConfirm() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactServiceImpl contactService = new ContactServiceImpl();

		PrintWriter out = response.getWriter();

		Helper helper = new Helper();

		StringBuilder contents = new StringBuilder();
		
		StringBuilder styles = new StringBuilder();

		StringBuilder error = new StringBuilder();

		String pathInfo = request.getPathInfo();

		String id = new String();

		if (pathInfo != null) {
			id = pathInfo.substring(pathInfo.lastIndexOf('/') + 1);
		} else {
			response.getWriter().write("No dynamic value provided");
		}

		try {

			Response<DeleteContactResponse> allContactRes = contactService.deleteContactById(id);

			DeleteContactResponse responseBody = allContactRes.getResponseBody();

			String message = responseBody.getStatus();

			contents.append("<div class=\"card border-dark mb-3\">\n" + "  <h5 class=\"card-header\">Confirmation screen</h5>\n"
					+ "  <div class=\"card-body\">\n"
//				+ "    <h6 class=\"card-title\">"+contactDetail.getName()+"Contact Deleted Successfully</h5>\n"
					
					+ "<div class=\"alert alert-success\" role=\"alert\">\n"
					+ message
					+ "</div>"
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
