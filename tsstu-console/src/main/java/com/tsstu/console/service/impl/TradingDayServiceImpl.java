package com.tsstu.console.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.TradingDayMapper;
import com.tsstu.console.model.TradingDay;
import com.tsstu.console.service.TradingDayService;

/**
 * 交易日管理业务实现类
 * @author： admin
 * @date： 2017-05-02
 */
@Service
public class TradingDayServiceImpl extends BaseServiceImpl<TradingDay> implements TradingDayService {

    @Autowired
    private TradingDayMapper tradingDayMapper;
    
    @Override
	public BaseMapper<TradingDay> getDao() {
		return tradingDayMapper;
	}

	@Override
	public boolean addByCategoryId(Integer product_category_id) {
		int effectRow = 0;
		for(int i=1; i<8; i++) {
			TradingDay tradingDay = new TradingDay();
			tradingDay.setProduct_category_id(product_category_id);
			if (i<6) {
				tradingDay.setOpening_time("07:00:00");
				tradingDay.setClosing_time("07:00:00");
				tradingDay.setClosing_day(1);
			} else {
				tradingDay.setOpening_time("00:00:00");
				tradingDay.setClosing_time("00:00:00");
				tradingDay.setClosing_day(0);
			}
			tradingDay.setWeek(i);
			effectRow = tradingDayMapper.add(tradingDay);
		}
		return effectRow > 0;
	}

	@Override
	@Transactional
	public boolean resetByCategoryId(Integer product_category_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product_category_id", product_category_id);
		tradingDayMapper.delete(map);
		boolean bo = this.addByCategoryId(product_category_id);
		return bo;
	}
}
