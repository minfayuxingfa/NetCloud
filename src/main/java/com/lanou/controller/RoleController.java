package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.RoleService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class RoleController {
    @Resource
    private RoleService roleService;

//    跳转页面
    @ResponseBody
    @RequestMapping(value = "/roleList")
    public AjaxResult roleList() {
        System.out.println(11);
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toRole")
    public String toService() {
        return "/role/role_list";
    }

//    获取所有信息

    @ResponseBody
    @RequestMapping(value = "/selectAllRoleInfo",method = RequestMethod.POST)
    public AjaxResult selectAllRoleInfo() {
        List<RoleInfo> roleInfos = roleService.selectAllRoleInfo();
        return new AjaxResult(roleInfos);
    }

    //    获取分页信息
    @ResponseBody
    @RequestMapping(value = "/rolePage", method = RequestMethod.POST)
    public PageInfo<RoleInfo> rolePage(@RequestParam("pagesize") Integer pageSize) {


        return roleService.getPageinfo(pageSize);
    }

    //    删除Role
    @ResponseBody
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public AjaxResult deleteRole(RoleInfo roleInfo) {
        int i = roleService.deleteByPrimaryKey(roleInfo);
        int i1 = roleService.deleteRMByPrimaryKey(roleInfo);
        return new AjaxResult(1);
    }

    //    跳转的添加页面

    @ResponseBody
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public AjaxResult addRole() {
        return new AjaxResult(1);
    }
    @RequestMapping(value = "/toAddRole")
    public String toAddRole() {
        return "/role/role_add";
    }

    //    保存到Role表
    @ResponseBody
    @RequestMapping(value = "/saveAddRole",method = RequestMethod.POST)
    public AjaxResult saveAddRole(RoleInfo roleInfo) {

        int insert = roleService.insert(roleInfo);
        RoleInfo roleInfo1 = roleService.selectPromaryKey(roleInfo);
        return new AjaxResult(roleInfo1);
    }
    //    保存到Role_Module表
    @ResponseBody
    @RequestMapping(value = "/saveAddRoleModule",method = RequestMethod.POST)
    public AjaxResult saveAddRoleModule(@RequestParam("roleId") Integer roleId,
                                        @RequestParam("moduleIdList") Integer[] moduleIdList) {

        for (int i=0;i<moduleIdList.length;i++) {

            int m = roleService.insertRM(roleId,moduleIdList[i]);
        }
        return new AjaxResult(1);
    }
//    获取role的详细信息放入session
    @ResponseBody
    @RequestMapping(value = "/showRoleModi",method = RequestMethod.POST)
    public AjaxResult saveAddRoleModule(HttpServletRequest request,RoleInfo roleInfo) {
        RoleInfo roleInfo1 = roleService.selectByPrimaryKey(roleInfo);
request.getSession().setAttribute("RoleModi",roleInfo1);
        return new AjaxResult(1);
    }

//    跳转roleModi详细
    @RequestMapping(value = "/toRoleModi")
    public String toRoleModi() {
        return "/role/role_modi";
    }

    //    session中获取role的详细信息
    @ResponseBody
    @RequestMapping(value = "/getRoleModi",method = RequestMethod.POST)
    public AjaxResult getRoleModi(HttpServletRequest request) {

        RoleInfo roleModi = (RoleInfo) request.getSession().getAttribute("RoleModi");
        return new AjaxResult(roleModi);
    }

    //    保存role的详细信息到数据库
    @ResponseBody
    @RequestMapping(value = "/saveModiRole",method = RequestMethod.POST)
    public AjaxResult saveModiRole(HttpServletRequest request,@RequestParam("name") String name,
                                   @RequestParam("moduleIdList") Integer[] moduleIdList) {
        RoleInfo roleModi = (RoleInfo) request.getSession().getAttribute("RoleModi");
                roleModi.setName(name);
        int i1 = roleService.updateByPrimaryKeySelected(roleModi);
        int i2 = roleService.deleteRMByPrimaryKey(roleModi);
        for (int i=0;i<moduleIdList.length;i++){
            int i3 = roleService.insertRM(roleModi.getRoleId(), moduleIdList[i]);
        }
        return new AjaxResult(1);
    }


}
