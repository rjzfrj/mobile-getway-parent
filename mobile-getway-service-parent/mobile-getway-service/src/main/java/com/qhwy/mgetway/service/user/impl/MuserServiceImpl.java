package com.qhwy.mgetway.service.user.impl;

import com.qhwy.mgetway.dao.user.MuserMapper;
import com.qhwy.mgetway.entity.user.Muser;
import com.qhwy.mgetway.service.user.MuserService;
import com.qhwy.mgetway.service.user.RetMessage;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MuserServiceImpl implements MuserService {

	@Autowired
	private MuserMapper userMapper;

	@Override
	public RetMessage addUser(Muser user) {
		RetMessage ret = new RetMessage();
		Muser u = userMapper.selectByLoginName(user.getLoginName());
		if (u != null) {
			ret.setRetCode("0001");
			ret.setRetMsg("该手机号已经存在");
			return ret;
		}
		if (StringUtils.isNotBlank(user.getMobileReference())) {

			Muser ure = userMapper.selectByLoginName(user.getMobileReference());
			if (ure == null) {
				ret.setRetCode("0002");
				ret.setRetMsg("该手机号已经存在！");
				return ret;
			}
		}
 		int i = userMapper.insert(user);
		if(i>0){
			ret.setRetCode("0000");
			ret.setRetMsg("注册成功！");
			return ret;
		}else{
			ret.setRetCode("0001");
			ret.setRetMsg("注册失败！");
			return ret;
		}
	}

}
