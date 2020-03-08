package com.hupun.controller;

import com.hupun.demo.Interface.GoodsService;
import com.hupun.demo.entity.HpGoods;
import com.hupun.demo.entity.HpStuff;
import com.hupun.demo.entity.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理商品请求的web层
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController extends BaseController{

    @Autowired
    private GoodsService service;

    //查询所有的商品列表
    @RequestMapping(value = "/goodsList")
    public List<HpGoods> showGoods(){
       return service.showGoodsList();
    }

    //根据对应条件查询商品
    @RequestMapping(value = "/searchList",method = RequestMethod.GET)
    public List<HpGoods> SearchGoodsList(@RequestParam("gno") String gno, @RequestParam("gname") String gname, @RequestParam("statu") Integer statu){
        return service.SearchGoodsList(gno,gname,statu);
    }

    //插入商品
    @RequestMapping(value = "/insertGoods", method = RequestMethod.POST)
    public JsonResult<Void> GoodsInsert(@RequestParam("gno") String gno,
                                         @RequestParam("gcode") String gcode,
                                         @RequestParam("gname") String gname,
                                         @RequestParam("gprice") Double gprice,
                                         @RequestParam("gtradeprice") Double gtradeprice,
                                         @RequestParam("greferprice") Double greferprice,
                                         @RequestParam("gweight") Double gweight,
                                         @RequestParam("gvolume") Double gvolume){
        service.GoodsInsert(gno,gcode,gname,gprice,gtradeprice,greferprice,gweight,gvolume);
        return new JsonResult<>(SUCCESS,"插入成功");
    }

    //修改商品
    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public JsonResult<Void> GoodsUpdate(@RequestParam("gid") String gid,
                                        @RequestParam("gcode") String gcode,
                                        @RequestParam("gname") String gname,
                                        @RequestParam("gprice") Double gprice,
                                        @RequestParam("gtradeprice") Double gtradeprice,
                                        @RequestParam("greferprice") Double greferprice,
                                        @RequestParam("gweight") Double gweight,
                                        @RequestParam("gvolume") Double gvolume){
        service.GoodsUpdate(gid,gcode,gname,gprice,gtradeprice,greferprice,gweight,gvolume);
        return new JsonResult<>(SUCCESS,"修改成功");
    }

    //修改商品状态，启用或停用
    @RequestMapping(value = "/changeGoodsStatu",method = RequestMethod.GET)
    public JsonResult<Void> changeStatu(@RequestParam("gid") String gid, @RequestParam("statu") Integer statu){
        service.goodsChangeStatu(gid,statu);
        return new JsonResult<>(SUCCESS,"已更改");
    }

    //判断商品编码是否已经存在
    @RequestMapping(value = "/findByGno",method = RequestMethod.GET)
    public JsonResult<Void> findByGno(@RequestParam("gno") String gno){
        return service.findByGno(gno);
    }

}
