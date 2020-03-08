package com.hupun.demo.controller;

import com.hupun.demo.entity.HpStuff;
import com.hupun.demo.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class StuffController {
//    @Autowired
//    private StuffService service;
//
//    @RequestMapping(value = "/addStuff")
//    public List<HpStuff> addStuff(){
//        List<HpStuff> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            HpStuff stuff = new HpStuff();
//            stuff.setEid(UUID.randomUUID().toString()).setName("wangwu"+i).setPhone("18874484981").setPassword("123456"+i)
//                    .setRemarks("员工").setEmana(0).setWhcount(1+i).setStatu(i%2);
//            list.add(stuff);
//        }
//        service.insertStuff(list);
//        return list;
//
//    }
}
