package com.tsstu.console.dao;

import com.tsstu.console.model.Task;

/**
 * 任务管理Mapper 接口
 * @author： admin
 * @date： 2017-05-13
 **/
public interface TaskMapper extends BaseMapper<Task> {

	int updateStatusByAutoStartup();

	
}