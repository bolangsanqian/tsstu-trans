package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.TaskMapper;
import com.tsstu.console.model.Task;
import com.tsstu.console.service.TaskService;

/**
 * 任务管理业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    
    @Override
	public BaseMapper<Task> getDao() {
		return taskMapper;
	}

	@Override
	public int updateStatusByAutoStartup() {
		return taskMapper.updateStatusByAutoStartup();
	}
}
