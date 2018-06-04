package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.util.FileUtil;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.dao.ImageMapper;
import com.tsstu.console.model.Image;
import com.tsstu.console.service.ImageService;
import com.tsstu.console.util.PathUtil;

/**
 * 图片管理业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService {

    @Autowired
    private ImageMapper imageMapper;
    
    @Override
	public BaseMapper<Image> getDao() {
		return imageMapper;
	}
    
    @Override
    public int delete(int id) {
    	Image image = this.getOne(id);
    	if (null != image.getImage_url() && !"".equals(image.getImage_url())) {
    		String filePath = PathUtil.getClasspath() + image.getImage_url();
    		FileUtil.delFile(filePath);
    	}
    	//删除图片
    	return super.delete(id);
    }
    
    @Override
    public int update(Image model) {
    	Image image = this.getOne(model.getId());
    	if (null != image.getImage_url() && !"".equals(image.getImage_url())) {
    		String filePath = PathUtil.getClasspath() + image.getImage_url();
    		FileUtil.delFile(filePath);
    	}
    	return super.update(model);
    }
}
