package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Manager;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * Created by 王同 on 2018/7/4.
 */
public interface ManagerService {

    public Manager queryManagerByMgrName(String mgrName,String mgrPwd) throws NoSuchAlgorithmException;

    public void addManager(Manager manager) throws NoSuchAlgorithmException;
}
