package com.hupun.demo.Interface;

import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Outstock;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@FeignClient(value = "MICROSERVICECLOUD-CONFIG-SERVICE-CLIENT")
public interface OutstockService {
    @RequestMapping(value = "/outstock/list", method = RequestMethod.GET)
    public List<Outstock> list(@RequestParam("eid") String eid);

    @RequestMapping(value = "/outdetial", method = RequestMethod.GET)
    public List<Inoutdetial> outdetial(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/searchout", method = RequestMethod.GET)
    public List<Outstock> searchout(@RequestParam(value = "sdate") String sdate, @RequestParam(value = "edate") String edate, @RequestParam(value = "code") String code, @RequestParam(value = "note") String note, @RequestParam(value = "sid") String sid,@RequestParam("eid") String eid);

    @RequestMapping(value = "/addout", method = RequestMethod.POST)
    @ResponseBody
    public boolean addout(@RequestParam(value = "outid", required = false) String outid, @RequestParam(value = "outcode", required = false) String outcode, @RequestParam(value = "sid", required = false) String sid, @RequestParam(value = "sname", required = false) String sname,
                          @RequestParam(value = "outnumb", required = false) Double outnumb, @RequestParam(value = "outpri", required = false) Double outpri, @RequestParam(value = "outnote", required = false) String outnote,
                          @RequestParam(value = "outdate", required = false) String outdate, @RequestParam(value = "outrecname", required = false) String outrecname, @RequestParam(value = "outrectel", required = false) String outrectel, @RequestParam(value = "outrecaddr", required = false) String outrecaddr,
                          @RequestParam(value = "outdetial", required = false) String outdetial);
}
