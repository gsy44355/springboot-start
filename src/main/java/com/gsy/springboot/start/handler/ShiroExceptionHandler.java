package com.gsy.springboot.start.handler;

import com.gsy.springboot.start.util.LogUtil;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.AuthorizationException;
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
        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        LogUtil.info(this.getClass(),"AuthorizationException was thrown", e);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("status", HttpStatus.FORBIDDEN.value());
//        map.put("message", "用户权限不足");
//        model.addAttribute("errors", map);
        return "error403";
    }


}
