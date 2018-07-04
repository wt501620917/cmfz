package com.baizhi.cmfz.service;


import com.baizhi.cmfz.dao.ManagerDAO;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.util.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Manager queryManagerByMgrName(String mgrName, String mgrPwd){
        Manager manager = managerDAO.selectManagerByName(mgrName);
        String password = EncryptionUtils.encryptionCodec(mgrPwd+manager.getMgrSalt());
        if(manager.getMgrPwd().equals(password)){
            return manager;
        }
        return null;
    }

    @Override
    public int addManager(Manager manager){
        String salt = EncryptionUtils.getRandomSalt(6);
        manager.setMgrSalt(salt);
        manager.setMgrPwd(EncryptionUtils.encryptionCodec(manager.getMgrPwd()+salt));
        manager.setMgrStatus("common");
        return managerDAO.insertManager(manager);
    }
}
