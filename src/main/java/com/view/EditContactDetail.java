package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helper.CustomException;
import com.helper.Helper;
import com.modal.ContactsModal;
import com.modal.GroupModal;
import com.modal.response.GetAllGroupsResponse;
import com.modal.response.GetContactDetailResponse;
import com.modal.response.Response;
import com.services.ContactServiceImpl;

@WebServlet("/editContactDetail")
public class EditContactDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditContactDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactServiceImpl contactService = new ContactServiceImpl();

		PrintWriter out = response.getWriter();

		Helper helper = new Helper();

		StringBuilder contents = new StringBuilder();

		StringBuilder styles = new StringBuilder();

		StringBuilder error = new StringBuilder();

//		String pathInfo = request.getPathInfo();

		String id = request.getParameter("contactId");

//		 if (pathInfo != null) {
//			 id = pathInfo.substring(pathInfo.lastIndexOf('/') + 1);
//	        } else {
//	            response.getWriter().write("No dynamic value provided");
//	        }

		try {

			Response<GetContactDetailResponse> allContactRes = contactService.getContactDetailById(id);

			Response<GetAllGroupsResponse> allGroupsRes = contactService.getAllGroups();

			List<GroupModal> groupList = allGroupsRes.getResponseBody().getGroupList();

			GetContactDetailResponse responseBody = allContactRes.getResponseBody();

			ContactsModal contactDetail = responseBody.getContactDetail();

			StringBuilder groupOptions = new StringBuilder();

			for (int i = 0; i < groupList.size(); i++) {
				groupOptions.append(
						" <option value=" + groupList.get(i).getId() + ">" + groupList.get(i).getName() + "</option>");

			}

//		contents.append("<div class=\"card\">\n"
//				+ "  <h5 class=\"card-header\">Contact Details</h5>\n"
//				+ "  <div class=\"card-body\">\n"
//				+ "    <h6 class=\"card-title\">Name: "+contactDetail.getName()+"</h5>\n"
//						+ "    <h6 class=\"card-title\">Phone Number: "+contactDetail.getPhoneNumber()+"</h5>\n"
//								+ "    <h6 class=\"card-title\">Group Name: "+contactDetail.getGroupName()+"</h5>\n"
//				+ "    <p class=\"card-text\">Are you sure want to delete the contact</p>\n"
//				+ "    <a href=\"#\" class=\"btn btn-primary\" onClick=\"onDelete("+contactDetail.getId()+")\">Delete</a>\n"
//				+ "    <a href=\"#\" class=\"btn btn-secondary\" onClick=\"onGoBack()\">Go Back</a>\n"
//				+ "  </div>\n"
//				+ "</div>");

			contents.append("<div class=\"card border-dark mb-3\">\n" + "  <h5 class=\"card-header\">Edit Contact Details</h5>\n"
					+ "  <div class=\"card-body\">\n"

					+ "<form action=\"editContactConfirm\" method=\"post\" id=\"editForm\">\n"

					+ " <div class=\"form-group mb-4\">\n" + "    <label for=\"name\">Name</label>\n"
					+ "    <input type=\"text\" name=\"name\" class=\"form-control\" value=" + contactDetail.getName()
					+ " id=\"name\" placeholder=\"Enter the name\">\n" + "</div>\n"

					+ " <div class=\"form-group mb-4\">\n" + "    <label for=\"name\">Phone Number</label>\n"
					+ "    <input type=\"text\" name=\"phoneNumber\" class=\"form-control\" value="
					+ contactDetail.getPhoneNumber() + " id=\"name\" placeholder=\"Enter the phonenumber\">\n"
					+ "  </div>\n"

					+ "  <div class=\"form-group mb-4\">\n"

					+ "    <label for=\"exampleFormControlSelect1\">Select Group</label>\n"
					+ "    <select name=\"groupId\" class=\"form-control\" id=\"exampleFormControlSelect1\" form=\"editForm\">\n"
					+ groupOptions + "    </select>\n" + "  </div>\n"

					+ " <input type=\"hidden\" id=\"id\" name=\"id\" value=" + contactDetail.getId() + ">"

					+ " <button class=\"btn btn-primary\" type=\"submit\">Submit</button>"

					+ "</form>" + "  </div>\n" + "</div>");

			contents.append("<script>\n" + "        function onGoBack(id){\n" + "            window.history.go(-1);"
					+ "        };\n" + "        function onDelete(id){\n"
					+ "            window.location.href = 'http://localhost:8085/contactmanagement/deleteConfirm/'+id;"
					+ "        };\n" + "    </script>");

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
