package com.data.big.controller;

import com.data.big.model.AuthResource;
import com.data.big.service.ResourceService;
import com.data.big.util.TreeUtil;
import com.data.big.vo.MenuTreeNode;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *   资源URL管理
 * @author tomsun28
 * @date 21:36 2018/3/17
 */
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAction.class);

    @Autowired
    private ResourceService resourceService;

 //   //@ApiOperation(value = "获取用户被授权菜单",notes = "通过uid获取对应用户被授权的菜单列表,获取完整菜单树形结构")
    @GetMapping("authorityMenu")
    public Message getAuthorityMenu(HttpServletRequest request) {
        String uid = request.getHeader("appId");
        List<MenuTreeNode> treeNodes = new ArrayList<>();
        List<AuthResource> resources = resourceService.getAuthorityMenusByUid(uid);

        for (AuthResource resource : resources) {
            MenuTreeNode node = new MenuTreeNode();
            BeanUtils.copyProperties(resource,node);
            treeNodes.add(node);
        }
        List<MenuTreeNode> menuTreeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,-1);
        return new Message().ok("success",menuTreeNodes);
    }

  //  //@ApiOperation(value = "获取全部菜单列", httpMethod = "GET")
    @GetMapping("menus")
    public Message getMenus() {

        List<MenuTreeNode> treeNodes = new ArrayList<>();
        List<AuthResource> resources = resourceService.getMenus();

        for (AuthResource resource: resources) {
            MenuTreeNode node = new MenuTreeNode();
            BeanUtils.copyProperties(resource,node);
            treeNodes.add(node);
        }
        List<MenuTreeNode> menuTreeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,-1);
        return new Message().ok("success",menuTreeNodes);
    }

    ////@ApiOperation(value = "增加菜单",httpMethod = "POST")
    @PostMapping("menu")
    public Message addMenu(@RequestBody AuthResource menu ) {

        Boolean flag = resourceService.addMenu(menu);
        if (flag) {
            return new Message().ok("success","");
        } else {
            return new Message().error("添加失败");
        }
    }

    ////@ApiOperation(value = "修改菜单",httpMethod = "PUT")
    @PutMapping("menu")
    public Message updateMenu(@RequestBody AuthResource menu) {

        Boolean flag = resourceService.modifyMenu(menu);
        if (flag) {
            return new Message().ok("success","");
        } else {
            return new Message().error( "修改失败");
        }
    }

    //@ApiOperation(value = "删除菜单", notes = "根据菜单ID删除菜单", httpMethod = "DELETE")
    @DeleteMapping("menu/{menuId}")
    public Message deleteMenuByMenuId(@PathVariable Integer menuId) {

        Boolean flag = resourceService.deleteMenuByMenuId(menuId);
        if (flag) {
            return new Message().ok("success","");
        } else {
            return new Message().error("删除失败");
        }
    }

    @SuppressWarnings("unchecked")
    //@ApiOperation(value = "获取API list", notes = "需要分页,根据teamId判断,-1->获取api分类,0->获取全部api,id->获取对应分类id下的api",httpMethod = "GET")
    @GetMapping("api/{teamId}/{currentPage}/{pageSize}")
    public Message getApiList(@PathVariable Integer teamId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {

        List<AuthResource> resources = null;
        if (teamId == -1) {
            // -1 为获取api分类
            resources = resourceService.getApiTeamList();
            return new Message().ok("success",resources);
        }
        PageHelper.startPage(currentPage, pageSize);
        if (teamId == 0) {
            // 0 为获取全部api
            resources = resourceService.getApiList();
        } else {
            // 其他查询teamId 对应分类下的apis
            resources = resourceService.getApiListByTeamId(teamId);
        }
        PageInfo pageInfo = new PageInfo(resources);
        return new Message().ok("success",pageInfo);
    }

    //@ApiOperation(value = "增加API",httpMethod = "POST")
    @PostMapping("api")
    public Message addApi(@RequestBody AuthResource api ) {

        Boolean flag = resourceService.addMenu(api);
        if (flag) {
            return new Message().ok("success","");
        } else {
            return new Message().error("添加失败");
        }
    }

    //@ApiOperation(value = "修改API",httpMethod = "PUT")
    @PutMapping("api")
    public Message updateApi(@RequestBody AuthResource api) {

        Boolean flag = resourceService.modifyMenu(api);
        if (flag) {
            return new Message().ok("success","");
        } else {
            return new Message().error("修改失败");
        }
    }

    //@ApiOperation(value = "删除API", notes = "根据API_ID删除API", httpMethod = "DELETE")
    @DeleteMapping("api/{apiId}")
    public Message deleteApiByApiId(@PathVariable Integer apiId) {

        Boolean flag = resourceService.deleteMenuByMenuId(apiId);
        if (flag) {
            return new Message().ok("success","");
        } else {
            return new Message().error("删除失败");
        }
    }

}
