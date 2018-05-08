package com.qhwy.mgetway.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qhwy.mgetway.entity.user.Muser;
import com.qhwy.mgetway.service.user.MuserService;
import com.qhwy.mgetway.service.user.RetMessage;

@Controller
public class UserController {
	private static Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@Resource
	private MuserService userService;
	


	@ResponseBody
	@RequestMapping("/register")
	public String register(HttpServletRequest req, Muser user) {
		log.info("register入参:"+user.toString());
//		JSONObject retJson = new JSONObject();
			RetMessage ret=userService.addUser(user);
			String error = null;
		
		return JSONObject.toJSONString(ret);
	}


}
