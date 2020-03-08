package com.hupun.controller;

import com.hupun.controller.ex.BaseException;
import com.hupun.controller.ex.UserNotFoundException;
import com.hupun.demo.entity.JsonResult;
import com.hupun.demo.entity.MenuAuthority;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理web层异常的基类
 */
@RestControllerAdvice
public class BaseController {
    /**
     * 操作成功时的状态吗
     */
    static final int SUCCESS = 2000;
    @ExceptionHandler({BaseException.class})
    //创建响应结果对象
    public JsonResult<Void> HandleException(Throwable e){
        JsonResult<Void> jsonResult = new JsonResult<>(e);
        if(e instanceof BaseException){
            jsonResult.setStatus(5000);
        }if(e instanceof UserNotFoundException){
            jsonResult.setStatus(5001);
        }
        return jsonResult;
    }
    //获取session的用户id
    public String getEid(HttpSession session){
        if(session.getAttribute("StuffId").toString()==null){
            return null;
        }
        return session.getAttribute("StuffId").toString();
    }
    //获取session中用户中的电话
    public String getEPhone(HttpSession session){
        if(session.getAttribute("StuffPhone").toString()==null){
            return null;
        }
        return session.getAttribute("StuffPhone").toString();
    }
    //获取session菜单权限列表吧
    public List<MenuAuthority> getMenuAuthority(HttpSession session){
        if((List<MenuAuthority>) session.getAttribute("MenuList")==null){
            return null;
        }
        return (List<MenuAuthority>) session.getAttribute("MenuList");
    }
}
