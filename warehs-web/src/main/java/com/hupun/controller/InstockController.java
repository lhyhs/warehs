package com.hupun.controller;

import com.hupun.demo.Interface.InstockService;
import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Instock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/consumer/instock")
public class InstockController extends BaseController{
    @Autowired
    private InstockService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Instock> list(@RequestParam("eid") String eid, HttpSession session) {
        return this.service.list(getEid(session));
    }

    @RequestMapping(value = "/indetial", method = RequestMethod.GET)
    public List<Inoutdetial> indetial(@RequestParam(value = "id") String id) {
        return service.indetial(id);
    }

    @RequestMapping(value = "/searchin", method = RequestMethod.GET)
    public List<Instock> searchin(@RequestParam(value = "sdate") String sdate, @RequestParam(value = "edate") String edate, @RequestParam(value = "code") String code, @RequestParam(value = "note") String note,@RequestParam(value = "sid") String sid,@RequestParam("eid") String eid, HttpSession session) {
        return service.searchin(sdate, edate, code, note,sid,getEid(session));

    }
    @RequestMapping(value = "/addin", method = RequestMethod.POST)
    @ResponseBody
    public boolean addin(@RequestParam(value = "inid", required = false) String inid, @RequestParam(value = "incode", required = false) String incode, @RequestParam(value = "sid", required = false) String sid, @RequestParam(value = "sname", required = false) String sname,
                          @RequestParam(value = "innumb", required = false) Double innumb, @RequestParam(value = "inpri", required = false) Double inpri, @RequestParam(value = "innote", required = false) String innote,
                          @RequestParam(value = "indate", required = false) String indate, @RequestParam(value = "inrecname", required = false) String inrecname, @RequestParam(value = "inconumb", required = false) String inconumb,
                          @RequestParam(value = "indetial", required = false) String indetial) {

        inid = UUID.randomUUID().toString().replace("-", "").toLowerCase();


        return service.addin(inid, incode, sid, sname, innumb, inpri, innote, indate, inrecname, inconumb, indetial);
    }
}
