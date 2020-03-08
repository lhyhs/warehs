package com.hupun.demo.service.impl;


import com.hupun.demo.dao.InstockDao;
import com.hupun.demo.dao.StorehouseDao;
import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Instock;
import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StorehouseServiceImpl {

    @Autowired
    private StorehouseDao dao;


    @RequestMapping(value = "/storehouse/list", method = RequestMethod.GET)
    public List<Storehouse> list(@RequestParam("eid") String eid) {
        return dao.findAllStorehouse(eid);
    }

    @RequestMapping(value = "/shdetial", method = RequestMethod.GET)
    public List<Warehsow> shdetial(@RequestParam(value = "sid") String sid, @RequestParam(value = "gid") String gid) {
        return dao.shdetial(sid, gid);
    }

    @RequestMapping(value = "/searchsh", method = RequestMethod.GET)
    public List<Storehouse> searchsh(@RequestParam(value = "sid") String sid, @RequestParam(value = "gname") String gname,@RequestParam("eid") String eid) {

        return dao.searchsh(sid, gname,eid);
    }
    @RequestMapping(value = "/searchow", method = RequestMethod.GET)
    public List<Warehsow> searchow(@RequestParam(value = "sid") String sid,@RequestParam(value = "gid") String gid,@RequestParam(value = "sdate") String sdate,@RequestParam(value = "edate") String edate) {
        return dao.searchow(sid, gid,sdate,edate);
    }


}
