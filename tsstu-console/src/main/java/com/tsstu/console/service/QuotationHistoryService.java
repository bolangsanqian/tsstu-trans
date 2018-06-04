package com.tsstu.console.service;

import java.util.List;

import com.tsstu.common.model.QuotationHistory;

/**
 * 历史行情业务接口
 * @author： admin
 * @date：2017-05-13
 **/
public interface QuotationHistoryService extends BaseService<QuotationHistory> {

	/**
	 * 删除行情记录
	 * @param code 行情代码
	 * @param minute 分钟线
	 * @param keepCount 保留行数
	 * @return
	 */
	int deleteQuotationHistory(String code, Integer minute, Integer keepCount);

	/**
	 * 根据行情代码和分钟查询k线列表
	 * @param code 行情代码 
	 * @param minute 分钟
	 * @param limit
	 * @return
	 */
	List<QuotationHistory> getListByCodeAndMinute(String code, int minute, int limit);
}
