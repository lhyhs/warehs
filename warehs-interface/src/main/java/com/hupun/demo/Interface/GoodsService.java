package com.hupun.demo.Interface;

import com.hupun.demo.entity.HpGoods;
import com.hupun.demo.entity.JsonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品对应操作的接口
 */
@FeignClient(value = "MICROSERVICECLOUD-CONFIG-SERVICE-CLIENT")
public interface GoodsService {
    //查询所有的商品列表
    @RequestMapping(value = "/goodsList")
    public List<HpGoods> showGoodsList();

    //根据对应条件查询商品
    @RequestMapping(value = "/goods/searchList",method = RequestMethod.GET)
    public List<HpGoods> SearchGoodsList(@RequestParam("gno") String gno, @RequestParam("gname") String gname, @RequestParam("statu") Integer statu);

    //插入商品
    @RequestMapping(value = "/insertGoods", method = RequestMethod.POST)
    public JsonResult<Void> GoodsInsert(@RequestParam("gno") String gno,
                                         @RequestParam("gcode") String gcode,
                                         @RequestParam("gname") String gname,
                                         @RequestParam("gprice") Double gprice,
                                         @RequestParam("gtradeprice") Double gtradeprice,
                                         @RequestParam("greferprice") Double greferprice,
                                         @RequestParam("gweight") Double gweight,
                                         @RequestParam("gvolume") Double gvolume);

    //修改商品
    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public JsonResult<Void> GoodsUpdate(@RequestParam("gid") String gid,
                                        @RequestParam("gcode") String gcode,
                                        @RequestParam("gname") String gname,
                                        @RequestParam("gprice") Double gprice,
                                        @RequestParam("gtradeprice") Double gtradeprice,
                                        @RequestParam("greferprice") Double greferprice,
                                        @RequestParam("gweight") Double gweight,
                                        @RequestParam("gvolume") Double gvolume);

    //修改商品状态，启用或停用
    @RequestMapping(value = "/changeGoodsStatu",method = RequestMethod.GET)
    public JsonResult<Void> goodsChangeStatu(@RequestParam("gid") String gid, @RequestParam("statu") Integer statu);

    //判断商品编码是否已经存在
    @RequestMapping(value = "/findByGno",method = RequestMethod.GET)
    public JsonResult<Void> findByGno(@RequestParam("gno") String gno);

}
