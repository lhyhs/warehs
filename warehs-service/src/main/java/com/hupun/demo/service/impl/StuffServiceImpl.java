package com.hupun.demo.service.impl;

import com.hupun.demo.dao.MenuAuthorityDao;
import com.hupun.demo.dao.StuffDao;
import com.hupun.demo.dao.WhsAuthorityDao;
import com.hupun.demo.entity.HpStuff;
import com.hupun.demo.entity.JsonResult;
import com.hupun.demo.entity.MenuAuthority;
import com.hupun.demo.entity.Vo.FindStuffMenuVO;
import com.hupun.demo.entity.Vo.FindStuffWhsVO;
import com.hupun.demo.entity.Vo.SearchStuffVO;
import com.hupun.demo.entity.WhsAuthority;
import com.hupun.demo.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 员工实现类
 */
@RestController
public class StuffServiceImpl extends BaseServiceImpl{
    @Autowired
    private StuffDao dao;

    @Autowired
    private WhsAuthorityDao wdao;

    @Autowired
    private MenuAuthorityDao mdao;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult<HpStuff> login(@RequestParam("phone") String phone, @RequestParam("password") String password){
        //System.out.println(phone);
        //通过用户账号查询用户是否存在
        HpStuff  stuff = dao.findByPhone(phone);
        if(stuff==null){
            //用户不存在时执行
            System.out.println("用户不存在");
            throw new StuffNotFoundException("用户不存在");
        }
        //若用户存在则进一步判断密码
        if(!stuff.getPassword().equals(password)){
            //密码错误时执行
            System.out.println("密码错误");
            throw new PasswordNotMatchException("密码错误");
        }
        //再判断用户状态
        if(stuff.getStatu() == 0){
            //用户状态为停用状态时执行
            throw new StuffStatuException("您已被禁用，请让管理员激活!");
        }
        //通过用户id查找对应的菜单权限
        List<MenuAuthority> menuList = mdao.findMenuByStuffId(stuff.getEid());
        if(menuList==null){
            //用户没有菜单权限时执行
            System.out.println("您暂时没有权限");
            throw new NoAuthorityException("您暂时没有权限");
        }
        stuff.setMenuList(menuList);
        System.out.println(menuList);
        System.out.println(stuff);
        return new JsonResult<>(SUCCESS,stuff,"登录成功");
    }

    //查看员工列表
    @RequestMapping(value = "stuffList",method = RequestMethod.GET)
    public List<HpStuff> showStuffList(){
        List<HpStuff> list = dao.findStuffList();
        return list;
    }

    //根据条件查询员工列表
    @RequestMapping(value="searchList",method = RequestMethod.GET)
    public List<HpStuff> findBySearchList(@RequestParam("stuffphone") String stuffphone,@RequestParam("stuffname") String stuffname){
        SearchStuffVO searchStuff = new SearchStuffVO(stuffphone,stuffname);
        return dao.findBySearchList(searchStuff);
    }

    /**
     * 根据员工的状态查询员工列表
     * @param statu
     * @return
     */
    @RequestMapping(value = "findbystatu", method = RequestMethod.GET)
    public List<HpStuff> findStuffByStatu(@RequestParam("statu") Integer statu){
        return dao.findStuffByStatu(statu);
    }

    /**
     * 修改用户状态，启用或停用
     */
    @RequestMapping(value = "changeStatu",method = RequestMethod.GET)
    public JsonResult<Void> changeStuffStatu(@RequestParam("statu") Integer statu,@RequestParam("stuffphone") String phone){
        HpStuff stuff = new HpStuff().setPhone(phone).setStatu(statu);
        Integer changeStuff = dao.changeStuffStatu(stuff);
        if(changeStuff==0){
            throw new UpdateStuffStauException("系统异常，修改失败");
        }
        return new JsonResult<>(SUCCESS,"修改状态成功");
    }

    //添加员工
    @Transactional
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public JsonResult<Void> addStuff(@RequestParam("phone") String phone,
                                     @RequestParam("password") String password,
                                     @RequestParam("name") String name,
                                     @RequestParam("remarks") String remarks,
                                     @RequestParam("chooseData") String chooseData,
                                     @RequestParam("menuchooseData") String menuchooseData){
        //补全数据
        //员工id
        String eid = UUID.randomUUID().toString();
        //管理员权限默认为0
        Integer emana = 0;
        //获取仓库管理的仓库数量
        Integer whcount = chooseData.startsWith("ALL_SELECT")?chooseData.split(",").length-1:chooseData.split(",").length;
        //员工状态默认为1表示启用
        Integer statu = 1;
        //封装数据
        HpStuff stuff = new HpStuff().setEid(eid).setName(name).setPhone(phone).setPassword(password).setEmana(emana)
                .setWhcount(whcount).setStatu(statu).setRemarks(remarks);
        //执行插入
        Integer insert = dao.insertStuff(stuff);
        if(insert==0){
            throw new AddStuffException("添加员工失败");
        }
        //对选中的仓库权限字符串进行切分
        String [] stringWa = chooseData.startsWith("ALL_SELECT")?chooseData.substring(11).split(","):chooseData.split(",");
        for (int i = 0; i < stringWa.length ; i++) {
            //将该用户对应的仓库权限存入表中
            Integer addwa = wdao.addWhsAuthority(new WhsAuthority().setId(UUID.randomUUID().toString()).setUid(eid).setWhid(stringWa[i]));
            if(addwa == 0){
                throw new AddStuffException("添加员工失败");
            }
        }
        //对选中的菜单权限字符串进行切分
        String[] stringMenu = menuchooseData.startsWith("MENU_ALL_SELECT")?menuchooseData.substring(16).split(","):menuchooseData.split(",");
        for (int i = 0; i < stringMenu.length ; i++) {
            //将该用户对应的菜单权限存入表中
            Integer addma = mdao.addMenuAuthority(new MenuAuthority().setId(UUID.randomUUID().toString()).setNumber(Integer.parseInt(stringMenu[i])).setUid(eid));
            if(addma == 0){
                throw new AddStuffException("添加员工失败");
            }
        }
        return new JsonResult<>(2000,"添加成功");
    }

