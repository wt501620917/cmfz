package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Manager;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 王同 on 2018/7/4.
 */
public interface ManagerDAO {
    public Manager selectManagerById(@Param("mgrId") String mgrId);

    public Manager selectManagerByName(@Param("mgrName") String mgrName);

    public int insertManager(Manager manager);
}
