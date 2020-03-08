package com.hupun.controller;

import com.hupun.demo.Interface.StuffService;
import com.hupun.demo.entity.HpStuff;
import com.hupun.demo.entity.JsonResult;
import com.hupun.demo.entity.MenuAuthority;
import com.hupun.demo.entity.Vo.FindStuffMenuVO;
import com.hupun.demo.entity.Vo.FindStuffWhsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理员工请求的web层
 */
@RestController
@RequestMapping("stuff")
public class StuffController extends BaseController{

    @Autowired
    private StuffService service;

    //员工登陆
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult<HpStuff> login(@RequestParam String phone, @RequestParam String password,HttpSession session){
        JsonResult<HpStuff> stuff = service.login(phone,password);
        if(stuff.getData()!=null){
            session.setAttribute("user",stuff.getData());
            session.setAttribute("StuffId",stuff.getData().getEid());
            session.setAttribute("StuffPhone",stuff.getData().getPhone());
            session.setAttribute("MenuList",stuff.getData().getMenuList());
        }
        return stuff;
    }

    //员工退出登录
    @RequestMapping(value = "loginOut",method = RequestMethod.GET)
    public JsonResult<Void> loginOut(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("StuffId");
        session.removeAttribute("StuffPhone");
        session.removeAttribute("MenuList");
        return new JsonResult<>(SUCCESS,"已退出");
    }

    //查询所有的员工
    @RequestMapping(value = "stuffList",method = RequestMethod.GET)
    public List<HpStuff> showStuffList(){
       return service.showStuffList();
    }

    //根据输入的条件查询员工
    @RequestMapping(value="searchList",method = RequestMethod.GET)
    public List<HpStuff> searchList(@RequestParam("stuffphone") String stuffphone,@RequestParam("stuffname") String stuffname){

        return service.findBySearchList(stuffphone,stuffname);
    }

    //根据选中的状态查询对应的员工
    @RequestMapping(value = "findbystatu", method = RequestMethod.GET)
    public List<HpStuff> findbystatu(@RequestParam("statu") Integer statu){
        if(statu == 0 || statu == 1){
            return service.findStuffByStatu(statu);
        }else{
            return service.showStuffList();
        }

    }

    //修改用户状态，启用或停用
    @RequestMapping(value = "changeStatu",method = RequestMethod.GET)
    public JsonResult<Void> changeStatu(@RequestParam("statu") Integer statu,@RequestParam("stuffphone") String phone){
        System.out.println("statu:"+statu);
        JsonResult<Void> jsonResult = service.changeStuffStatu(statu,phone);
        return jsonResult;
    }

    //插入员工
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public JsonResult<Void> insertStuff(@RequestParam("phone") String phone,
                                        @RequestParam("password") String password,
                                        @RequestParam("name") String name,
                                        @RequestParam("remarks") String remarks,
                                        @RequestParam("chooseData") String chooseData,
                                        @RequestParam("menuchooseData") String menuchooseData){
        System.out.println(chooseData);
        return service.addStuff(phone,password,name,remarks,chooseData,menuchooseData);
    }

    //查看员工的菜单那权限
    @RequestMapping(value = "/userType" , method = RequestMethod.GET)
    public List userType(HttpSession session){
        List<MenuAuthority> menuList = getMenuAuthority(session);
        List<Integer> Ilist = new ArrayList<>();
        if(menuList!=null){
            for (MenuAuthority menu : menuList) {
                Ilist.add(menu.getNumber());
            }
        }
        return Ilist;
    }

    //查询员工对应的仓库列表吧
    @RequestMapping(value = "/choosed",method = RequestMethod.GET)
    public List<FindStuffWhsVO> findChoosedWhs(@RequestParam("eid") String eid){
        return service.findChoosedWhsList(eid);
    }

    //查看当前员工对应的仓库列表吧
    @RequestMapping(value = "/current",method = RequestMethod.GET)
    public List<FindStuffWhsVO> findStuffWhsVOList(@RequestParam("eid") String eid,HttpSession session){
        eid = getEid(session);
        return service.findStuddWhsVO(eid);
    }

    //查询员工对应的菜单列表
    @RequestMapping(value = "/choosedMenu",method = RequestMethod.GET)
    public List<FindStuffMenuVO> findChoosedMenu(@RequestParam("eid") String eid){
        return service.findChooseMenuList(eid);
    }

    //修改用户信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<Void> updateStuff(@RequestParam("eid") String eid,
                                        @RequestParam("password") String password,
                                        @RequestParam("name") String name,
                                        @RequestParam("remarks") String remarks,
                                        @RequestParam("chooseData") String chooseData,
                                        @RequestParam("menuchooseData") String menuchooseData){
        return service.updateStuffMessage(eid,password,name,remarks,chooseData,menuchooseData);
    }

    //获取session里的员工
    @RequestMapping(value = "/findSessionStuff",method = RequestMethod.GET)
    public HpStuff findSessionStuff(HttpSession session){
        HpStuff stuff = (HpStuff) session.getAttribute("user");
        stuff.setMenuList((List<MenuAuthority>) session.getAttribute("MenuList"));
        return stuff;
    }

    //判断员工是否已经存在
    @RequestMapping(value = "/findByphone",method = RequestMethod.GET)
    public JsonResult<Void> findByPhone(@RequestParam("phone") String phone){
        return service.findByPhone(phone);
    }


}
