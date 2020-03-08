package com.hupun.demo.service.impl;

import com.hupun.demo.entity.JsonResult;
import com.hupun.demo.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 实现类处理异常的基类
 */
@RestControllerAdvice
public class BaseServiceImpl {
    /**
     * 操作成功时的状态吗
     */
    static final int SUCCESS = 2000;
    @ExceptionHandler({BaseException.class})
    //创建响应结果对象
    public JsonResult<Void> HandleException(Throwable e){
        JsonResult<Void> jsonResult = new JsonResult<>(e);
        if(e instanceof BaseException){
            jsonResult.setStatus(4000);
        }if(e instanceof StuffNotFoundException){
            //员工不存在异常
            jsonResult.setStatus(4001);
        }if(e instanceof NoAuthorityException){
            //员工没有菜单权限异常
            jsonResult.setStatus(4002);
        }if(e instanceof PasswordNotMatchException){
            //密码错误异常
            jsonResult.setStatus(4003);
        }if(e instanceof UpdateStuffStauException){
            //员工状态被禁用异常
            jsonResult.setStatus(4004);
        }if(e instanceof AddStuffException){
            //添加员工异常
            jsonResult.setStatus(4005);
        }if(e instanceof StuffStatuException){
            jsonResult.setStatus(4006);
        }if(e instanceof UpdateStuffException){
            //修改员工信息异常
            jsonResult.setStatus(4007);
        }if(e instanceof  UpdateWhStatuException){
            //修改仓库状态异常
            jsonResult.setStatus(4008);
        }if(e instanceof  AddStockException){
            //添加仓库异常
            jsonResult.setStatus(4009);
        }if(e instanceof UpdateStockException){
            //修改仓库异常
            jsonResult.setStatus(4010);
        }if(e instanceof UpdateGoodsStatuException){
            //修改商品状态异常
            jsonResult.setStatus(4011);
        }if(e instanceof  AddGoodsException){
            //添加商品异常
            jsonResult.setStatus(4012);
        }if (e instanceof UpdateGoodsException){
//            修改商品异常
            jsonResult.setStatus(4013);
        }if(e instanceof GoodsAlreadyException){
            //商品已存在异常
            jsonResult.setStatus(4014);
        }if(e instanceof WarehsAlreadyException){
            //仓库已存在异常
            jsonResult.setStatus(4015);
        }if(e instanceof StuffAlreadyException){
            //员工已存在异常
            jsonResult.setStatus(4016);
        }

        return jsonResult;
    }
}
