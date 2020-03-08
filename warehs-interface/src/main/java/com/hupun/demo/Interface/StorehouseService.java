package com.hupun.demo.Interface;


import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "MICROSERVICECLOUD-CONFIG-SERVICE-CLIENT")
public interface StorehouseService {
    @RequestMapping(value = "/storehouse/list", method = RequestMethod.GET)
    public List<Storehouse> list(@RequestParam("eid") String eid);

    @RequestMapping(value = "/shdetial", method = RequestMethod.GET)
    public List<Warehsow> shdetial(@RequestParam(value = "sid") String sid, @RequestParam(value = "gid") String gid);

    @RequestMapping(value = "/searchsh", method = RequestMethod.GET)
    public List<Storehouse> searchsh(@RequestParam(value = "sid") String sid, @RequestParam(value = "gname") String gname,@RequestParam("eid") String eid);
    @RequestMapping(value = "/searchow", method = RequestMethod.GET)
    public List<Warehsow> searchow(@RequestParam(value = "sid") String sid, @RequestParam(value = "gid") String gid, @RequestParam(value = "sdate") String sdate, @RequestParam(value = "edate") String edate) ;

}
