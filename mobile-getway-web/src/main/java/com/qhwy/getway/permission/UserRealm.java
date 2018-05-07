
package com.qhwy.getway.permission;


import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.qhwy.getway.entity.User;
import com.qhwy.getway.service.RoleService;
import com.qhwy.getway.service.UserService;  
  
//import com.puhui.flowplatform.common.model.manage.ManageUser;  
//import com.puhui.flowplatform.manage.service.RoleService;  
//import com.puhui.flowplatform.manage.service.UserService;  
  
/**
 * 
 */
/**
 * @author lzf
 *
 */
public class UserRealm extends AuthorizingRealm {  
  
	@Autowired
    private UserService userService;  
  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
        String username = principals.getPrimaryPrincipal().toString();  
        SimpleAuthorizationInfo authorizationInfo  = new SimpleAuthorizationInfo();  
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user=(User) session.getAttribute("USER");
        if(user==null){
        	user=userService.findUserByUserName(username);
        	session.setAttribute("USER", user);
        }
        Set<String> roles = (Set<String>) session.getAttribute("ROLES");
        if(roles==null || roles.isEmpty()){
             roles = userService.findRolesByUserName(username);  
            session.setAttribute("ROLES", roles);
        }
        authorizationInfo .setRoles(roles);  
        Set<String> permissions=(Set<String>) session.getAttribute("PERMISSIONS");
        if(permissions==null ||permissions.isEmpty()){
        	 permissions = userService.findPermissionsByUserName(username);  
        	 session.setAttribute("PERMISSIONS", permissions);
        }
        authorizationInfo .setStringPermissions(permissions);  
        return authorizationInfo ;  
  
    }  
  
    /** 
     * 验证当前登录的Subject LoginController.login()方法中执行Subject.login()时 执行此方法 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {  
    	String loginName = (String) token.getPrincipal();
		if (StringUtils.isEmpty(loginName)) {
			throw new UnknownAccountException();// 没找到帐号
		}
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;// 获取用户输入的token  
        String username = utoken.getUsername();  
        User user = userService.findUserByUserName(username);  
        if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
     // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
     		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(), // 登录名
     				user.getPassWord(), // 密码
     				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
     				getName() // realm name
     		);

     		return authenticationInfo;
/*        return new SimpleAuthenticationInfo(user, user.getPassWord(), this.getClass().getName());// 放入shiro.调用CredentialsMatcher检验密码  
*/  
    }  
}  