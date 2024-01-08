package com.modal.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.modal.GroupModal;

public class GetAllGroupsResponse {

	public List<GroupModal> groupList = new ArrayList<>();

	public GetAllGroupsResponse() {

	}

	public GetAllGroupsResponse(List<GroupModal> groups) {

		for (GroupModal a : groups) {

//			HashMap<String, String> spObj = new HashMap<String, String>();

//			spObj.put("id", a.getId());
//			spObj.put("name", a.getName());

			groupList.add(new GroupModal(a.getId(), a.getName()));

		}

	}

	public List<GroupModal> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupModal> groupList) {
		this.groupList = groupList;
	}

}
