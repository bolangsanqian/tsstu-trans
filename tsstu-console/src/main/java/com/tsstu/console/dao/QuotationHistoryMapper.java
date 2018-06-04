package com.tsstu.console.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsstu.common.model.QuotationHistory;

/**
 * 历史行情Mapper 接口
 * @author： admin
 * @date： 2017-05-13
 **/
public interface QuotationHistoryMapper extends BaseMapper<QuotationHistory> {

	/**
	 * 删除行情记录
	 * @param code 行情代码
	 * @param minute 分钟线
	 * @param keepCount 保留行数
	 * @return
	 */
	int deleteQuotationHistory(@Param("code")String code, @Param("minute")Integer minute, @Param("keepCount")Integer keepCount);

	/**
	 * 根据行情代码和分钟查询k线列表
	 * @param code 行情代码 
	 * @param minute 分钟
	 * @param limit
	 * @return
	 */
	List<QuotationHistory> getListByCodeAndMinute(@Param("code")String code, @Param("minute")int minute, @Param("limit")int limit);

	
}