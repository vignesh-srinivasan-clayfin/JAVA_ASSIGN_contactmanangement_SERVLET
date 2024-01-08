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
import com.modal.GroupModal;
import com.modal.response.GetAllGroupsResponse;
import com.modal.response.Response;
import com.services.ContactServiceImpl;

@WebServlet("/addContactDetail")
public class AddContactDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddContactDetail() {
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

		try {

			Response<GetAllGroupsResponse> allGroupsRes = contactService.getAllGroups();

			List<GroupModal> groupList = allGroupsRes.getResponseBody().getGroupList();

			StringBuilder groupOptions = new StringBuilder();

			for (int i = 0; i < groupList.size(); i++) {
				groupOptions.append(
						" <option value=" + groupList.get(i).getId() + ">" + groupList.get(i).getName() + "</option>");

			}

			contents.append("<div class=\"card border-dark mb-3\">\n"
					+ "  <h5 class=\"card-header\">Add New Contact</h5>\n" + "  <div class=\"card-body\">\n"

					+ "<form action=\"addContactConfirm\" method=\"post\" id=\"editForm\">\n"

					+ " <div class=\"form-group mb-4\">\n" + "    <label for=\"name\">Name</label>\n"
					+ "    <input type=\"text\" name=\"name\" class=\"form-control\"  id=\"name\" placeholder=\"Enter the name\">\n"
					+ "</div>\n"

					+ " <div class=\"form-group mb-4\">\n" + "    <label for=\"name\">Phone Number</label>\n"
					+ "    <input type=\"text\" name=\"phoneNumber\" class=\"form-control\" id=\"name\" placeholder=\"Enter the phonenumber\">\n"
					+ "  </div>\n"

					+ "  <div class=\"form-group mb-4\">\n"

					+ "    <label for=\"exampleFormControlSelect1\">Select Group</label>\n"
					+ "    <select name=\"groupId\" class=\"form-control\" id=\"exampleFormControlSelect1\" form=\"editForm\">\n"
					+ groupOptions + "    </select>\n" + "  </div>\n"

					+ " <input type=\"hidden\" id=\"id\" name=\"id\" >"

					+ " <button  class=\"btn btn-primary\" type=\"submit\">Submit</button>"

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
