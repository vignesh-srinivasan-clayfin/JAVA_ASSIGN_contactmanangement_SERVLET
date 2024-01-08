package com.modal.request;

import com.modal.ContactsModal;

public class EditContactRequest {
	
	
	
public ContactsModal contactInfo = new ContactsModal();
	
	
	public EditContactRequest(){
		
		
		
	}


	public ContactsModal getContactInfo() {
		return contactInfo;
	}


	public void setContactInfo(ContactsModal contactInfo) {
		this.contactInfo = contactInfo;
	}
	


}
