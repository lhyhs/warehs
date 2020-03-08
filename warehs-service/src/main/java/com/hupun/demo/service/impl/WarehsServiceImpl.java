package com.hupun.demo.service.impl;


import com.hupun.demo.dao.StuffDao;
import com.hupun.demo.dao.WarehsDao;
import com.hupun.demo.dao.WhsAuthorityDao;
import com.hupun.demo.entity.*;
import com.hupun.demo.entity.Vo.SearchWhsVO;
import com.hupun.demo.service.WarehsService;
import com.hupun.demo.service.ex.AddStockException;
import com.hupun.demo.service.ex.UpdateStockException;
import com.hupun.demo.service.ex.UpdateWhStatuException;
import com.hupun.demo.service.ex.WarehsAlreadyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 仓库实现类
 */
@RestController
public class WarehsServiceImpl extends BaseServiceImpl{
    @Autowired
    private WarehsDao dao;

    @Autowired
    private WhsAuthorityDao wdao;

    @Autowired
    private StuffDao sdao;

    //查看全部的仓库
    @RequestMapping(value = "/stock/stockList",method = RequestMethod.GET)
    public List<HpWarehs> showWarehs(){
        return dao.findAll();
    }

    //根据员工id多表查询员工管理的对应的仓库
    @RequestMapping(value = "showStockList",method = RequestMethod.GET)
    public List<HpWarehs> showWhsList(@RequestParam("eid") String eid){
        return dao.showWarehsList(eid);
    }

    //根据对应条件查询仓库
    @RequestMapping(value = "searchWhsList",method = RequestMethod.GET)
    public List<HpWarehs> WarehsSearchList(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("statu") Integer statu,@RequestParam("eid") String eid){
        return dao.findWhsSearchList( new SearchWhsVO().setCode(code).setName(name).setStatu(statu).setEid(eid));
    }

    //修改仓库状态，启用或停用
    @RequestMapping(value = "/changeWhsStatu",method = RequestMethod.GET)
    public JsonResult<Void> changeWhsStatu(@RequestParam("id") String id, @RequestParam("statu") Integer statu){
        Integer updateStatu = dao.updateWhsStatu(new HpWarehs().setId(id).setStatu(statu));
        if(updateStatu == 0){
            throw new UpdateWhStatuException("修改状态失败");
        }
        return new JsonResult<>(SUCCESS,"已更改");
    }

    //插入仓库
    @RequestMapping(value = "/insertStock",method = RequestMethod.POST)
    @Transactional
    public JsonResult<Void> warehsInsertStock(@RequestParam("code") String code,
                                              @RequestParam("name") String name,
                                              @RequestParam("contacts") String contacts,
                                              @RequestParam("phone") String phone,
                                              @RequestParam("address") String address){
        //补全数据
        //生成UUid
        String id = UUID.randomUUID().toString();
        //封装数据执行操作
        Integer addStock = dao.addWarehs(new HpWarehs().setId(id).setStatu(1).setCode(code).setName(name)
        .setContacts(contacts).setPhone(phone).setAddress(address));
        //修改管理员的仓库数量
        sdao.updateWhcount();
        //查询管理员列表
        List<HpStuff> list = sdao.findStuffByEmana(1);
        if(list != null){
            for (HpStuff stuff:list) {
                if(stuff != null){
                    //给管理员添加仓库权限
                    Integer addWhsAuthorityByEmana = wdao.addWhsAuthorityByEmana(new WhsAuthority().setId(UUID.randomUUID().toString())
                            .setWhid(id).setUid(stuff.getEid()));
                    if(addWhsAuthorityByEmana == 0 ){
                        throw new AddStockException("添加仓库异常");
                    }
                }
            }
        }
        if(addStock == 0){
            throw new AddStockException("添加仓库异常");
        }
        return new JsonResult<>(SUCCESS,"插入成功");

    }

    //修改仓库信息
    @RequestMapping(value = "/updateStock",method = RequestMethod.POST)
    public JsonResult<Void> updateStock(@RequestParam("id") String id,
                                        @RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("address") String address){
        Integer updateWhs = dao.updateWhsByid(new HpWarehs().setId(id).setName(name).setContacts(contacts).setPhone(phone).setAddress(address));
        if(updateWhs == 0){
            throw new UpdateStockException("更新失败");
        }
        return new JsonResult<>(SUCCESS,"更新成功");
    }

    //判断仓库编码是否已经存在
    @RequestMapping(value = "/findByCode",method = RequestMethod.GET)
    public JsonResult<Void> findByCode(@RequestParam("code") String code){
        HpWarehs warehs = dao.getByCode(code);
        if(warehs != null){
            throw new WarehsAlreadyException("仓库已经存在");
        }
        return new JsonResult<>(SUCCESS,"仓库不存在，可以添加");
    }


}
