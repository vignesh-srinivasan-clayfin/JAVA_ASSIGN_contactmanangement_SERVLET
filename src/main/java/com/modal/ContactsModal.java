package com.modal;

import java.util.HashMap;
import java.util.Map;

public class ContactsModal {
	
		public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber; 	
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

		protected String id;
		protected String name;
		protected String phoneNumber;
		protected String groupName;
		protected String groupId;

		public ContactsModal() {
		}

//		public ContactsModal(HashMap<String, String> spObj) {
//			super();
//			// Access params by key
//			this.id = (String) spObj.get("id");
//			this.name = (String) spObj.get("name");
//			this.phoneNumber = (String) spObj.get("phoneNumber");
//			this.groupName = (String) spObj.get("groupName");
//
//		}
		
		 public ContactsModal(String id, String name, String phoneNumber,String groupName) {
		        this.id = id;
		        this.name = name;
		        this.phoneNumber = phoneNumber;
		        this.groupName = groupName;
		    }
		 
		 
		 public ContactsModal(String id, String name, String phoneNumber,String groupName,String groupId) {
			 this.id = id;
		        this.name = name;
		        this.phoneNumber = phoneNumber;
		        this.groupName = groupName;

			    this.groupId = groupId;
		    }
		 
		 public ContactsModal(String id, String name, String phoneNumber) {
		        this.id = id;
		        this.name = name;
		        this.phoneNumber = phoneNumber;
		    }

		
		
		
		public String getPhoneNumber() {
		    return this.phoneNumber;
		  }
		
		public String getId() {
		    return this.id;
		  }
		
		public String getName() {
		    return this.name;
		  }
		
		public String getGroupName() {
		    return this.groupName;
		  }

}
