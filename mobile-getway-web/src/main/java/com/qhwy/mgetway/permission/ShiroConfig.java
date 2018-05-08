package com.qhwy.mgetway.permission;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

  
@Configuration  
public class ShiroConfig {  
    /** 
     * ShiroFilterFactoryBean 处理拦截资源文件问题。 
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在 
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager 
     * 
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 
     * 3、部分过滤器可指定参数，如perms，roles 
     * 
     */  
    @Bean  
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
          
        // 必须设置 SecurityManager  
        shiroFilterFactoryBean.setSecurityManager(securityManager);  
  
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面  
        shiroFilterFactoryBean.setLoginUrl("/getway/gologin");  
        // 登录成功后要跳转的链接  
        shiroFilterFactoryBean.setSuccessUrl("/index");  
        // 未授权界面  
        shiroFilterFactoryBean.setUnauthorizedUrl("/getway/403");  
  
        // 拦截器.  
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
        // 配置不会被拦截的链接 顺序判断  
        filterChainDefinitionMap.put("/static/**", "anon");  
        filterChainDefinitionMap.put("/getway/login", "anon");  
        filterChainDefinitionMap.put("/getway/register", "anon");  
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了  
        filterChainDefinitionMap.put("/logout", "logout");  
  
        filterChainDefinitionMap.put("/add", "perms[权限添加]");  
  
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;  
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->  
        filterChainDefinitionMap.put("/**", "authc");  
  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        System.out.println("Shiro拦截器工厂类注入成功");  
        return shiroFilterFactoryBean;  
    }  
  
    @Bean  
    public SecurityManager securityManager() {  
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();  
        // 设置realm.  
        securityManager.setRealm(userRealm());  
        return securityManager;  
    }  
  
    /**  
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)  
     *   
     * @return  
     */  
    @Bean  
    public UserRealm userRealm() {  
            UserRealm userRealm=new UserRealm();  
            userRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
        userRealm.setCachingEnabled(false);  
            return  userRealm;  
         
    }  
    @Bean(name="credentialsMatcher")  
    public HashedCredentialsMatcher hashedCredentialsMatcher(){  
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
       hashedCredentialsMatcher.setHashAlgorithmName("md5");  
       hashedCredentialsMatcher.setHashIterations(2);  
       //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码  
       hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);  
  
       return hashedCredentialsMatcher;  
    }  
    /** 
     * Shiro生命周期处理器 
     * @return 
     */  
    @Bean  
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){  
        return new LifecycleBeanPostProcessor();  
    }  
    /** 
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 
     * @return 
     */  
    @Bean  
    @DependsOn({"lifecycleBeanPostProcessor"})  
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){  
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();  
        advisorAutoProxyCreator.setProxyTargetClass(true);  
        return advisorAutoProxyCreator;  
    }  
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());  
        return authorizationAttributeSourceAdvisor;  
    }  
    
} 