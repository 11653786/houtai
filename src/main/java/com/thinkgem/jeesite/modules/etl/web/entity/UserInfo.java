/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户信息保存成功Entity
 * @author yt
 * @version 2018-01-24
 */
public class UserInfo extends DataEntity<UserInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String careCode;		// 用户银行卡号
	private String manager;		// 理财经理
	private String invitationCode;		// 邀请码
	private Date createTime;		// 创建时间
	
	public UserInfo() {
		super();
	}

	public UserInfo(String id){
		super(id);
	}

	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="用户银行卡号长度必须介于 0 和 50 之间")
	public String getCareCode() {
		return careCode;
	}

	public void setCareCode(String careCode) {
		this.careCode = careCode;
	}
	
	@Length(min=0, max=50, message="理财经理长度必须介于 0 和 50 之间")
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@Length(min=0, max=50, message="邀请码长度必须介于 0 和 50 之间")
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}