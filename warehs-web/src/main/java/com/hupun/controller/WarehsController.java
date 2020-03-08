package com.hupun.controller;


import com.hupun.demo.Interface.WarehsService;
import com.hupun.demo.entity.HpWarehs;
import com.hupun.demo.entity.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理仓库请求的web层
 */
@RestController
public class WarehsController extends BaseController{

    @Autowired
    private WarehsService warehsService;

    //查询员工所有的仓库列表
    @RequestMapping(value = "/stock/showStockList",method = RequestMethod.GET)
    public List<HpWarehs> showWarehsList(@RequestParam("eid") String eid,HttpSession session){
        eid = (String) session.getAttribute("StuffId");
        System.out.println(eid);
        return warehsService.showWhsList(eid);
    }

    //查询所有的仓库列表
    @RequestMapping(value = "/stock/stockList",method = RequestMethod.GET)
    public List<HpWarehs> showWarehs(){
        return warehsService.showWarehs();
    }

    //根据对应条件查询仓库
    @RequestMapping(value = "/stock/searchWhsList",method = RequestMethod.GET)
    public List<HpWarehs> SearchWarehsList(@RequestParam("code") String code,@RequestParam("name") String name,@RequestParam("statu") Integer statu,@RequestParam("eid") String eid,HttpSession session){
        return warehsService.WarehsSearchList(code,name,statu,(String)session.getAttribute("StuffId"));
    }

    //插入仓库
    @RequestMapping(value = "/stock/insertStock", method = RequestMethod.POST)
    public JsonResult<Void> warehsInsert(@RequestParam String code,
                                         @RequestParam String name,
                                         @RequestParam String contacts,
                                         @RequestParam String phone,
                                         @RequestParam String address){
        warehsService.warehsInsertStock(code,name,contacts,phone,address);
        return new JsonResult<>(SUCCESS,"插入成功");
    }

    //修改仓库信息
    @RequestMapping(value = "/stock/updateStock",method = RequestMethod.POST)
    public JsonResult<Void> warehsUpdate(@RequestParam("id") String id,
                                         @RequestParam("name") String name,
                                         @RequestParam("contacts") String contacts,
                                         @RequestParam("phone") String phone,
                                         @RequestParam("address") String address){
        warehsService.updateStock(id,name,contacts,phone,address);
        return new JsonResult<>(SUCCESS,"更新成功");
    }

    //修改仓库状态，启用或停用
    @RequestMapping(value = "/stock/changeWhsStatu",method = RequestMethod.GET)
    public JsonResult<Void> changeStatu(@RequestParam("id") String id, @RequestParam("statu") Integer statu){
        warehsService.changeWhsStatu(id,statu);
        return new JsonResult<>(SUCCESS,"已更改");
    }

    //判断商品编码是否已经存在
    @RequestMapping(value = "/stock/findByCode",method = RequestMethod.GET)
    public JsonResult<Void> findByCode(@RequestParam("code") String code){
        return warehsService.findByCode(code);
    }

    @RequestMapping(value = "/stock/findWhsName" , method = RequestMethod.GET)
    public List<String> findWhsName(){
        List<String> list = new ArrayList<>();
        String stock1 = "仓库1";
        String stock2 = "仓库2";
        String stock3 = "仓库3";
        list.add(stock1);
        list.add(stock2);
        list.add(stock3);
        return list;
    }

    @RequestMapping(value = "/stock/getWhsName" , method = RequestMethod.GET)
    public List<String> getWhsName(@RequestParam("phone") String phone){
        List<String> list = new ArrayList<>();
        String stock2 = "仓库2";
        String stock3 = "仓库3";
        list.add(stock2);
        list.add(stock3);
        System.out.println("phone:"+phone);
        return list;
    }




//    @Autowired
//    private WarehsClientService service;
//
//    @RequestMapping(value = "/consumer/warehs/get/{id}")
//    public Warehs get(@PathVariable("id") int id)
//    {
//        return this.service.get(id);
//    }
//
//    @RequestMapping(value = "/consumer/warehs/list", method = RequestMethod.GET)
//    public List<Warehs> list()
//    {
//        List<Warehs> list = new ArrayList<>();
//        Warehs w = new Warehs("11","11","11","11");
//        w.setSno(11);
//
//        list.add(w);
//        return list;
//    }
//
//    @RequestMapping(value = "/consumer/warehs/add")
//    public Object add(Warehs dept)
//    {
//        return this.service.add(dept);
//    }
}
