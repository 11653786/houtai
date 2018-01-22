/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.etl.web.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息DAO接口
 *
 * @author yt
 * @version 2018-01-22
 */
@MyBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {

    /**
     * 根据银行卡号获取用户信息
     *
     * @param cardCode
     * @return
     */
    UserInfo findUserInfoByPhone(@Param("cardCode") String cardCode);

}