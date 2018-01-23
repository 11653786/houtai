package com.thinkgem.jeesite;

import com.thinkgem.jeesite.modules.etl.web.entity.UserInfo;
import com.thinkgem.jeesite.modules.etl.web.service.UserInfoService;
import com.thinkgem.jeesite.modules.handler.PlatformRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangtao on 2018-01-23.
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {


    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "login")
    @ResponseBody
    public PlatformRes<String> login(@RequestBody UserInfo userInfo) {
        return userInfoService.login(userInfo);
    }


    @RequestMapping(value = "register")
    @ResponseBody
    public PlatformRes<String> register(@RequestBody UserInfo userInfo) {
        return userInfoService.register(userInfo);
    }

    /**
     * 根据名称和手机号获取邀请码
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "getInvitationCode")
    @ResponseBody
    public PlatformRes<String> getInvitationCode(@RequestBody UserInfo userInfo) {
        return userInfoService.getInvitationCodeByPhoneAndName(userInfo);
    }

}
