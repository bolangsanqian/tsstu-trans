package com.tsstu.console.service;

import com.tsstu.console.model.Task;

/**
 * 任务管理业务接口
 * @author： admin
 * @date：2017-05-13
 **/
public interface TaskService extends BaseService<Task> {

	int updateStatusByAutoStartup();

}
