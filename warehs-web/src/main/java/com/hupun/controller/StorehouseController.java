package com.hupun.controller;


import com.hupun.demo.Interface.StorehouseService;

import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/consumer/storehouse")
public class StorehouseController extends BaseController{
    @Autowired
    private StorehouseService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Storehouse> list(@RequestParam("eid") String eid, HttpSession session) {
        return this.service.list(getEid(session));
    }

    @RequestMapping(value = "/shdetial", method = RequestMethod.GET)
    public List<Warehsow> shdetial(@RequestParam(value = "sid") String sid,@RequestParam(value = "gid") String gid) {
        return service.shdetial(sid,gid);
    }

    @RequestMapping(value = "/searchsh", method = RequestMethod.GET)
    public List<Storehouse> searchin(@RequestParam(value = "sid") String sid, @RequestParam(value = "gname") String gname,@RequestParam("eid") String eid, HttpSession session) {

        return service.searchsh(sid,gname,getEid(session));

    }
    @RequestMapping(value = "/searchow", method = RequestMethod.GET)
    public List<Warehsow> searchow(@RequestParam(value = "sid") String sid,@RequestParam(value = "gid") String gid,@RequestParam(value = "sdate") String sdate,@RequestParam(value = "edate") String edate) {
        return service.searchow(sid,gid,sdate,edate);
    }
}
