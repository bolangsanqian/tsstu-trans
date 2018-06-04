package com.tsstu.front.dao;

import java.util.List;

import com.tsstu.front.model.HoldTime;

/**
 * 持单时间Mapper 接口
 * @author： admin
 * @date： 2017-05-09
 **/
public interface HoldTimeMapper extends BaseMapper<HoldTime> {

	List<HoldTime> getDictList(Integer product_id);

	
}