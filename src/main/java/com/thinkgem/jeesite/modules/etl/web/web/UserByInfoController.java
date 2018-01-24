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
import com.thinkgem.jeesite.modules.etl.web.entity.UserByInfo;
import com.thinkgem.jeesite.modules.etl.web.service.UserByInfoService;

/**
 * 开卡信息保存成功Controller
 * @author yt
 * @version 2018-01-24
 */
@Controller
@RequestMapping(value = "${adminPath}/etl.web/userByInfo")
public class UserByInfoController extends BaseController {

	@Autowired
	private UserByInfoService userByInfoService;
	
	@ModelAttribute
	public UserByInfo get(@RequestParam(required=false) String id) {
		UserByInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userByInfoService.get(id);
		}
		if (entity == null){
			entity = new UserByInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("etl.web:userByInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserByInfo userByInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserByInfo> page = userByInfoService.findPage(new Page<UserByInfo>(request, response), userByInfo); 
		model.addAttribute("page", page);
		return "modules/etl.web/userByInfoList";
	}

	@RequiresPermissions("etl.web:userByInfo:view")
	@RequestMapping(value = "form")
	public String form(UserByInfo userByInfo, Model model) {
		model.addAttribute("userByInfo", userByInfo);
		return "modules/etl.web/userByInfoForm";
	}

	@RequiresPermissions("etl.web:userByInfo:edit")
	@RequestMapping(value = "save")
	public String save(UserByInfo userByInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userByInfo)){
			return form(userByInfo, model);
		}
		userByInfoService.save(userByInfo);
		addMessage(redirectAttributes, "保存开卡信息保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/etl.web/userByInfo/?repage";
	}
	
	@RequiresPermissions("etl.web:userByInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(UserByInfo userByInfo, RedirectAttributes redirectAttributes) {
		userByInfoService.delete(userByInfo);
		addMessage(redirectAttributes, "删除开卡信息保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/etl.web/userByInfo/?repage";
	}

}