package com.hupun.demo.dao;

import com.hupun.demo.entity.Inoutdetial;
import com.hupun.demo.entity.Instock;
import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InstockDao {
    public List<Instock> findAllIn(String eid);

    public List<Inoutdetial> indetial(String id);

    public List<Instock> searchin(@Param("sdate") String sdate, @Param("edate") String edate, @Param("code") String code, @Param("note") String note, @Param("sid") String sid, @Param("eid") String eid);

    public boolean addin(Instock instock);
    public boolean addind(Inoutdetial inoutdetial);
    public Storehouse findsh(@Param("sid") String sid, @Param("gid") String gid);
    public boolean addsh(Storehouse storehouse);
    public boolean updatesh(@Param("shnumb") Double xshnumb, @Param("shpri") Double xshpri, @Param("shavgpri") Double xshavgpri, @Param("sid") String sid, @Param("gid") String gid);
    public boolean addow(Warehsow warehsow);

}
