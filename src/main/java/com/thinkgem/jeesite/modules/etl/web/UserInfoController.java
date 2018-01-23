/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.etl.web.dao.UserInfoDao;
import com.thinkgem.jeesite.modules.etl.web.entity.UserInfo;
import com.thinkgem.jeesite.modules.etl.web.service.UserInfoService;
import com.thinkgem.jeesite.modules.handler.PlatformRes;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息Controller
 *
 * @author yt
 * @version 2018-01-22
 */
@Controller
@RequestMapping(value = "${adminPath}/userInfo/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @ModelAttribute
    public UserInfo get(@RequestParam(required = false) String id) {
        UserInfo entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = userInfoService.get(id);
        }
        if (entity == null) {
            entity = new UserInfo();
        }
        return entity;
    }

    @RequiresPermissions("userInfo:userInfo:view")
    @RequestMapping(value = {"list", ""})
    public String list(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<UserInfo> page = userInfoService.findPage(new Page<UserInfo>(request, response), userInfo);
        model.addAttribute("page", page);
        return "modules/userInfo/userInfoList";
    }


    @RequiresPermissions("userInfo:userInfo:view")
    @RequestMapping(value = "detail")
    public String detailList(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<UserInfo> page = userInfoService.findPage(new Page<UserInfo>(request, response), userInfo);
        model.addAttribute("page", page);
        return "modules/userInfo/detailList";
    }

    @RequiresPermissions("userInfo:userInfo:view")
    @RequestMapping(value = "form")
    public String form(UserInfo userInfo, Model model) {
        model.addAttribute("userInfo", userInfo);
        return "modules/userInfo/userInfoForm";
    }

    @Autowired
    private UserInfoDao userInfoDao;


    @RequiresPermissions("userInfo:userInfo:edit")
    @RequestMapping(value = "save")
    public String save(UserInfo userInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, userInfo)) {
            return form(userInfo, model);
        }
        //保存判断
        UserInfo selectInfo = null;
        if (userInfo.getIsNewRecord()) {
            userInfo.setId(null);
            selectInfo = userInfoDao.findUserInfoByNameAndPhone(userInfo.getName(), userInfo.getPhone());
        } else {
            selectInfo = userInfoDao.findInvitationCodeByPhoneAndNameAndId(userInfo.getId(), userInfo.getName(), userInfo.getPhone());
        }

        if (selectInfo != null) {
            addMessage(redirectAttributes, "失败,当前用户手机号已存在");
            return "redirect:" + Global.getAdminPath() + "/userInfo/userInfo/?repage";
        }


        userInfoService.save(userInfo);
        addMessage(redirectAttributes, "保存用户信息成功");
        return "redirect:" + Global.getAdminPath() + "/userInfo/userInfo/?repage";
    }

    @RequiresPermissions("userInfo:userInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(UserInfo userInfo, RedirectAttributes redirectAttributes) {
        userInfoService.delete(userInfo);
        addMessage(redirectAttributes, "删除用户信息成功");
        return "redirect:" + Global.getAdminPath() + "/userInfo/userInfo/?repage";
    }


    @RequestMapping(value = "login")
    @ResponseBody
    public PlatformRes<String> login(UserInfo userInfo) {
        return userInfoService.login(userInfo);
    }


    @RequestMapping(value = "register")
    @ResponseBody
    public PlatformRes<String> register(UserInfo userInfo) {
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
    public PlatformRes<String> getInvitationCode(UserInfo userInfo) {
        return userInfoService.getInvitationCodeByPhoneAndName(userInfo);
    }


}