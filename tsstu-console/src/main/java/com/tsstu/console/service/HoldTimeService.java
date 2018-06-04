package com.tsstu.console.service;

import java.util.List;

import com.tsstu.console.model.HoldTime;

/**
 * 持单时间业务接口
 * @author： admin
 * @date：2017-05-09
 **/
public interface HoldTimeService extends BaseService<HoldTime> {

	List<HoldTime> getDictList();

}
