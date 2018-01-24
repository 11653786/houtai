/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.handler.PlatformRes;
import com.thinkgem.jeesite.modules.handler.ResCodeMsgType;
import com.thinkgem.jeesite.util.PhoneFormatCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.etl.web.entity.UserInfo;
import com.thinkgem.jeesite.modules.etl.web.dao.UserInfoDao;

/**
 * 用户信息保存成功Service
 *
 * @author yt
 * @version 2018-01-24
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudService<UserInfoDao, UserInfo> {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo get(String id) {
        return super.get(id);
    }


    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    @Transactional(readOnly = false)
    public PlatformRes<String> register(UserInfo userInfo) {
        //用户银行卡名称必填写
        if (userInfo == null || StringUtils.isEmpty(userInfo.getName()) || StringUtils.isEmpty(userInfo.getCareCode()))
            return PlatformRes.error(ResCodeMsgType.PARAMS_NOT_EMPTY);



        UserInfo selectInfo = userInfoDao.findByNameAndCareCode(userInfo.getName(), userInfo.getCareCode());
        //手机号注册判断
        if (selectInfo != null)
            return PlatformRes.error(ResCodeMsgType.USER_INFO_WAS_REGISTER);


        //
        userInfo.setCreateTime(new Date());
        //注册生成邀请码
        String code = null;
        while (true) {
            code = getCode();
            if (userInfoDao.findUserInfoByinvitationCode(code) == null)
                break;
        }
        userInfo.setInvitationCode(code);
        userInfoDao.insert(userInfo);
        return PlatformRes.success(userInfo.getInvitationCode());
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