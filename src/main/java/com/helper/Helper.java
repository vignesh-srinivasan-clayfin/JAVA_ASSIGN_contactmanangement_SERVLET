package com.helper;

import java.io.IOException;

public class Helper {

	public Helper() {

	}

	public StringBuilder pageBuilder(StringBuilder contents) throws IOException {

		StringBuilder styles = new StringBuilder();

		StringBuilder error = new StringBuilder();

		return pageBuilder(contents, styles, error);

	}

	public StringBuilder pageBuilder(StringBuilder contents, StringBuilder styles, StringBuilder error)
			throws IOException {

		StringBuilder htmlContent = new StringBuilder();

		htmlContent.append("<html>");

		htmlContent.append("<head>");

		htmlContent.append(
				"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");

		htmlContent.append(
				"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\" crossorigin=\"anonymous\"></script>");

		htmlContent
				.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>");

		if (styles.length() != 0) {

			htmlContent.append(styles);

		}

		htmlContent.append("</head>");

		htmlContent.append("<body>");

		htmlContent.append("<div class=\"container mt-4\">");

		if (error.length() != 0) {

			htmlContent.append(error);

		}

		htmlContent.append("<div class=\"row align-items-end\">");
		htmlContent.append("<div class=\"col\">");
		htmlContent.append("</div>");
		htmlContent.append("<div class=\"col-6\">");

		htmlContent.append(contents);

		htmlContent.append("</div>");
		htmlContent.append("<div class=\"col\">");
		htmlContent.append("</div>");
		htmlContent.append("</div>");

		htmlContent.append("</div>");

		htmlContent.append("</body>");

		htmlContent.append("</html>");

		return htmlContent;
	}

	public StringBuilder showErrorBanner(String error) {

		StringBuilder htmlContent = new StringBuilder();

		htmlContent.append("<div class=\"alert alert-danger\" role=\"alert\">\n"
				+ error 
				+ "</div>");
		
		

		return htmlContent;

	}
	
public StringBuilder exceptionHandler (Exception e) {
		
		
	StringBuilder errorMessage = new StringBuilder();

		if (e.getMessage().contentEquals("API_ERR_500")) {
			errorMessage.append("<strong>");
			errorMessage.append("API_ERR_500\n");
			errorMessage.append("</strong>");
			errorMessage.append("</br>");
			errorMessage.append("</br>");
			
			errorMessage.append("Internal Server Error");			

		} else if (e.getMessage().contentEquals("API_ERR_404")) {
			errorMessage.append("<strong>");
			errorMessage.append("API_ERR_404\n");
			errorMessage.append("</strong>");
			errorMessage.append("</br>");
			errorMessage.append("</br>");
			
			errorMessage.append("Genric Error");			

		}  else if (e.getMessage().contentEquals("API_ERR_400")) {
			errorMessage.append("<strong>");
			errorMessage.append("API_ERR_400\n");
			errorMessage.append("</strong>");
			errorMessage.append("</br>");
			errorMessage.append("</br>");
			
			errorMessage.append(((CustomException) e).getErrorMessage()+"\n");		

		}else {

			
			errorMessage.append("<strong>");
			errorMessage.append(e.getMessage()+"\n");
			errorMessage.append("</strong>");
			errorMessage.append("</br>");
			errorMessage.append("</br>");
			
			errorMessage.append("Unknown Error");
			

			
		}
		
		StringBuilder htmlContent = new StringBuilder();

		htmlContent.append("<div class=\"alert alert-danger\" role=\"alert\">\n"
				+ errorMessage 
				+"</br>"
				+"</br>"
				+ "<a href=\"/contactmanagement\" class=\"alert-link\">Go to Index</a>"
				+ "</div>");
		
		

		return htmlContent;
				
		
	}

}
