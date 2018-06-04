package com.tsstu.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsstu.common.util.FileUtil;
import com.tsstu.console.dao.BannerMapper;
import com.tsstu.console.dao.BaseMapper;
import com.tsstu.console.model.Banner;
import com.tsstu.console.service.BannerService;
import com.tsstu.console.util.PathUtil;

/**
 * 轮播图片业务实现类
 * @author： admin
 * @date： 2017-05-13
 */
@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;
    
    @Override
	public BaseMapper<Banner> getDao() {
		return bannerMapper;
	}
    
    @Override
    public int delete(int id) {
    	Banner banner = this.getOne(id);
    	if (null != banner.getImage_url() && !"".equals(banner.getImage_url())) {
    		String filePath = PathUtil.getClasspath() + banner.getImage_url();
    		FileUtil.delFile(filePath);
    	}
    	return super.delete(id);
    }
    
    @Override
    public int update(Banner model) {
    	Banner banner = this.getOne(model.getId());
    	if (null != banner.getImage_url() && !"".equals(banner.getImage_url())) {
    		String filePath = PathUtil.getClasspath() + banner.getImage_url();
    		FileUtil.delFile(filePath);
    	}
    	return super.update(model);
    }
}
