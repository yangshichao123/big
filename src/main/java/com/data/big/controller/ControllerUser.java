package com.data.big.controller;

import com.data.big.mapper.TokenMapper;
import com.data.big.mapper.WxjhMapper;
import com.data.big.model.*;
import com.data.big.service.Service;
import com.data.big.service.ServiceUser;
import com.data.big.token.TokenTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/controllerUser")
@CrossOrigin
public class ControllerUser {


    @Autowired
    private ServiceUser serviceUser;


    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, User user) {
        Map<String,Object> map = new HashMap<>();
        if (user == null || StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
            map.put("status", 1);
            map.put("message", "参数错误");
            return map;
        }
        Map<String,Object> login = serviceUser.login(request, user);

        return login;
    }

    @RequestMapping("/getToken")
    @ResponseBody
    public Map<String,Object> getToken(User user) {
        Map<String,Object> map = new HashMap<>();
        if (user == null || StringUtils.isEmpty(user.getId())) {
            map.put("status", 1);
            map.put("message", "参数错误");
            return map;
        }

        Map<String,Object> login = serviceUser.getToken(user);

        return login;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Map<String,Object> addUser(User user) {
        Map<String,Object> map = new HashMap<>();
        if (user == null || user.getAccount() == null || user.getPassword() == null || user.getJobId() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }
        return serviceUser.addUser(user);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,Object> deleteUser(User user) {
        Map<String,Object> map = new HashMap<>();
        if (user == null || user.getId() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }
        return serviceUser.deleteUser(user);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Map<String,Object> updateUser(User user) {
        Map<String,Object> map = new HashMap<>();
        if (user == null || user.getId() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }
        return serviceUser.updateUser(user);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Map<String,Object> getUser(User user) {
        Map<String,Object> map = new HashMap<>();
        if (user == null) {
            user = new User();
        }
        return serviceUser.getUser(user);
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public Map<String,Object> addRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        if (role == null || role.getName() == null || role.getModularIds() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }
        List<Integer> modularIds = role.getModularIds();
        for (Integer modularId : modularIds) {
            if (role.getJurisdiction() == null) {
                role.setJurisdiction(modularId + "");
            } else {
                role.setJurisdiction(role.getJurisdiction() + ";" + modularId);
            }
        }
        return serviceUser.addRole(role);
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public Map<String,Object> deleteRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        if (role == null || role.getId() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }

        return serviceUser.deleteRole(role);
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public Map<String,Object> updateRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        if (role == null || role.getId() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }
        List<Integer> modularIds = role.getModularIds();
        for (Integer modularId : modularIds) {
            if (role.getJurisdiction() == null) {
                role.setJurisdiction(modularId + "");
            } else {
                role.setJurisdiction(role.getJurisdiction() + ";" + modularId);
            }
        }
        return serviceUser.updateRole(role);
    }

    @RequestMapping("/getRole")
    @ResponseBody
    public Map<String,Object> getRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        if (role == null) {
            role = new Role();
        }
        return serviceUser.updateRole(role);
    }

    @RequestMapping("/addModular")
    @ResponseBody
    public Map<String,Object> addModular(Modular modular) {
        Map<String,Object> map = new HashMap<>();
        if (modular == null || modular.getName() == null || modular.getCode() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }
        return serviceUser.addModular(modular);
    }

    @RequestMapping("/deleteModular")
    @ResponseBody
    public Map<String,Object> deleteModular(Modular modular) {
        Map<String,Object> map = new HashMap<>();
        if (modular == null || modular.getId() == null) {
            map.put("status", 1);
            map.put("message", "参数错误");
        }
        return serviceUser.deleteModular(modular);
    }

    @RequestMapping("/getModular")
    @ResponseBody
    public Map<String,Object> getModular(Modular modular) {
        Map<String,Object> map = new HashMap<>();
        if (modular == null) {
            modular = new Modular();
        }
        return serviceUser.deleteModular(modular);
    }
}
