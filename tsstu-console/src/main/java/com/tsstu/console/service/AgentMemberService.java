package com.tsstu.console.service;

import java.util.List;

import com.tsstu.console.model.AgentMember;

/**
 * 代理会员业务接口
 * @author： admin
 * @date：2017-05-04
 **/
public interface AgentMemberService extends BaseService<AgentMember> {

	/**
	 * 查询所有启用代理会员集合
	 * @return
	 */
	List<AgentMember> getEnableAgentMemberList();

	/**
	 * 修改公众号二维码
	 * @param id
	 * @param wx_qrcode_url
	 * @return
	 */
	int updateQrcodeUrl(Integer id, String wx_qrcode_url);

}
