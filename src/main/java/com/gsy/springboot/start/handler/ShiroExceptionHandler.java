package com.gsy.springboot.start.handler;

import com.gsy.springboot.start.util.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By Gsy on 2019/6/3
 */
@ControllerAdvice
@SuppressWarnings("Duplicates")
public class ShiroExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(AuthorizationException e, Model model) {
        String exceptionCall = "HostUnauthorizedException";
        if(e instanceof UnauthenticatedException){
            exceptionCall = "未登录";
        }else if(e instanceof UnauthorizedException){
            exceptionCall = "未授权";
        }
        model.addAttribute("message",exceptionCall);
        LogUtil.info(this.getClass(),"shiro权限管理:权限异常={},登录用户名={}",exceptionCall, SecurityUtils.getSubject().getPrincipal());
        return "error403";
    }


}
