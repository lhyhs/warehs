package com.hupun.demo.service.impl;


import com.hupun.demo.dao.InstockDao;
import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Instock;
import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class InstockServiceImpl {

    @Autowired
    private InstockDao dao;


    @RequestMapping(value = "/instock/list", method = RequestMethod.GET)
    public List<Instock> list(@RequestParam("eid") String eid) {
        return dao.findAllIn(eid);
    }

    @RequestMapping(value = "/indetial", method = RequestMethod.GET)
    public List<Inoutdetial> indetial(@RequestParam(value = "id") String id) {
        return dao.indetial(id);
    }

    @RequestMapping(value = "/searchin", method = RequestMethod.GET)
    public List<Instock> searchin(@RequestParam(value = "sdate") String sdate, @RequestParam(value = "edate") String edate, @RequestParam(value = "code") String code, @RequestParam(value = "note") String note,@RequestParam(value = "sid") String sid,@RequestParam("eid") String eid) {
        return dao.searchin(sdate, edate, code, note,sid,eid);
    }
    @RequestMapping(value = "/addin", method = RequestMethod.POST)
    @Transactional
    public boolean addin(@RequestParam(value = "inid", required = false) String inid, @RequestParam(value = "incode", required = false) String incode, @RequestParam(value = "sid", required = false) String sid, @RequestParam(value = "sname", required = false) String sname,
                         @RequestParam(value = "innumb", required = false) Double innumb, @RequestParam(value = "inpri", required = false) Double inpri, @RequestParam(value = "innote", required = false) String innote,
                         @RequestParam(value = "indate", required = false) String indate, @RequestParam(value = "inrecname", required = false) String inrecname, @RequestParam(value = "inconumb", required = false) String inconumb,
                         @RequestParam(value = "indetial", required = false) String indetial) {

        Instock instock = new Instock(inid, incode, sid, sname, innumb, inpri, innote, indate, inrecname, inconumb);
        Boolean pd;
        if (dao.addin(instock)) {
            pd = true;
        } else {
            pd = false;
        }
        String[] split = indetial.split(",");
        List<Inoutdetial> inoutdetials = new ArrayList<Inoutdetial>();
        List<Warehsow> warehsows = new ArrayList<Warehsow>();
        for (int i = 0; i < split.length; i++) {
            String[] dindetial = split[i].split("&");


            String[] ind = new String[5];
            for (int j = 0; j < dindetial.length; j++) {
                String[] dh = dindetial[j].split("=");
                ind[j] = dh[1];
            }
            Double xshpri = 0.0;
            Double xshnumb = 0.0;
            String gid = ind[0];
            Double onumb = Double.parseDouble(ind[1]);
            Double opri = Double.parseDouble(ind[2]);
            Inoutdetial a = new Inoutdetial();
            a.setIoid(UUID.randomUUID().toString().replace("-", "").toLowerCase()).setIotype(1).setIocode(inid).setGid(ind[0]).setIonumb(onumb)
                    .setIopri(opri).setIodnote(ind[3]);
            inoutdetials.add(a);
            Storehouse sh = dao.findsh(sid, gid);
            if (sh == null) {
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
                addsh.setShpri(opri);
                addsh.setShnumb(onumb);
                addsh.setShavgpri(avgpri);
                if (dao.addsh(addsh)) {
                    pd = true;
                } else {
                    pd = false;
                }
                xshpri += opri;
                xshnumb += onumb;
                Double xshavgpri = xshpri / xshnumb;
            } else {
                xshpri = sh.getShpri();
                xshnumb = sh.getShnumb();
                xshpri += opri;
                xshnumb += onumb;
                Double xshavgpri;
                if(xshnumb!=0){
                    xshavgpri = div(xshpri,xshnumb,2);
                }else{
                    xshavgpri = 0.0;
                }
                if (dao.updatesh(xshnumb, xshpri, xshavgpri, sid, gid)) {
                    pd = true;
                } else {
                    pd = false;
                }
            }
            Warehsow b = new Warehsow();
            b.setWowid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            b.setIotype(1);
            b.setIocode(incode);
            b.setGid(gid);
            b.setSid(sid);
            b.setIonumb(onumb);
            b.setIopri(opri);
            b.setShnumb(xshnumb);
            b.setShpri(xshpri);
            b.setIodate(indate);
            warehsows.add(b);
        }
        for (int i = 0; i < inoutdetials.size(); i++) {
            if (dao.addind(inoutdetials.get(i))) {
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
