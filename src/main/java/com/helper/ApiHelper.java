package com.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modal.AjaxResponse;

public class ApiHelper<T> {

	public String baseUrl = "http://localhost:8085/contactmanagement_rest/api/";

	public String onInvokeAPI(String URLPath, String method, String requestBody) throws CustomException {

		System.out.println("URLPath " + URLPath);
		System.out.println("method " + method);
		System.out.println("requestBody " + requestBody);

		System.out.println("requestBody length " + requestBody.length());

		String urlPath = baseUrl + URLPath;

		int responseCode = 0;

		StringBuilder responseStringBuilder = new StringBuilder();

		ObjectMapper objectMapper = new ObjectMapper();

		URL url;
		try {
			url = new URL(urlPath);

			// Open a connection to the URL
			HttpURLConnection connection;

			connection = (HttpURLConnection) url.openConnection();

			// Set the request method (GET, POST, PUT, DELETE, etc.)

			connection.setRequestMethod(method);

			if (requestBody.length() > 0) {

				connection.setRequestProperty("Content-Type", "application/json");

				connection.setRequestProperty("Accept", "application/json");
				// Enable input/output streams
				connection.setDoOutput(true);

				// Set the request body
				String tmpRrequestBody = requestBody;

				System.out.println("requestBody" + tmpRrequestBody);
				try (OutputStream os = connection.getOutputStream()) {
					byte[] input = tmpRrequestBody.getBytes(StandardCharsets.UTF_8);
					os.write(input, 0, input.length);
				}

			}

			responseCode = connection.getResponseCode();
			// Read the response from the web service
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				responseStringBuilder.append(line);
			}
			reader.close();

			System.out.println("responseCode===" + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {

				return responseStringBuilder.toString();

			}

//			else if (responseCode == 500) {
//
//				throw new CustomException("API_ERR", "500", "500");
//
//			} else if (responseCode == 400) {
//
//				throw new CustomException("API_ERR", "500", responseStringBuilder.toString());
//
//			} 

			else {

				throw new RuntimeException("Failed to retrieve blogs. HTTP error code: " + responseCode);

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CustomException("API_ERR_MalformedURLException", e.getMessage(), "");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			System.out.println("IOEX..." + responseCode);
			String error = "API_ERR_" + responseCode;

//			String errorResponse = "";

			if (responseCode == 400) {
				return responseStringBuilder.toString();
			}

			throw new CustomException(error, e1.getMessage(), "");

		}

	}

	public AjaxResponse onInvokeAPIV2(String URLPath, String method, String requestBody) throws CustomException {

		System.out.println("URLPath " + URLPath);
		System.out.println("method " + method);
		System.out.println("requestBody " + requestBody);

		System.out.println("requestBody length " + requestBody.length());

		String urlPath = baseUrl + URLPath;

		int responseCode = 0;

		StringBuilder responseStringBuilder = new StringBuilder();

		AjaxResponse ajaxRes = new AjaxResponse();

		URL url;
		try {
			url = new URL(urlPath);

			// Open a connection to the URL
			HttpURLConnection connection;

			connection = (HttpURLConnection) url.openConnection();

			// Set the request method (GET, POST, PUT, DELETE, etc.)

			connection.setRequestMethod(method);

			if (requestBody.length() > 0) {

				connection.setRequestProperty("Content-Type", "application/json");

				connection.setRequestProperty("Accept", "application/json");
				// Enable input/output streams
				connection.setDoOutput(true);

				// Set the request body
				String tmpRrequestBody = requestBody;

				System.out.println("requestBody" + tmpRrequestBody);
				try (OutputStream os = connection.getOutputStream()) {
					byte[] input = tmpRrequestBody.getBytes(StandardCharsets.UTF_8);
					os.write(input, 0, input.length);
				}
				
			}

			responseCode = connection.getResponseCode();
			// Read the response from the web service
			
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String line;
				while ((line = reader.readLine()) != null) {
					responseStringBuilder.append(line);
				}			
				
				reader.close();
				
				
				ajaxRes.setResponseCode(responseCode);
				ajaxRes.setResponseString(responseStringBuilder.toString());		

			}else if(responseCode==400) {
				
				System.out.println("responseCode Block===" + responseCode);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

				String line;
				while ((line = reader.readLine()) != null) {
					responseStringBuilder.append(line);
				}			
				
				ajaxRes.setResponseCode(responseCode);
				ajaxRes.setResponseString(responseStringBuilder.toString());		
				
				reader.close();

			}else {
				
				throw new IOException("Failed to retrieve blogs. HTTP error code: " + responseCode);
			}
			
			
			return ajaxRes;

			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CustomException("API_ERR_MalformedURLException", e.getMessage(), "");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			System.out.println("IOEX..." + responseCode);
			String error = "API_ERR_" + responseCode;

			throw new CustomException(error, e1.getMessage(), "");

		}

	}

}
