package com.hupun.demo.service.impl;

import com.hupun.demo.Interface.GoodsService;
import com.hupun.demo.dao.GoodsDao;
import com.hupun.demo.entity.HpGoods;
import com.hupun.demo.entity.JsonResult;
import com.hupun.demo.service.ex.AddGoodsException;
import com.hupun.demo.service.ex.GoodsAlreadyException;
import com.hupun.demo.service.ex.UpdateGoodsException;
import com.hupun.demo.service.ex.UpdateGoodsStatuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 商品实现类
 */
@RestController
public class GoodsServiceImpl extends BaseServiceImpl {
    @Autowired
    private GoodsDao dao;


    //展示仓库列表
    @RequestMapping(value = "/goodsList")
    public List<HpGoods> showGoodsList(){
        return dao.findGoodsAll();
    }
    //根据对应条件查询商品
    @RequestMapping(value = "/goods/searchList",method = RequestMethod.GET)
    public List<HpGoods> SearchGoodsList(@RequestParam("gno") String gno, @RequestParam("gname") String gname, @RequestParam("statu") Integer statu){
        return dao.findBySearchList(new HpGoods().setGno(gno).setGname(gname).setStatu(statu));
    }


    //修改商品状态，启用或停用
    @RequestMapping(value = "/changeGoodsStatu",method = RequestMethod.GET)
    public JsonResult<Void> goodsChangeStatu(@RequestParam("gid") String gid, @RequestParam("statu") Integer statu){
        Integer updateStatu = dao.updateGoodsStatu(new HpGoods().setGid(gid).setStatu(statu));
        if(updateStatu == 0 ){
            throw new UpdateGoodsStatuException("修改状态失败");
        }
        return new JsonResult<>(SUCCESS,"修改成功");
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
        //封装数据到对象中
        HpGoods goods = new HpGoods().setGid(UUID.randomUUID().toString()).setGno(gno).setGcode(gcode).setGname(gname).setStatu(1)
                .setGprice(gprice).setGtradeprice(gtradeprice).setGreferprice(greferprice).setGweight(gweight).setGvolume(gvolume);
        Integer addGoods = dao.addGoods(goods);
        if(addGoods == 0){
            throw new AddGoodsException("添加商品失败");
        }
        return new JsonResult<>(SUCCESS,"添加成功");
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
        //封装数据
        HpGoods goods = new HpGoods().setGid(gid).setGcode(gcode).setGname(gname).setGprice(gprice).setGtradeprice(gtradeprice)
                .setGreferprice(greferprice).setGweight(gweight).setGvolume(gvolume);
        System.out.println(goods.toString());
        Integer changeGoods = dao.changeGoods(goods);
        if(changeGoods == 0){
            throw new UpdateGoodsException("修改商品失败");
        }
        return new JsonResult<>(SUCCESS,"修改成功");
    }

    //判断商品编码是否已经存在
    @RequestMapping(value = "/findByGno",method = RequestMethod.GET)
    public JsonResult<Void> findByGno(@RequestParam("gno") String gno){
        HpGoods getGno = dao.getByGno(gno);
        if(getGno!=null){
            throw new GoodsAlreadyException("商品已存在");
        }
        return new JsonResult<>(SUCCESS,"商品不存在，可以添加");
    }

}
