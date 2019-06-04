//package com.gsy.springboot.start.shiro;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.cache.CacheManager;
//import org.apache.shiro.cache.MemoryConstrainedCacheManager;
//import org.apache.shiro.mgt.DefaultSecurityManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.realm.jdbc.JdbcRealm;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
//import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
//import org.apache.shiro.subject.Subject;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
///**
// * Created By Gsy on 2019/6/1
// */
//@Configuration
//@ControllerAdvice
//public class ShiroConfig {
//    @Autowired
//    DruidDataSourceAutoConfigure dataSource;
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
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
//        chainDefinition.addPathDefinition("/logout", "logout");
//        chainDefinition.addPathDefinition("/error", "anon");
//        chainDefinition.addPathDefinition("/druid/**", "roles[admin]");
//        chainDefinition.addPathDefinition("/*", "anon");
//        return chainDefinition;
//    }
//
////    @ModelAttribute(name = "subject")
////    public Subject subject() {
////        Subject subject =  SecurityUtils.getSubject();
////        return subject;
////    }
//    @Bean
//    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
//        /**
//         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
//         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
//         * 加入这项配置能解决这个bug
//         */
//        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//
//}
