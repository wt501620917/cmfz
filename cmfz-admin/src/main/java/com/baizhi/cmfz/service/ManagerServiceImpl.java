package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.ManagerDAO;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.util.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

/**
 * Created by 王同 on 2018/7/4.
 */

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ManagerDAO managerDAO;

    @Transactional(readOnly = true)
    @Override
    public Manager queryManagerByMgrName(String mgrName, String mgrPwd) throws NoSuchAlgorithmException {
        Manager manager = managerDAO.selectManagerByName(mgrName);
        String password = EncryptionUtils.encryption(mgrPwd+manager.getMgrSalt());
        if(manager.getMgrSalt().equals(password)){
            return manager;
        }
        return null;
    }

    @Override
    public void addManager(Manager manager) throws NoSuchAlgorithmException {
        String salt = EncryptionUtils.getRandomSalt(6);
        manager.setMgrSalt(salt);
        manager.setMgrPwd(EncryptionUtils.encryption(manager.getMgrPwd()+salt));
        managerDAO.insertManager(manager);
    }
}
