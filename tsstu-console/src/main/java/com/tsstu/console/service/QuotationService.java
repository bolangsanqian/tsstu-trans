package com.tsstu.console.service;

import com.tsstu.common.model.Quotation;
import com.tsstu.console.model.ProductCategory;

/**
 * 实时行情业务接口
 * @author： admin
 * @date：2017-05-13
 **/
public interface QuotationService extends BaseService<Quotation> {

	public void saveQuotation(Quotation quotation, ProductCategory productCategory);

	public int deleteQuotation(String code, int keepCount);

	public Quotation getLastQuotation(String code);
	
	public boolean initToCache();
}
