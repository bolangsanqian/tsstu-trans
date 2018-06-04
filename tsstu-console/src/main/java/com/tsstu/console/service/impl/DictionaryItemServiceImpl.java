package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.DictionaryItemMapper;
import com.tsstu.console.model.DictionaryItem;
import com.tsstu.console.service.DictionaryItemService;

@Service
public class DictionaryItemServiceImpl extends BaseServiceImpl<DictionaryItem> implements DictionaryItemService {

	@Autowired
    private DictionaryItemMapper dictionaryItemMapper;

	@Override
	public BaseMapper<DictionaryItem> getDao() {
		return dictionaryItemMapper	;
	}
}
