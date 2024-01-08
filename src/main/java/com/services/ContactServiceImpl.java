package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helper.ApiHelper;
import com.helper.CustomException;
import com.modal.AjaxResponse;
import com.modal.request.AddContactRequest;
import com.modal.request.EditContactRequest;
import com.modal.response.AddContactResponse;
import com.modal.response.ApiFailureResponse;
import com.modal.response.DeleteContactResponse;
import com.modal.response.EditContactResponse;
import com.modal.response.GetAllContactsResponse;
import com.modal.response.GetAllGroupsResponse;
import com.modal.response.GetContactDetailResponse;
import com.modal.response.Response;

public class ContactServiceImpl implements IContactService {

	String baseUrl = "http://localhost:8085/contactmanagement_rest/api/";

	public ContactServiceImpl() {

	}

	@Override
	public Response<GetAllContactsResponse> getAllContacts() throws ProtocolException, IOException, CustomException {

		ApiHelper<GetAllContactsResponse> api = new ApiHelper<GetAllContactsResponse>();

		ObjectMapper objectMapper = new ObjectMapper();

		AjaxResponse res = api.onInvokeAPIV2("getAllContacts", "GET", "");

		if (res.getResponseCode() == 400) {

			Response<ApiFailureResponse> response = objectMapper.readValue(res.getResponseString(),
					new TypeReference<Response<ApiFailureResponse>>() {
					});

			throw new CustomException("API_ERR_400", response.getError(), response.getErrorCode());
		}

		Response<GetAllContactsResponse> response = objectMapper.readValue(res.getResponseString(),
				new TypeReference<Response<GetAllContactsResponse>>() {
				});

		return response;

	}

	@Override
	public Response<GetContactDetailResponse> getContactDetailById(String id)
			throws ProtocolException, IOException, CustomException {
		
		ApiHelper<GetContactDetailResponse> api = new ApiHelper<GetContactDetailResponse>();

		ObjectMapper objectMapper = new ObjectMapper();

		AjaxResponse res = api.onInvokeAPIV2("getContactDetail/" + id, "GET", "");

		if (res.getResponseCode() == 400) {

			Response<ApiFailureResponse> response = objectMapper.readValue(res.getResponseString(),
					new TypeReference<Response<ApiFailureResponse>>() {
					});

			throw new CustomException("API_ERR_400", response.getError(), response.getErrorCode());
		}

		Response<GetContactDetailResponse> response = objectMapper.readValue(res.getResponseString(),
				new TypeReference<Response<GetContactDetailResponse>>() {
				});

		return response;
		
	

	}

	@Override
	public Response<DeleteContactResponse> deleteContactById(String id)
			throws ProtocolException, IOException, CustomException {

		ApiHelper<DeleteContactResponse> api = new ApiHelper<DeleteContactResponse>();

		ObjectMapper objectMapper = new ObjectMapper();

		String res = api.onInvokeAPI("deleteContact/" + id, "DELETE", "");

		Response<DeleteContactResponse> response = objectMapper.readValue(res,
				new TypeReference<Response<DeleteContactResponse>>() {
				});

		return response;

	}

	@Override
	public Response<GetAllGroupsResponse> getAllGroups() throws ProtocolException, IOException, CustomException {
		
		
		ApiHelper<GetAllGroupsResponse> api = new ApiHelper<GetAllGroupsResponse>();

		ObjectMapper objectMapper = new ObjectMapper();

		String res = api.onInvokeAPI("getAllGroups", "GET", "");

		Response<GetAllGroupsResponse> response = objectMapper.readValue(res,
				new TypeReference<Response<GetAllGroupsResponse>>() {
				});

		return response;
		
		
		

	}

	@Override
	public Response<EditContactResponse> updateContact(EditContactRequest request)
			throws ProtocolException, IOException, CustomException {

		ApiHelper<EditContactResponse> api = new ApiHelper<EditContactResponse>();

		ObjectMapper objectMapper = new ObjectMapper();

		String requestBody = objectMapper.writeValueAsString(request);

		AjaxResponse res = api.onInvokeAPIV2("editContact", "POST", requestBody);

		if (res.getResponseCode() == 400) {

			System.out.println("Resp....."+res.getResponseString());
			Response<ApiFailureResponse> response = objectMapper.readValue(res.getResponseString(),
					new TypeReference<Response<ApiFailureResponse>>() {}
			);

			throw new CustomException("API_ERR_400", response.getError(), response.getErrorCode());
		}

		Response<EditContactResponse> response = objectMapper.readValue(res.getResponseString(),
				new TypeReference<Response<EditContactResponse>>() {}
		);

		return response;
		
		
//		String urlPath = baseUrl + "editContact";
//
//		URL url = new URL(urlPath);
//		// Open a connection to the URL
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		// Set the request method (GET, POST, PUT, DELETE, etc.)
//		connection.setRequestMethod("POST");
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		connection.setRequestProperty("Content-Type", "application/json");
//
//		connection.setRequestProperty("Accept", "application/json");
//		// Enable input/output streams
//		connection.setDoOutput(true);
//
//		// Set the request body
//		String requestBody = objectMapper.writeValueAsString(request);
//
//		System.out.println("requestBody" + requestBody);
//		try (OutputStream os = connection.getOutputStream()) {
//			byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
//			os.write(input, 0, input.length);
//		}
//
//		// Set other necessary properties (headers, timeouts, etc.) if needed
//		// Get the response code
//		int responseCode = connection.getResponseCode();
//		// Read the response from the web service
//		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		StringBuilder responseStringBuilder = new StringBuilder();
//		String line;
//		while ((line = reader.readLine()) != null) {
//			responseStringBuilder.append(line);
//		}
//		reader.close();
//		if (responseCode == HttpURLConnection.HTTP_OK) {
//
//			Response<EditContactResponse> res = objectMapper.readValue(responseStringBuilder.toString(),
//					new TypeReference<Response<EditContactResponse>>() {
//					});
//
//			return res;
//
//		} else {
//			throw new RuntimeException("Failed to retrieve blogs. HTTP error code: " + responseCode);
//		}

	}

	@Override
	public Response<AddContactResponse> addContact(AddContactRequest request)
			throws ProtocolException, IOException, CustomException {

		ApiHelper<AddContactResponse> api = new ApiHelper<AddContactResponse>();

		ObjectMapper objectMapper = new ObjectMapper();

		String requestBody = objectMapper.writeValueAsString(request);

		AjaxResponse res = api.onInvokeAPIV2("addContact", "POST", requestBody);

		if (res.getResponseCode() == 400) {

			System.out.println("Resp....."+res.getResponseString());
			Response<ApiFailureResponse> response = objectMapper.readValue(res.getResponseString(),
					new TypeReference<Response<ApiFailureResponse>>() {}
			);

			throw new CustomException("API_ERR_400", response.getError(), response.getErrorCode());
		}

		Response<AddContactResponse> response = objectMapper.readValue(res.getResponseString(),
				new TypeReference<Response<AddContactResponse>>() {}
		);

		return response;


	}

}
