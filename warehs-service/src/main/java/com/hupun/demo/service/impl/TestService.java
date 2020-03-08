package com.hupun.demo.service.impl;

import com.hupun.demo.Interface.WarehsService;
import com.hupun.demo.dao.TestDao;
import com.hupun.demo.entity.HpWarehs;
import com.hupun.demo.entity.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.Addressing;
import java.util.List;

//@Service
//public class TestService implements WarehsService {
//    @Autowired
//    private TestDao dao;
//
//
//    @Override
//    public List<HpWarehs> showWhsList(String eid) {
//        return null;
//    }
//
//    @Override
//    public List<HpWarehs> SearchList(String stockcode, String stockname, Integer selected) {
//        return null;
//    }
//
//
//    @Override
//    public JsonResult<Void> warehsInsert(String code, String name, String contacts, String phone, String address) {
//        return null;
//    }
//
//    @Override
//    public JsonResult<Void> warehsUpdate(String code, String name, String contacts, String phone, String address) {
//        return null;
//    }
//
//    @Override
//    public JsonResult<Void> changeStatu(String code, Integer statu) {
//        return null;
//    }
//}
