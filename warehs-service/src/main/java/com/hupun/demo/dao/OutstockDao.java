package com.hupun.demo.dao;

import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Outstock;
import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Mapper
public interface OutstockDao {
    public List<Outstock> findAllOut(String eid);

    public List<Inoutdetial> outdetial(String id);

    public List<Outstock> searchout(@Param("sdate") String sdate, @Param("edate") String edate, @Param("code") String code, @Param("note") String note, @Param("sid") String sid, @Param("eid") String eid);
    public boolean addout(Outstock outstock);
    public boolean addoutd(Inoutdetial inoutdetial);
    public Storehouse findsh(@Param("sid") String sid, @Param("gid") String gid);
    public boolean addsh(Storehouse storehouse);
    public boolean updatesh(@Param("shnumb") Double xshnumb, @Param("shpri") Double xshpri, @Param("shavgpri") Double xshavgpri, @Param("sid") String sid, @Param("gid") String gid);
    public boolean addow(Warehsow warehsow);

}
