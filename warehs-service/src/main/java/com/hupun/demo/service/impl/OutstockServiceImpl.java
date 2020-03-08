package com.hupun.demo.service.impl;

import com.hupun.demo.Interface.OutstockService;
import com.hupun.demo.dao.OutstockDao;
import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Outstock;
import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class OutstockServiceImpl {

    @Autowired
    private OutstockDao dao;


    @RequestMapping(value = "/outstock/list", method = RequestMethod.GET)
    public List<Outstock> list(@RequestParam("eid") String eid) {
        return dao.findAllOut(eid);
    }

    @RequestMapping(value = "/outdetial", method = RequestMethod.GET)
    public List<Inoutdetial> outdetial(@RequestParam(value = "id") String id) {
        return dao.outdetial(id);
    }

    @RequestMapping(value = "/searchout", method = RequestMethod.GET)
    public List<Outstock> searchout(@RequestParam(value = "sdate") String sdate, @RequestParam(value = "edate") String edate, @RequestParam(value = "code") String code, @RequestParam(value = "note") String note, @RequestParam(value = "sid") String sid,@RequestParam("eid") String eid) {
        return dao.searchout(sdate, edate, code, note, sid,eid);
    }

    @RequestMapping(value = "/addout", method = RequestMethod.POST)
    @Transactional
    public boolean addout(@RequestParam(value = "outid", required = false) String outid, @RequestParam(value = "outcode", required = false) String outcode, @RequestParam(value = "sid", required = false) String sid, @RequestParam(value = "sname", required = false) String sname,
                          @RequestParam(value = "outnumb", required = false) Double outnumb, @RequestParam(value = "outpri", required = false) Double outpri, @RequestParam(value = "outnote", required = false) String outnote,
                          @RequestParam(value = "outdate", required = false) String outdate, @RequestParam(value = "outrecname", required = false) String outrecname, @RequestParam(value = "outrectel", required = false) String outrectel,
                          @RequestParam(value = "outrecaddr", required = false) String outrecaddr, @RequestParam(value = "outdetial", required = false) String outdetial) {
        Outstock outstock = new Outstock(outid, outcode, sid, sname, outnumb, outpri, outnote, outdate, outrecname, outrectel, outrecaddr);
        Boolean pd;
        if (dao.addout(outstock)) {
            pd = true;
        } else {
            pd = false;
        }
        String[] split = outdetial.split(",");
        List<Inoutdetial> inoutdetials = new ArrayList<Inoutdetial>();
        List<Warehsow> warehsows = new ArrayList<Warehsow>();
        for (int i = 0; i < split.length; i++) {
            String[] doutdetial = split[i].split("&");
            String[] outd = new String[5];
            for (int j = 0; j < doutdetial.length; j++) {
                String[] dh = doutdetial[j].split("=");
                outd[j] = dh[1];

            }
            Double xshpri = 0.0;
            Double xshnumb = 0.0;
            String gid = outd[0];
            Double onumb = Double.parseDouble(outd[1]);
            Double opri = Double.parseDouble(outd[2]);
            Inoutdetial a = new Inoutdetial();
            a.setIoid(UUID.randomUUID().toString().replace("-", "").toLowerCase()).setIotype(0).setIocode(outid).setGid(outd[0]).setIonumb(onumb)
                    .setIopri(opri).setIodnote(outd[3]);
            inoutdetials.add(a);
            Storehouse sh = dao.findsh(sid, gid);
            if (sh == null) {
                xshpri -= opri;
                xshnumb -= onumb;
                Storehouse addsh = new Storehouse();
                Double avgpri;
                if(onumb != 0){
                    avgpri = opri / onumb;
                }else{
                    avgpri = 0.0;
                }
                addsh.setShid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                addsh.setSid(sid);
                addsh.setGid(gid);
                addsh.setShpri(xshpri);
                addsh.setShnumb(xshnumb);
                addsh.setShavgpri(avgpri);
                if (dao.addsh(addsh)) {
                    pd = true;
                } else {
                    pd = false;
                }
                Double xshavgpri = xshpri / xshnumb;
            } else {
                xshpri = sh.getShpri();
                xshnumb = sh.getShnumb();
                xshpri -= opri;
                xshnumb -= onumb;
                Double xshavgpri;
                if(xshnumb!=0){
                    xshavgpri = div(xshpri,xshnumb,2);
                }else{
                    xshavgpri = 0.0;
                }
                System.out.println("xshavgpri-----"+xshavgpri);
                if (dao.updatesh(xshnumb, xshpri, xshavgpri, sid, gid)) {
                    pd = true;
                } else {
                    pd = false;
                }
            }
            Warehsow b = new Warehsow();
            b.setWowid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            b.setIotype(0);
            b.setIocode(outcode);
            b.setGid(gid);
            b.setSid(sid);
            b.setIonumb(onumb);
            b.setIopri(opri);
            b.setShnumb(xshnumb);
            b.setShpri(xshpri);
            b.setIodate(outdate);
            warehsows.add(b);
        }
        for (int i = 0; i < inoutdetials.size(); i++) {
            System.out.println("inoutdetials.get(i)"+inoutdetials.get(i));
            if (dao.addoutd(inoutdetials.get(i))) {
                pd = true;
            } else {
                pd = false;
            }
        }
        for (int i = 0; i < warehsows.size(); i++) {
            if (dao.addow(warehsows.get(i))) {
                pd = true;
            } else {
                pd = false;
            }
        }
        return pd;
    }
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
