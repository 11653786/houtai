/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.etl.web.entity.UserByInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 开卡信息保存成功DAO接口
 * @author yt
 * @version 2018-01-24
 */
@MyBatisDao
public interface UserByInfoDao extends CrudDao<UserByInfo> {


    UserByInfo findUserInfoByPhone(@Param("phone")String phone);
	
}