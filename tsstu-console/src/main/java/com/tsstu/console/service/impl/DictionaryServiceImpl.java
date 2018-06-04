package com.tsstu.console.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.DictionaryItemMapper;
import com.tsstu.console.dao.DictionaryMapper;
import com.tsstu.console.model.Dictionary;
import com.tsstu.console.service.DictionaryService;

@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements DictionaryService {

	@Autowired
    private DictionaryMapper dictionaryMapper;
	
	@Autowired
    private DictionaryItemMapper dictionaryItemMapper;

	@Override
	public BaseMapper<Dictionary> getDao() {
		return dictionaryMapper	;
	}
	
	@Override
	@Transactional
	public int update(Dictionary model) {
		if (!model.getDict_code().equals(model.getOld_dict_code())) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("dict_code", model.getDict_code());
			condition.put("old_dict_code", model.getOld_dict_code());
			dictionaryItemMapper.update(condition);
		}
		return super.update(model);
	}
}