    //根据员工id查询仓库列表
    @RequestMapping(value = "/choosed",method = RequestMethod.GET)
    public List<FindStuffWhsVO> findChoosedWhsList(@RequestParam("eid") String eid){
        return dao.getChoosedWhsList(eid);
    }

    //根据员工id查询菜单权限列表
    @RequestMapping(value = "/choosedMenu",method = RequestMethod.GET)
    List<FindStuffMenuVO> findChooseMenuList(@RequestParam("eid") String eid){
        List<FindStuffMenuVO> list = mdao.getChoosedWhsList(eid);
        for (FindStuffMenuVO stuffMenu:list) {
            if(stuffMenu.getNumber().equals("1")){stuffMenu.setName("仓库管理");}
            if(stuffMenu.getNumber().equals("2")){stuffMenu.setName("商品管理");}
            if(stuffMenu.getNumber().equals("3")){stuffMenu.setName("库存状况");}
            if(stuffMenu.getNumber().equals("4")){stuffMenu.setName("商品出库");}
            if(stuffMenu.getNumber().equals("5")){stuffMenu.setName("商品入库");}
            if(stuffMenu.getNumber().equals("6")){stuffMenu.setName("员工管理");}
        }
        return list;
    }

    //修改员工信息
    @Transactional
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<Void> updateStuffMessage(@RequestParam("eid") String eid,
                                               @RequestParam("password") String password,
                                               @RequestParam("name") String name,
                                               @RequestParam("remarks") String remarks,
                                               @RequestParam("chooseData") String chooseData,
                                               @RequestParam("menuchooseData") String menuchooseData){
        //修改过后仓库的数量
        Integer whcount = chooseData.startsWith("ALL_SELECT")?chooseData.split(",").length-1:chooseData.split(",").length;
        HpStuff stuff = new HpStuff().setEid(eid).setPassword(password).setName(name).setRemarks(remarks).setWhcount(whcount);
        Integer stuffUpdate = dao.UpdateStuff(stuff);
        //删除仓库权限
        Integer dwh = wdao.deleteWhs(eid);
        //删除菜单权限
        Integer dmenu = mdao.deleteMenu(eid);
        if(stuffUpdate == 0 || dwh == 0 || dmenu == 0){
            throw new UpdateStuffException("修改失败");
        }
        //添加修改过后的仓库权限
        String [] stringWa = chooseData.startsWith("ALL_SELECT")?chooseData.substring(11).split(","):chooseData.split(",");
        for (int i = 0; i < stringWa.length ; i++) {
            Integer addwa = wdao.addWhsAuthority(new WhsAuthority().setId(UUID.randomUUID().toString()).setUid(eid).setWhid(stringWa[i]));
            if(addwa == 0){
                throw new UpdateStuffException("修改失败");
            }
        }
        //添加修改之后的菜单权限
        String[] stringMenu = menuchooseData.startsWith("MENU_ALL_SELECT")?menuchooseData.substring(16).split(","):menuchooseData.split(",");
        for (int i = 0; i < stringMenu.length ; i++) {
            System.out.println(stringMenu[i]);
            Integer addma = mdao.addMenuAuthority(new MenuAuthority().setId(UUID.randomUUID().toString()).setNumber(Integer.parseInt(stringMenu[i])).setUid(eid));
            if(addma == 0){
                throw new UpdateStuffException("修改失败");
            }
        }
        return new JsonResult<>(SUCCESS,"修改成功");
    }

    //查看当前员工对应的仓库列表吧
    @RequestMapping(value = "/current",method = RequestMethod.GET)
    public List<FindStuffWhsVO> findStuddWhsVO(@RequestParam("eid") String eid){
        return dao.getChoosedWhsList(eid);
    }

    //判断员工是否已经存在
    @RequestMapping(value = "/findByphone",method = RequestMethod.GET)
    public JsonResult<Void> findByPhone(@RequestParam("phone") String phone){
        HpStuff stuff = dao.findByPhone(phone);
        if(stuff != null){
            throw new StuffAlreadyException("员工已存在");
        }
        return new JsonResult<>(SUCCESS,"员工不存在，可以添加员工");
    }


    /*private String getMd5Password(String password,String salt) {
        //加密规则：将盐值拼接在密码两端
        //执行五次加密
        String str = salt + password + salt;
        for (int i = 0; i < 5; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes());
        }
        return str;
    }*/
}
