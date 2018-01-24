/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 开卡信息保存成功Entity
 * @author yt
 * @version 2018-01-24
 */
public class UserByInfo extends DataEntity<UserByInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 用户姓名
	private String byUserInvitationCode;		// 邀请人邀请码
	private String phone;		// 手机号
	private Date advanceOpenCodeTime;		// 预约开卡日
	private Date createTime;		// 创建时间
	
	public UserByInfo() {
		super();
	}

	public UserByInfo(String id){
		super(id);
	}

	@Length(min=0, max=50, message="用户姓名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="邀请人邀请码长度必须介于 0 和 50 之间")
	public String getByUserInvitationCode() {
		return byUserInvitationCode;
	}

	public void setByUserInvitationCode(String byUserInvitationCode) {
		this.byUserInvitationCode = byUserInvitationCode;
	}
	
	@Length(min=0, max=50, message="手机号长度必须介于 0 和 50 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAdvanceOpenCodeTime() {
		return advanceOpenCodeTime;
	}

	public void setAdvanceOpenCodeTime(Date advanceOpenCodeTime) {
		this.advanceOpenCodeTime = advanceOpenCodeTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}