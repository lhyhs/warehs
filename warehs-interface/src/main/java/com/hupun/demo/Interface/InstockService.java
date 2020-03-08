package com.hupun.demo.Interface;

import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Instock;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "MICROSERVICECLOUD-CONFIG-SERVICE-CLIENT")
public interface InstockService {
    @RequestMapping(value = "/instock/list", method = RequestMethod.GET)
    public List<Instock> list(@RequestParam("eid") String eid);

    @RequestMapping(value = "/indetial", method = RequestMethod.GET)
    public List<Inoutdetial> indetial(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/searchin", method = RequestMethod.GET)
    public List<Instock> searchin(@RequestParam(value = "sdate") String sdate, @RequestParam(value = "edate") String edate, @RequestParam(value = "code") String code, @RequestParam(value = "note") String note, @RequestParam(value = "sid") String sid,@RequestParam("eid") String eid);
    @RequestMapping(value = "/addin", method = RequestMethod.POST)
    @ResponseBody
    public boolean addin(@RequestParam(value = "inid", required = false) String inid, @RequestParam(value = "incode", required = false) String incode, @RequestParam(value = "sid", required = false) String sid, @RequestParam(value = "sname", required = false) String sname,
                         @RequestParam(value = "innumb", required = false) Double innumb, @RequestParam(value = "inpri", required = false) Double inpri, @RequestParam(value = "innote", required = false) String innote,
                         @RequestParam(value = "indate", required = false) String indate, @RequestParam(value = "inrecname", required = false) String inrecname, @RequestParam(value = "inconumb", required = false) String inconumb,
                         @RequestParam(value = "indetial", required = false) String indetial);

}
