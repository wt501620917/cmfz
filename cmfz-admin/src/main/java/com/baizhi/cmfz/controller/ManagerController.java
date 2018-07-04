package com.baizhi.cmfz.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 王同 on 2018/7/4.
 */
@Controller
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/login")
    public String login(@ModelAttribute("mgrName") String mgrName,@ModelAttribute("mgrPwd") String mgrPwd, HttpSession session, ModelMap map) throws IOException {
        Manager manager = managerService.queryManagerByMgrName(mgrName,mgrPwd);
        map.addAttribute("manager",manager);
        session.setAttribute("manager",manager);
        if(manager != null){
            return "index";
        }
        return "error";
    }
    /*public void login(String mgrName,String mgrPwd,HttpServletResponse response) throws IOException {
        Manager manager = managerService.queryManagerByMgrName(mgrName,mgrPwd);
        String jsonStr = JSON.toJSONString(manager);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println(jsonStr);

        out.flush();
    }*/

    @RequestMapping("register")
    public String register(Manager manager) {

        int result = managerService.addManager(manager);
        if(result != 0){
            return "login";
        }
        return "error";
    }


    public ManagerService getManagerService() {
        return managerService;
    }

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }
}
