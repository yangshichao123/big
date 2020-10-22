package com.data.big.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.big.gw.GwaqscJxglService;
import com.data.big.gw.GwaqscJxglServicePortType;
import com.data.big.mapper.*;
import com.data.big.model.*;
import com.data.big.service.Service;
import com.data.big.service.ServiceNetty;
import com.data.big.service.ServiceUser;
import com.data.big.task.KeepTask;
import com.data.big.util.Properties;
import com.data.big.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@org.springframework.stereotype.Service("ServiceUser")
public class ServiceUserImpl implements ServiceUser {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ModularMapper modularMapper;


    @Override
    public Map<String,Object> login(User user) {
        Map<String,Object> map=new HashMap<>();
        User user1=new User();
        user1.setAccount(user.getAccount());
        List<User> select = userMapper.select(user1);
        if(select.size()>0){
            if(select.get(0).getPassword().equals(user.getPassword())){
                map.put("status",0);
                map.put("message","登陆成功");
                map.put("data",select.get(0));
            }else{
                map.put("status",1);
                map.put("message","密码错误");
            }
        }else {
            map.put("status",1);
            map.put("message","没有该用户");
        }

        return map;
    }
    @Override
    public Map<String,Object> addUser(User user) {
        Map<String,Object> map=new HashMap<>();
        user.setId(UUIDHelper.getUUIDStr());
        user.setCreatetime(new Date());
        int i = userMapper.insertSelective(user);
        if(i>0){
            map.put("status",0);
            map.put("message","成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteUser(User user) {
        Map<String,Object> map=new HashMap<>();
        int i = userMapper.deleteByPrimaryKey(user.getId());
        if(i>0){
            map.put("status",0);
            map.put("message","成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> updateUser(User user) {
        Map<String,Object> map=new HashMap<>();
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i>0){
            map.put("status",0);
            map.put("message","成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getUser(User user) {
        Map<String,Object> map=new HashMap<>();
        List<User> select = userMapper.select(user);
        if(select.size()>0){
            map.put("status",0);
            map.put("message","成功");
            map.put("data",select);
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> addRole(Role role) {
        Map<String,Object> map=new HashMap<>();
        role.setCreatetime(new Date());
        int i = roleMapper.insertSelective(role);
        if(i>0){
            map.put("status",0);
            map.put("message","保存成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteRole(Role role) {
        Map<String,Object> map=new HashMap<>();
        int i = roleMapper.deleteByPrimaryKey(role.getId());
        if(i>0){
            map.put("status",0);
            map.put("message","保存成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> updateRole(Role role) {
        Map<String,Object> map=new HashMap<>();
        int i = roleMapper.updateByPrimaryKeySelective(role);
        if(i>0){
            map.put("status",0);
            map.put("message","保存成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getRole(Role role) {
        Map<String,Object> map=new HashMap<>();
        List<Role> select = roleMapper.select(role);
        if(select.size()>0){
            map.put("status",0);
            map.put("message","保存成功");
            map.put("data",select);
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteModular(Modular modular) {
        Map<String,Object> map=new HashMap<>();
        int i = modularMapper.deleteByPrimaryKey(modular.getId());
        if(i>0){
            map.put("status",0);
            map.put("message","保存成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> addModular(Modular modular) {
        Map<String,Object> map=new HashMap<>();
        int i = modularMapper.insertSelective(modular);
        if(i>0){
            map.put("status",0);
            map.put("message","保存成功");
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getModular(Modular modular) {
        Map<String,Object> map=new HashMap<>();
        List<Modular> select = modularMapper.select(modular);
        if(select.size()>0){
            map.put("status",0);
            map.put("message","保存成功");
            map.put("data",select);
        }else{
            map.put("status",1);
            map.put("message","保存失败");
        }
        return map;
    }
}
