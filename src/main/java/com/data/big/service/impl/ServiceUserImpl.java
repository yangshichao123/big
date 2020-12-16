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
import com.data.big.token.TokenProccessor;
import com.data.big.token.TokenTools;
import com.data.big.util.Properties;
import com.data.big.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private TokenMapper tokenMapper;


    @Override
    public Map<String,Object> login(HttpServletRequest request, User user) {

        Map<String,Object> map = new HashMap<>();
        User user1 = new User();
        user1.setAccount(user.getAccount());
        List<User> select = userMapper.select(user1);
        if (select.size() > 0) {
            if (select.get(0).getPassword().equals(user.getPassword())) {
                User user3 = select.get(0);
                map.put("status", 0);
                map.put("message", "登陆成功");
                map.put("data", user3);
                Token token = this.addToken(user3.getId());
                request.getSession().setAttribute("userId", user3.getId());
                user3.setToken(token);
            } else {
                map.put("status", 1);
                map.put("message", "密码错误");
            }
        } else {
            map.put("status", 1);
            map.put("message", "没有该用户");
        }
        log.info("用户登陆 " + map.get("message") + " 用户名：" + user.getAccount() + " 密码：" + user.getPassword());
        return map;
    }

    private Token addToken(String userId) {
        User user1 = new User();
        user1.setId(userId);
        String token = TokenProccessor.getInstance().makeToken();
        Token tok = new Token();
        tok.setCreatetime(new Date());
        tok.setStatus(0);
        tok.setToken(token);
        tok.setUserId(user1.getId());
        tokenMapper.insertSelective(tok);
        user1.setToken(tok);
        try {
            HashMapHelper.loginUserLock.writeLock().lock();
            User user2 = HashMapHelper.loginUser.get(user1.getId());
            if (user2 != null && user2.getToken() != null) {
                user2.getToken().setStatus(1);
                user2.getToken().setOutDate(new Date());
                Example example1 = new Example(Token.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("userId", user2.getToken().getId());
                criteria1.andEqualTo("token", user2.getToken().getToken());
                tokenMapper.updateByExampleSelective(user2.getToken(), example1);
            }
            HashMapHelper.loginUser.put(user1.getId(), user1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            HashMapHelper.loginUserLock.writeLock().unlock();
        }
        return tok;
    }

    @Override
    public void checkToken() {
        List<String> userIds = new ArrayList<>();
        int outTime = Properties.getUserTokenOutTime();
        //获取用户登陆信息  比较token时间是否超时 保存超时到list里
        try {
            HashMapHelper.loginUserLock.readLock().lock();
            Set<String> strings = HashMapHelper.loginUser.keySet();
            for (String string : strings) {
                User user = HashMapHelper.loginUser.get(string);
                if (user != null && user.getToken() != null) {
                    Date currentTime = new Date();
                    Date tokenTime = user.getToken().getCreatetime();
                    if (currentTime.getTime() > (tokenTime.getTime() + outTime * 60 * 1000)) {
                        userIds.add(user.getId());
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            HashMapHelper.loginUserLock.readLock().unlock();
        }
        //把超时的用户登陆token 数据库置为已过期 删除session信息 删除用户缓存
        for (String userId : userIds) {
            try {
                HashMapHelper.loginUserLock.writeLock().lock();
                User user2 = HashMapHelper.loginUser.get(userId);

                if (user2 != null && user2.getToken() != null) {
                    user2.getToken().setStatus(1);
                    user2.getToken().setOutDate(new Date());
                    Example example1 = new Example(Token.class);
                    Example.Criteria criteria1 = example1.createCriteria();
                    criteria1.andEqualTo("userId", user2.getToken().getId());
                    criteria1.andEqualTo("token", user2.getToken().getToken());
                    tokenMapper.updateByExampleSelective(user2.getToken(), example1);
                }
                HashMapHelper.loginUser.remove(userId);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                HashMapHelper.loginUserLock.writeLock().unlock();
            }
        }
    }

    @Override
    public Map<String,String> judgeTokenIsEqual(String token, String userId) {
        Map<String,String> map = new HashMap<>();
        try {
            HashMapHelper.loginUserLock.readLock().lock();
            User user = HashMapHelper.loginUser.get(userId);
            if (user == null) {
                map.put("status", "1");
                map.put("message", "token已过期 请重新获取有效token");
                return map;
            }
            map.put("status", "0");
            map.put("message", "验证通过");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            HashMapHelper.loginUserLock.readLock().unlock();
        }
        return map;
    }

    @Override
    public Map<String,Object> getToken(User user) {
        Map<String,Object> map = new HashMap<>();
        Token token = this.addToken(user.getId());
        if (token == null || StringUtils.isEmpty(token.getToken())) {
            map.put("status", 1);
            map.put("message", "获取失败请重试！");
        } else {
            map.put("status", 0);
            map.put("message", "获取成功！");
            map.put("data", token);
        }
        log.info("用户:" + user.getId() + " 重新获取token值");
        return map;
    }

    @Override
    public Map<String,Object> addUser(User user) {
        Map<String,Object> map = new HashMap<>();
        user.setId(UUIDHelper.getUUIDStr());
        user.setCreatetime(new Date());
        int i = userMapper.insertSelective(user);
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteUser(User user) {
        Map<String,Object> map = new HashMap<>();
        int i = userMapper.deleteByPrimaryKey(user.getId());
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> updateUser(User user) {
        Map<String,Object> map = new HashMap<>();
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getUser(User user) {
        Map<String,Object> map = new HashMap<>();
        List<User> select = userMapper.select(user);
        if (select.size() > 0) {
            map.put("status", 0);
            map.put("message", "成功");
            map.put("data", select);
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> addRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        role.setCreatetime(new Date());
        int i = roleMapper.insertSelective(role);
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "保存成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        int i = roleMapper.deleteByPrimaryKey(role.getId());
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "保存成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> updateRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        int i = roleMapper.updateByPrimaryKeySelective(role);
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "保存成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        List<Role> select = roleMapper.select(role);
        if (select.size() > 0) {
            map.put("status", 0);
            map.put("message", "保存成功");
            map.put("data", select);
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteModular(Modular modular) {
        Map<String,Object> map = new HashMap<>();
        int i = modularMapper.deleteByPrimaryKey(modular.getId());
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "保存成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> addModular(Modular modular) {
        Map<String,Object> map = new HashMap<>();
        int i = modularMapper.insertSelective(modular);
        if (i > 0) {
            map.put("status", 0);
            map.put("message", "保存成功");
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getModular(Modular modular) {
        Map<String,Object> map = new HashMap<>();
        List<Modular> select = modularMapper.select(modular);
        if (select.size() > 0) {
            map.put("status", 0);
            map.put("message", "保存成功");
            map.put("data", select);
        } else {
            map.put("status", 1);
            map.put("message", "保存失败");
        }
        return map;
    }
}
