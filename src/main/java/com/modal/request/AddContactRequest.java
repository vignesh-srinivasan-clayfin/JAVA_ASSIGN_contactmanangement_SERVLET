package com.modal.request;

import com.modal.ContactsModal;

public class AddContactRequest {
	

	
public ContactsModal contactInfo = new ContactsModal();
	
	
	public AddContactRequest(){
		
		
		
	}


	public ContactsModal getContactInfo() {
		return contactInfo;
	}


	public void setContactInfo(ContactsModal contactInfo) {
		this.contactInfo = contactInfo;
	}

}
