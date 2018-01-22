/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.service;

import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.thinkgem.jeesite.modules.handler.PlatformRes;
import com.thinkgem.jeesite.modules.handler.ResCodeMsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.etl.web.entity.UserInfo;
import com.thinkgem.jeesite.modules.etl.web.dao.UserInfoDao;

/**
 * 用户信息Service
 *
 * @author yt
 * @version 2018-01-22
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudService<UserInfoDao, UserInfo> {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo get(String id) {
        return super.get(id);
    }

    public List<UserInfo> findList(UserInfo userInfo) {
        return super.findList(userInfo);
    }

    public Page<UserInfo> findPage(Page<UserInfo> page, UserInfo userInfo) {
        return super.findPage(page, userInfo);
    }

    @Transactional(readOnly = false)
    public void save(UserInfo userInfo) {
        super.save(userInfo);
    }

    @Transactional(readOnly = false)
    public void delete(UserInfo userInfo) {
        super.delete(userInfo);
    }

    @Transactional(readOnly = false)
    public PlatformRes<String> register(UserInfo userInfo) {
        //用户银行卡名称必填写
        if (userInfo == null || StringUtils.isEmpty(userInfo.getName()) || StringUtils.isEmpty(userInfo.getPhone()) || userInfo.getAdvanceOpenCodeTime() == null
                || StringUtils.isEmpty(userInfo.getByUserInvitationCode())) {
            return PlatformRes.error(ResCodeMsgType.PARAMS_NOT_EMPTY);
        }


        UserInfo selectInfo = userInfoDao.findUserInfoByPhone(userInfo.getPhone());
        //银行卡注册判断
        if (selectInfo != null) {
            return PlatformRes.error(ResCodeMsgType.PHONE_WAS_REGISTER);
        }

        //

        super.save(userInfo);
    }


}