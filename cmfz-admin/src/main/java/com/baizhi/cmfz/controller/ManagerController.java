package com.baizhi.cmfz.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 王同 on 2018/7/4.
 */
@Controller
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("login")
    @ResponseBody
    public void login(String mgrName, String mgrPwd, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        Manager manager = managerService.queryManagerByMgrName(mgrName,mgrPwd);
        String jsonStr = JSON.toJSONString(manager);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println(jsonStr);

        out.flush();

    }

    @RequestMapping("register")
    @ResponseBody
    public void register(Manager manager) throws NoSuchAlgorithmException {
        managerService.addManager(manager);
    }
}
