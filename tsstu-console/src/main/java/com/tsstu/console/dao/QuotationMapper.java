package com.tsstu.console.dao;

import org.apache.ibatis.annotations.Param;

import com.tsstu.common.model.Quotation;

/**
 * 实时行情Mapper 接口
 * @author： admin
 * @date： 2017-05-13
 **/
public interface QuotationMapper extends BaseMapper<Quotation> {

	/**
	 * 删除行情记录
	 * @param code 行情代码
	 * @param keepCount 保留行数
	 * @return
	 */
	int deleteQuotation(@Param("code")String code, @Param("keepCount")Integer keepCount);

	/**
	 * 获取最新行情
	 * @param code 行情代码
	 * @return
	 */
	Quotation getLastQuotation(@Param("code")String code);

	
}