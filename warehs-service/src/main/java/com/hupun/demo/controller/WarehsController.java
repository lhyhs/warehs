package com.hupun.demo.controller;


import com.hupun.demo.entity.HpWarehs;
import com.hupun.demo.entity.Warehs;
import com.hupun.demo.service.WarehsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class WarehsController {
//    @Autowired
//    private WarehsClientService service;
//
//    @RequestMapping(value="/warehs/list",method = RequestMethod.GET)
//    public List<Warehs> list(){
//        return service.list();
//    }
//    @Autowired
//    private WarehsService service;
//
//    @RequestMapping("/addStock")
//    public List<HpWarehs> addStock(){
//        List<HpWarehs> list = new ArrayList<>();
//        for (int i = 0; i < 123; i++) {
//            HpWarehs warehs = new HpWarehs();
//            String id = UUID.randomUUID().toString();
//            warehs.setId(id).setCode(i+"").setName("stock"+i).setContacts("zhangsan"+i).setPhone("18874484981").setStatu(i%2).setAddress("杭州");
//            list.add(warehs);
//        }
//        service.add(list);
//        return list;
//    }
}
