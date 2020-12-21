package com.data.big.controller;

import com.data.big.model.AuthResource;
import com.data.big.model.AuthRole;
import com.data.big.model.AuthUser;
import com.data.big.service.ResourceService;
import com.data.big.service.RoleService;
import com.data.big.service.UserService;
import com.data.big.shiro.filter.ShiroFilterChainManager;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tomsun28
 * @date 20:02 2018/3/20
 */
@RequestMapping("/role")
@RestController
public class RoleController extends BaseAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ShiroFilterChainManager shiroFilterChainManager;

    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取角色关联的(roleId)对应用户列表",httpMethod = "GET")
    @GetMapping("user/{roleId}/{currentPage}/{pageSize}")
    public Message getUserListByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<AuthUser> users = userService.getUserListByRoleId(roleId);
        users.forEach(user->user.setPassword(null));
        PageInfo pageInfo = new PageInfo(users);
        return new Message().ok("success",pageInfo);
    }

    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取角色未关联的用户列表", httpMethod = "GET")
    @GetMapping("user/-/{roleId}/{currentPage}/{pageSize}")
    public Message getUserListExtendByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthUser> users = userService.getNotAuthorityUserListByRoleId(roleId);
        users.forEach(user -> user.setPassword(null));
        PageInfo pageInfo = new PageInfo(users);
        return new Message().ok("success", pageInfo);
    }


    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取角色(roleId)所被授权的API资源")
    @GetMapping("api/{roleId}/{currentPage}/{pageSize}")
    public Message getRestApiExtendByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getAuthorityApisByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok("success", pageInfo);
    }

    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取角色(roleId)未被授权的API资源")
    @GetMapping("api/-/{roleId}/{currentPage}/{pageSize}")
    public Message getRestApiByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getNotAuthorityApisByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok( "success", pageInfo);
    }

    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取角色(roleId)所被授权的menu资源")
    @GetMapping("menu/{roleId}/{currentPage}/{pageSize}")
    public Message getMenusByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getAuthorityMenusByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok("success", pageInfo);
    }

    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取角色(roleId)未被授权的menu资源")
    @GetMapping("menu/-/{roleId}/{currentPage}/{pageSize}")
    public Message getMenusExtendByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getNotAuthorityMenusByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok("return api success", pageInfo);
    }

    //@ApiOperation(value = "授权资源给角色",httpMethod = "POST")
    @PostMapping("/authority/resource")
    public Message authorityRoleResource(HttpServletRequest request) {
        Map<String,String> map = getRequestBody(request);
        int roleId = Integer.parseInt(map.get("roleId"));
        int resourceId = Integer.parseInt(map.get("resourceId"));
        boolean flag = roleService.authorityRoleResource(roleId,resourceId);
        shiroFilterChainManager.reloadFilterChain();
        return flag ? new Message().ok("success","") : new Message().error("授权失败");
    }

    //@ApiOperation(value = "删除对应的角色的授权资源",httpMethod = "DELETE")
    @DeleteMapping("/authority/resource/{roleId}/{resourceId}")
    public Message deleteAuthorityRoleResource(@PathVariable Integer roleId, @PathVariable Integer resourceId ) {
        boolean flag = roleService.deleteAuthorityRoleResource(roleId,resourceId);
        shiroFilterChainManager.reloadFilterChain();
        return flag ? new Message().ok("success","") : new Message().error("删除授权资源失败");
    }

    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取角色LIST", httpMethod = "GET")
    @GetMapping("{currentPage}/{pageSize}")
    public Message getRoles(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {

        PageHelper.startPage(currentPage, pageSize);
        List<AuthRole> roles = roleService.getRoleList();
        PageInfo pageInfo = new PageInfo(roles);
        return new Message().ok("success", pageInfo);
    }

    //@ApiOperation(value = "添加角色", httpMethod = "POST")
    @PostMapping("")
    public Message addRole(@RequestBody AuthRole role) {

        boolean flag = roleService.addRole(role);
        if (flag) {
            return new Message().ok( "success","");
        } else {
            return new Message().error( "添加失败");
        }
    }

    //@ApiOperation(value = "更新角色", httpMethod = "PUT")
    @PutMapping("")
    public Message updateRole(@RequestBody AuthRole role) {

        boolean flag = roleService.updateRole(role);
        if (flag) {
            return new Message().ok("success","");
        } else {
            return new Message().error("修改失败");
        }
    }

    //@ApiOperation(value = "根据角色ID删除角色", httpMethod = "DELETE")
    @DeleteMapping("{roleId}")
    public Message deleteRoleByRoleId(@PathVariable Integer roleId) {

        boolean flag = roleService.deleteRoleByRoleId(roleId);
        if (flag) {
            return new Message().ok( "success","");
        } else {
            return new Message().error( "删除失败");
        }
    }


}
