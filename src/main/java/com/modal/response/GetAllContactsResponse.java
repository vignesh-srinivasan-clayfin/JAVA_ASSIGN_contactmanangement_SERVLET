package com.modal.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.modal.ContactsModal;

public class GetAllContactsResponse {

	public List<ContactsModal> contactList = new ArrayList<>();
	
	public GetAllContactsResponse() {
		
	}

	public GetAllContactsResponse(List<ContactsModal> contacts) {

		for (ContactsModal a : contacts) {

//			System.out.println("Print Contacts..." + a.getPhoneNumber());
//
//			HashMap<String, String> spObj = new HashMap<String, String>();
//
//			spObj.put("phoneNumber", a.getPhoneNumber());
//			spObj.put("id", a.getId());
//			spObj.put("name", a.getName());
//			spObj.put("groupName", a.getGroupName());

			this.contactList.add(new ContactsModal(a.getId(),a.getName(),a.getGroupName()));

		}

	}

	public List<ContactsModal> getContactList() {
		return contactList;
	}

	public void setContactList(List<ContactsModal> contactList) {
		this.contactList = contactList;
	}
	
	
	
}
