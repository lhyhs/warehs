package com.hupun.demo.dao;


import com.hupun.demo.entity.Storehouse;
import com.hupun.demo.entity.Warehsow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorehouseDao {
    public List<Storehouse> findAllStorehouse(String eid);

    public List<Warehsow> shdetial(@Param("sid") String sid, @Param("gid") String gid);

    public List<Storehouse> searchsh(@Param("sid") String sid, @Param("gname") String gname, @Param("eid") String eid);
    public List<Warehsow> searchow(@Param("sid") String sid, @Param("gid") String gid, @Param("sdate") String sdate, @Param("edate") String edate);

//    public boolean addin(Instock instock);

}
