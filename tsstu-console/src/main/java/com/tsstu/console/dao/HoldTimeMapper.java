package com.tsstu.console.dao;

import java.util.List;

import com.tsstu.console.model.HoldTime;

/**
 * 持单时间Mapper 接口
 * @author： admin
 * @date： 2017-05-09
 **/
public interface HoldTimeMapper extends BaseMapper<HoldTime> {

	List<HoldTime> getDictList();

	
}