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
import com.modal.response.GetContactDetailResponse;
import com.modal.response.Response;
import com.services.ContactServiceImpl;

@WebServlet("/deleteContactDetail")
public class DeleteContactDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteContactDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ContactServiceImpl contactService = new ContactServiceImpl();
		
		PrintWriter out = response.getWriter();

		Helper helper = new Helper();

		StringBuilder contents = new StringBuilder();
		
		StringBuilder styles = new StringBuilder();
		
		StringBuilder error = new StringBuilder();
		
		 String id = request.getParameter("contactId");
		 try {

		Response<GetContactDetailResponse> allContactRes = contactService.getContactDetailById(id);

		GetContactDetailResponse responseBody = allContactRes.getResponseBody();

		ContactsModal contactDetail = responseBody.getContactDetail();
		
		
		contents.append("<div class=\"card border-dark mb-3\">\n"
				+ "  <h5 class=\"card-header\">Contact Details</h5>\n"
				+ "  <div class=\"card-body\">\n"
				+ "    <h6 class=\"card-title\">Name: "+contactDetail.getName()+"</h5>\n"
						+ "    <h6 class=\"card-title\">Phone Number: "+contactDetail.getPhoneNumber()+"</h5>\n"
								+ "    <h6 class=\"card-title\">Group Name: "+contactDetail.getGroupName()+"</h5>\n"
										+ "<div class=\"alert alert-warning\" role=\"alert\">\n"
										+ "Are you sure want to delete the contact"
										+ "</div>"
				
				+ "    <a href=\"#\" class=\"btn btn-danger\" onClick=\"onDelete("+contactDetail.getId()+")\">Delete</a>\n"
				+ "    <a href=\"#\" class=\"btn btn-link\" onClick=\"onGoBack()\">Go Back</a>\n"
				+ "  </div>\n"
				+ "</div>");
		
		
		
		contents.append("<script>\n"
				+ "        function onGoBack(id){\n"
				+ "            window.history.go(-1);"
				+ "        };\n"
				+ "        function onDelete(id){\n"
				+ "            window.location.href = 'http://localhost:8085/contactmanagement/deleteContactConfirm/'+id;"
				+ "        };\n"
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
