package com.hupun.demo.Interface;

import com.hupun.demo.entity.HpWarehs;
import com.hupun.demo.entity.JsonResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 仓库对应操作的接口
 */
@FeignClient(value = "microservicecloud-config-service-client")
public interface WarehsService {
    //查询所有的仓库列表
    @RequestMapping(value = "showStockList",method = RequestMethod.GET)
    public List<HpWarehs> showWhsList(@RequestParam("eid") String eid);
    //根据对应条件查询仓库
    @RequestMapping(value = "searchWhsList",method = RequestMethod.GET)
    public List<HpWarehs> WarehsSearchList(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("statu") Integer statu,@RequestParam("eid") String eid);
    //插入仓库
    @RequestMapping(value = "/insertStock",method = RequestMethod.POST)
    public JsonResult<Void> warehsInsertStock(@RequestParam("code") String code,
                                         @RequestParam("name") String name,
                                         @RequestParam("contacts") String contacts,
                                         @RequestParam("phone") String phone,
                                         @RequestParam("address") String address);
    //修改仓库信息
    @RequestMapping(value = "/updateStock",method = RequestMethod.POST)
    public JsonResult<Void> updateStock(@RequestParam("id") String id,
                                        @RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("address") String address);
    //修改仓库状态，启用或停用
    @RequestMapping(value = "/changeWhsStatu",method = RequestMethod.GET)
    public JsonResult<Void> changeWhsStatu(@RequestParam("id") String id, @RequestParam("statu") Integer statu);
    //查看全部的仓库
    @RequestMapping(value = "/stock/stockList",method = RequestMethod.GET)
    public List<HpWarehs> showWarehs();

    //判断商品编码是否已经存在
    @RequestMapping(value = "/findByCode",method = RequestMethod.GET)
    public JsonResult<Void> findByCode(@RequestParam("code") String code);

}
