package com.baobao.common.mapping;

import java.util.List;

import com.baobao.common.model.andshow.Contacts;

public interface TaskContactsMapper {
	
	
	public List<Contacts> getTaskContacts(Integer[] ids);
	
}
