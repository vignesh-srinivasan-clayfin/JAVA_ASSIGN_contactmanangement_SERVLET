package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helper.CustomException;
import com.helper.Helper;
import com.modal.ContactsModal;
import com.modal.HtmlContent;
import com.modal.response.GetAllContactsResponse;
import com.modal.response.Response;
import com.services.ContactServiceImpl;
import com.services.IContactService;

@WebServlet("/homepage.do")
public class homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IContactService contactService = null;

	public homepage() {
		super();
		contactService = new ContactServiceImpl();
	}

	public void onDeleteAlert(HttpServletRequest request, HttpServletResponse response) {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Helper helper = new Helper();

		StringBuilder contents = new StringBuilder();

		StringBuilder styles = new StringBuilder();
		
		StringBuilder error = new StringBuilder();
		
		Boolean isNoRecord = false;

		

		Response<GetAllContactsResponse> allContactRes;

		try {		
			

			allContactRes = contactService.getAllContacts();

			GetAllContactsResponse responseBody = allContactRes.getResponseBody();

			List<ContactsModal> contactList = responseBody.getContactList();
			
			contents.append("<h2 class=\"text-center mb-4\">Contact Management</h2>");
			
			contents.append("<div class=\"d-flex justify-content-center mb-4\"><a href=\"addContactDetail\" class=\"link-primary\">Add New Contact</a></div>");
			
			
			isNoRecord = contactList.isEmpty();
			
			if(!isNoRecord) {
				contents.append("<table class=\"table\">\n"
						+ "  <thead class=\"thead-dark\">\n"
						+ "    <tr>\n"
						+ "      <th scope=\"col\">#</th>\n"
						+ "      <th scope=\"col\">Name</th>\n"
						+ "      <th scope=\"col\">Phone Number</th>\n"
						+ "      <th scope=\"col\">Group</th>\n"
						+ "      <th scope=\"col\">Manage</th>\n"
						+ "    </tr>\n"
						+ "  </thead>\n"
						+ "  <tbody>\n");
			}else {
				
				contents.append("<div class=\"alert alert-warning text-center\" role=\"alert\">\n"
						+ " No Contacts Found\n"
						+ "</div>");
			}
			
			

//			contents.append("<ul class=\"list-group\">");
			
			int index = 1;

			for (ContactsModal contact : contactList) {
				
				String groupName = contact.getGroupName();
				if(groupName.contentEquals("NONE")) {
					groupName = "-";
				}
				
				
				contents.append(		"    <tr>\n"
						+ "      <th scope=\"row\">"+index+++"</th>\n"
						+ "      <td>"+ contact.getName()+"</td>\n"
						+ "      <td>"+ contact.getPhoneNumber()+"</td>\n"
						+ "      <td>"+ groupName+"</td>\n"
						+ "<td  class=\"d-flex column\"> <form action=\"editContactDetail\" method=\"post\">\n"
								+ "<button class=\"icon_btn\" type=\"submit\">"
								+ "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-pencil\" viewBox=\"0 0 16 16\">\n"
								+ "  <path d=\"M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z\"/>\n"
								+ "</svg>" + "</button>" + "<input type=\"hidden\" id=\"contactId\" name=\"contactId\" value="
								+ contact.getId() + ">" + "</form>" +"\n"
+"<form action=\"deleteContactDetail\" method=\"post\">\n"
+ "<button class=\"icon_btn\" type=\"submit\">"
+ "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-trash3\" viewBox=\"0 0 16 16\">\n"
+ "  <path d=\"M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5\"/>\n"
+ "</svg>" + "<input type=\"hidden\" id=\"contactId\" name=\"contactId\" value="
+ contact.getId() + ">" + "</form></td>"
						+ "    </tr>\n");


			}
			
			
			contents.append("  </tbody>\n"
					+ "</table>\n");

			styles.append(
					"<style>\n" + ".icon_btn {\n" + "  background: none;\n" + "  border: none;\n" + "}\n" + "</style>");

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
