//package com.gsy.springboot.start.shiro;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.credential.CredentialsMatcher;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.realm.jdbc.JdbcRealm;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import java.util.LinkedHashMap;
//
///**
// * Created By Gsy on 2019/6/3
// */
//@Configuration
//public class ShiroConfig1 {
//    @Autowired
//    DruidDataSourceAutoConfigure dataSource;
//    @Bean(name="shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
//        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
//        bean.setSecurityManager(manager);
//        //配置登录的url和登录成功的url
//        bean.setLoginUrl("/login.html");
//        bean.setSuccessUrl("/");
//        //配置访问权限
//        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/jsp/login.jsp*", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/login.html", "authc"); // need to accept POSTs from the login form
//        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/error", "anon");
//        filterChainDefinitionMap.put("/druid/**", "roles[admin]");
//        filterChainDefinitionMap.put("/file/**", "roles[admin]");
//        filterChainDefinitionMap.put("/**", "anon");
//        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return bean;
//    }
//    //配置核心安全事务管理器
//    @Bean(name="securityManager")
//    public SecurityManager securityManager(Realm authRealm) {
//        System.err.println("--------------shiro已经加载----------------");
//        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
//        manager.setRealm(authRealm);
//        return manager;
//    }
//    //配置自定义的权限登录器
//
//    @Bean
//    public Realm realm() {
//        JdbcRealm jdbcRealm = new JdbcRealm();
//
//        jdbcRealm.setDataSource(dataSource.dataSource());
//        jdbcRealm.setPermissionsLookupEnabled(true);
//        String sqlQueryUserPassword = "select user_password from tb_sys_user where user_id = ?";
//        jdbcRealm.setAuthenticationQuery(sqlQueryUserPassword);
//        return jdbcRealm;
//    }
//
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
//        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(manager);
//        return advisor;
//    }
//    @ModelAttribute(name = "subject")
//    public Subject subject() {
//        Subject subject =  SecurityUtils.getSubject();
//        return subject;
//    }
//}
