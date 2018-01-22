/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.service;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.etl.web.dao.UserInfoDao;
import com.thinkgem.jeesite.modules.etl.web.entity.UserInfo;
import com.thinkgem.jeesite.modules.handler.PlatformRes;
import com.thinkgem.jeesite.modules.handler.ResCodeMsgType;
import com.thinkgem.jeesite.util.PhoneFormatCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

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


    /**
     * 登录更新银行卡信息
     *
     * @param userInfo
     * @return
     */
    @Transactional(readOnly = false)
    public PlatformRes<String> login(UserInfo userInfo) {
        //用户银行卡名称必填写
        if (userInfo == null || StringUtils.isEmpty(userInfo.getName()) || StringUtils.isEmpty(userInfo.getPhone())) {
            return PlatformRes.error(ResCodeMsgType.PARAMS_NOT_EMPTY);
        }


        UserInfo selectInfo = userInfoDao.findUserInfoByNameAndPhone(userInfo.getName(), userInfo.getPhone());
        //手机号注册判断
        if (selectInfo == null) {
            return PlatformRes.error(ResCodeMsgType.USER_NOT_IN);
        }


        //传递银行卡就更新
        if (!StringUtils.isEmpty(userInfo.getCareCode())) {
            selectInfo.setCareCode(userInfo.getCareCode());
            userInfoDao.update(selectInfo);
        }


        //登录返回邀请码
        return PlatformRes.success(selectInfo.getInvitationCode());
    }


    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    @Transactional(readOnly = false)
    public PlatformRes<String> register(UserInfo userInfo) {
        //用户银行卡名称必填写
        if (userInfo == null || StringUtils.isEmpty(userInfo.getName()) || StringUtils.isEmpty(userInfo.getPhone()) || userInfo.getAdvanceOpenCodeTime() == null) {
            return PlatformRes.error(ResCodeMsgType.PARAMS_NOT_EMPTY);
        }

        if(!PhoneFormatCheckUtils.isPhoneLegal(userInfo.getPhone())){
            return PlatformRes.error(ResCodeMsgType.PHONE_FORMATTER_ERROR);
        }


        UserInfo selectInfo = userInfoDao.findUserInfoByNameAndPhone(userInfo.getName(), userInfo.getPhone());
        //手机号注册判断
        if (selectInfo != null) {
            return PlatformRes.error(ResCodeMsgType.PHONE_WAS_REGISTER);
        }

        //
        userInfo.setCreateTime(new Date());
        //注册生成邀请码
        userInfo.setInvitationCode(getCode());

        super.save(userInfo);
        return PlatformRes.success(userInfo.getInvitationCode());
    }


    @Transactional(readOnly = false)
    public PlatformRes<String> getInvitationCodeByPhoneAndName(UserInfo userInfo) {
        UserInfo selectInfo = userInfoDao.findUserInfoByNameAndPhone(userInfo.getName(), userInfo.getPhone());
        //手机号注册判断
        if (selectInfo == null) {
            return PlatformRes.error(ResCodeMsgType.USER_NOT_IN);
        } else {
            return PlatformRes.success(selectInfo.getInvitationCode());
        }


    }


    public String getCode() {
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGIJKLMNOPQRSTUVWXYZ";
        int charLength = charStr.length();
        StringBuffer valSb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(charLength);
            valSb.append(charStr.charAt(index));
        }
        return valSb.toString();
    }


}