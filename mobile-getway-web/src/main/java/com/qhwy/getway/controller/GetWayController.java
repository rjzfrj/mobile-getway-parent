package com.qhwy.getway.controller;

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

import com.alibaba.fastjson.JSONObject;

@Controller
public class GetWayController {
	private static Logger log = org.slf4j.LoggerFactory.getLogger(GetWayController.class);

	@ResponseBody
	@RequestMapping("/test")
	public String categoryList(String test) {
		System.out.println(test);
		return test;
	}

	/*
	 * @RequestMapping("") public String index() { return "index"; }
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("403")
	public String noa() {
		return "403";
	}
	@ResponseBody
	@RequestMapping("/gologin")
	public String goLogin() {
		JSONObject retJson = new JSONObject();
		retJson.put("retCode", "0001");
		retJson.put("retMsg", "用户为登录请登录！");
		return retJson.toJSONString();
	}
/*	
	@RequestMapping("/gologin")
	public String goLogin() {
		return "login";
	}
*/	
	
	
	/**
	 * 模拟绑卡权限控制.
	 * 
	 * @return 
	 */
	
	@RequiresPermissions("pms:listMain:view")
	@ResponseBody
	@RequestMapping("/listMain")
	public String listMain(HttpServletRequest req, String userName, String cardNo){
		log.info("listMain入参:{userName:", userName + " passWord:" + cardNo + "}");
		log.info("listMain成功");
		JSONObject retJson = new JSONObject();
		retJson.put("retCode", "0000");
		retJson.put("retMsg", "模拟成功已经进入主页返回主页数据");
		return retJson.toJSONString();
	}
	/**
	 * 模拟绑卡权限控制.
	 * 
	 * @return 
	 */
	
	@RequiresPermissions("pms:card:add")
	@ResponseBody
	@RequestMapping("/bindCard")
	public String bindCard(HttpServletRequest req, String userName, String cardNo){
		log.info("bindCard入参:{userName:", userName + " passWord:" + cardNo + "}");
		log.info("bindCard成功");
		JSONObject retJson = new JSONObject();
		retJson.put("retCode", "0000");
		retJson.put("retMsg", "模拟成功已经进bindCard成功返回数据");
		return retJson.toJSONString();
	}

	@ResponseBody
	@RequestMapping("/login")
	public String login(HttpServletRequest req, String userName, String passWord) {
		log.info("login入参:{userName:", userName + " passWord:" + passWord + "}");
		JSONObject retJson = new JSONObject();
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passWord);
			SecurityUtils.getSubject().login(usernamePasswordToken);
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			retJson.put("token", session.getId());
			String error = null;
		} catch (UnknownAccountException e) {
			retJson.put("message", "用户不存在");
		} catch (IncorrectCredentialsException e) {
			retJson.put("message", "用户名/密码错误");
		} catch (LockedAccountException e) {
			retJson.put("message", "账号冻结,请联系管理员");
		} catch (AuthenticationException e) {
			retJson.put("message", "其他错误");
		} catch (Exception e) {
			retJson.put("message", "未知异常");
		}
		
		return retJson.toJSONString();
	}

	@RequestMapping("/ynshweixinAlipayH5Pay")
	public String ynshweixinAlipayH5Pay() {
		return "ynshweixinAlipayH5Pay";
	}

}
