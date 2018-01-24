/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.etl.web.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.etl.web.entity.UserInfo;
import com.thinkgem.jeesite.modules.etl.web.service.UserInfoService;

/**
 * 用户信息保存成功Controller
 * @author yt
 * @version 2018-01-24
 */
@Controller
@RequestMapping(value = "${adminPath}/etl.web/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoService;
	
	@ModelAttribute
	public UserInfo get(@RequestParam(required=false) String id) {
		UserInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userInfoService.get(id);
		}
		if (entity == null){
			entity = new UserInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("etl.web:userInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserInfo> page = userInfoService.findPage(new Page<UserInfo>(request, response), userInfo); 
		model.addAttribute("page", page);
		return "modules/etl.web/userInfoList";
	}

	@RequiresPermissions("etl.web:userInfo:view")
	@RequestMapping(value = "form")
	public String form(UserInfo userInfo, Model model) {
		model.addAttribute("userInfo", userInfo);
		return "modules/etl.web/userInfoForm";
	}

	@RequiresPermissions("etl.web:userInfo:edit")
	@RequestMapping(value = "save")
	public String save(UserInfo userInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userInfo)){
			return form(userInfo, model);
		}
		userInfoService.save(userInfo);
		addMessage(redirectAttributes, "保存用户信息保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/etl.web/userInfo/?repage";
	}
	
	@RequiresPermissions("etl.web:userInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(UserInfo userInfo, RedirectAttributes redirectAttributes) {
		userInfoService.delete(userInfo);
		addMessage(redirectAttributes, "删除用户信息保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/etl.web/userInfo/?repage";
	}

}