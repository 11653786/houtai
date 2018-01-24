/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.service;

import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.handler.PlatformRes;
import com.thinkgem.jeesite.modules.handler.ResCodeMsgType;
import com.thinkgem.jeesite.util.PhoneFormatCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.etl.web.entity.UserByInfo;
import com.thinkgem.jeesite.modules.etl.web.dao.UserByInfoDao;

/**
 * 开卡信息保存成功Service
 *
 * @author yt
 * @version 2018-01-24
 */
@Service
@Transactional(readOnly = true)
public class UserByInfoService extends CrudService<UserByInfoDao, UserByInfo> {

    @Autowired
    private UserByInfoDao userByInfoDao;

    @Transactional(readOnly = false)
    public PlatformRes<String> register(UserByInfo userByInfo) {
        if (StringUtils.isBlank(userByInfo.getName()) || StringUtils.isBlank(userByInfo.getByUserInvitationCode()) ||
                StringUtils.isBlank(userByInfo.getPhone()) || userByInfo.getAdvanceOpenCodeTime() == null)
            return PlatformRes.error(ResCodeMsgType.PARAMS_NOT_EMPTY);

        //手机号合法判断
        if (!PhoneFormatCheckUtils.isPhoneLegal(userByInfo.getPhone()))
            return PlatformRes.error(ResCodeMsgType.PHONE_FORMATTER_ERROR);


        UserByInfo selectInfo = userByInfoDao.findUserInfoByPhone(userByInfo.getPhone());
        //手机号注册判断
        if (selectInfo != null) {
            return PlatformRes.error(ResCodeMsgType.PHONE_WAS_REGISTER);
        }

        userByInfoDao.insert(userByInfo);
        return PlatformRes.success("");


    }

    public UserByInfo get(String id) {
        return super.get(id);
    }

    public List<UserByInfo> findList(UserByInfo userByInfo) {
        return super.findList(userByInfo);
    }

    public Page<UserByInfo> findPage(Page<UserByInfo> page, UserByInfo userByInfo) {
        return super.findPage(page, userByInfo);
    }

    @Transactional(readOnly = false)
    public void save(UserByInfo userByInfo) {
        super.save(userByInfo);
    }

    @Transactional(readOnly = false)
    public void delete(UserByInfo userByInfo) {
        super.delete(userByInfo);
    }

}