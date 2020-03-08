package com.hupun.demo.Interface;

import com.hupun.demo.entity.HpStuff;
import com.hupun.demo.entity.JsonResult;
import com.hupun.demo.entity.Vo.FindStuffMenuVO;
import com.hupun.demo.entity.Vo.FindStuffWhsVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工对应操作的接口
 */
@FeignClient(value = "MICROSERVICECLOUD-CONFIG-SERVICE-CLIENT")
public interface StuffService {
    /**
     * 通过用户号码查询用户是否存在，存在则返回
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult<HpStuff> login(@RequestParam("phone") String phone, @RequestParam("password") String password);

    /**
     * 查看员工列表
     * @return
     */
    @RequestMapping(value = "stuffList",method = RequestMethod.GET)
    List<HpStuff> showStuffList();

    /**
     * 通过员工账号和员工姓名模糊查询员工
     * @param stuffphone
     * @param stuffname
     * @return
     */
    @RequestMapping(value="searchList",method = RequestMethod.GET)
    List<HpStuff> findBySearchList(@RequestParam("stuffphone") String stuffphone,@RequestParam("stuffname") String stuffname);

    /**
     * 根据员工的状态查询员工列表
     * @param statu
     * @return
     */
    @RequestMapping(value = "findbystatu", method = RequestMethod.GET)
    public List<HpStuff> findStuffByStatu(@RequestParam("statu") Integer statu);

    /**
     * 修改用户状态，启用或停用
     */
    @RequestMapping(value = "changeStatu",method = RequestMethod.GET)
    public JsonResult<Void> changeStuffStatu(@RequestParam("statu") Integer statu,@RequestParam("stuffphone") String phone);

    /**
     * 插入员工
     * @param phone
     * @param password
     * @param name
     * @param remarks
     * @param chooseData
     * @param menuchooseData
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public JsonResult<Void> addStuff(@RequestParam("phone") String phone,
                                     @RequestParam("password") String password,
                                     @RequestParam("name") String name,
                                     @RequestParam("remarks") String remarks,
                                     @RequestParam("chooseData") String chooseData,
                                     @RequestParam("menuchooseData") String menuchooseData);

    /**
     * 根据用户id查询对应的仓库
     * @param eid
     * @return
     */
    @RequestMapping(value = "/choosed",method = RequestMethod.GET)
    public List<FindStuffWhsVO> findChoosedWhsList(@RequestParam("eid") String eid);

    /**
     * 查询员工对应的菜单权限
     * @param eid
     * @return
     */
    @RequestMapping(value = "/choosedMenu",method = RequestMethod.GET)
    List<FindStuffMenuVO> findChooseMenuList(@RequestParam("eid") String eid);

    /**
     * 修改员工信息
     * @param eid
     * @param password
     * @param name
     * @param remarks
     * @param chooseData
     * @param menuchooseData
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<Void> updateStuffMessage(@RequestParam("eid") String eid,
                                        @RequestParam("password") String password,
                                        @RequestParam("name") String name,
                                        @RequestParam("remarks") String remarks,
                                        @RequestParam("chooseData") String chooseData,
                                        @RequestParam("menuchooseData") String menuchooseData);

    /**
     * 查看当前员工对应的仓库列表吧
     * @param eid
     * @return
     */
    @RequestMapping(value = "/current",method = RequestMethod.GET)
    public List<FindStuffWhsVO> findStuddWhsVO(@RequestParam("eid") String eid);

    /**
     * 判断员工是否已经存在
     */
    @RequestMapping(value = "/findByphone",method = RequestMethod.GET)
    public JsonResult<Void> findByPhone(@RequestParam("phone") String phone);

}
