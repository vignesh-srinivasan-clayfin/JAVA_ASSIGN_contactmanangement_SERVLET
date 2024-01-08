package com.services;

import java.io.IOException;
import java.net.ProtocolException;

import com.helper.CustomException;
import com.modal.request.AddContactRequest;
import com.modal.request.EditContactRequest;
import com.modal.response.AddContactResponse;
import com.modal.response.DeleteContactResponse;
import com.modal.response.EditContactResponse;
import com.modal.response.GetAllContactsResponse;
import com.modal.response.GetAllGroupsResponse;
import com.modal.response.GetContactDetailResponse;
import com.modal.response.Response;

public interface IContactService {

	public Response<GetAllContactsResponse> getAllContacts() throws ProtocolException, IOException, CustomException;
	
	public Response<GetContactDetailResponse> getContactDetailById(String id) throws ProtocolException, IOException, CustomException;
	
	public Response<DeleteContactResponse> deleteContactById(String id) throws ProtocolException, IOException, CustomException;
	
	public Response<GetAllGroupsResponse> getAllGroups() throws ProtocolException, IOException, CustomException;
	
	public Response<EditContactResponse> updateContact(EditContactRequest request) throws ProtocolException, IOException, CustomException;
	
	public Response<AddContactResponse> addContact(AddContactRequest request) throws ProtocolException, IOException, CustomException;
	
	
}
