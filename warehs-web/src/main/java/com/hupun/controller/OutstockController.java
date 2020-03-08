package com.hupun.controller;

import com.hupun.demo.Interface.OutstockService;
import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Outstock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/consumer/outstock")
public class OutstockController extends BaseController{
    @Autowired
    private OutstockService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Outstock> list(@RequestParam("eid") String eid, HttpSession session) {
        return this.service.list(getEid(session));
    }

    @RequestMapping(value = "/outdetial", method = RequestMethod.GET)
    public List<Inoutdetial> outdetial(@RequestParam(value = "id") String id) {
        return service.outdetial(id);
    }

    @RequestMapping(value = "/searchout", method = RequestMethod.GET)
    public List<Outstock> searchout(@RequestParam(value = "sdate") String sdate, @RequestParam(value = "edate") String edate, @RequestParam(value = "code") String code, @RequestParam(value = "note") String note, @RequestParam(value = "sid") String sid,@RequestParam("eid") String eid, HttpSession session) {
        return service.searchout(sdate, edate, code, note, sid, getEid(session));

    }

    @RequestMapping(value = "/addout", method = RequestMethod.POST)
    @ResponseBody
    public boolean addout(@RequestParam(value = "outid", required = false) String outid, @RequestParam(value = "outcode", required = false) String outcode, @RequestParam(value = "sid", required = false) String sid, @RequestParam(value = "sname", required = false) String sname,
                          @RequestParam(value = "outnumb", required = false) Double outnumb, @RequestParam(value = "outpri", required = false) Double outpri, @RequestParam(value = "outnote", required = false) String outnote,
                          @RequestParam(value = "outdate", required = false) String outdate, @RequestParam(value = "outrecname", required = false) String outrecname, @RequestParam(value = "outrectel", required = false) String outrectel, @RequestParam(value = "outrecaddr", required = false) String outrecaddr,
                          @RequestParam(value = "outdetial", required = false) String outdetial) {

        outid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return service.addout(outid, outcode, sid, sname, outnumb, outpri, outnote, outdate, outrecname, outrectel, outrecaddr, outdetial);
    }
}
