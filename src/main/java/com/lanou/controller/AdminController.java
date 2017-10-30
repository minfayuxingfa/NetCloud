package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.AdminInfoService;
import com.lanou.utils.AjaxResult;
import com.lanou.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class AdminController {

    @Resource
    private AdminInfoService adminInfoService;


    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    @RequestMapping(value = "/index")
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/nopower")
    public String nopower() {

        return "nopower";
    }

    @RequestMapping(value = "/error")
    public String error() {

        return "error";
    }

    @ResponseBody
    @RequestMapping(value = "/getImg")
    public void CodImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        request.getSession().setAttribute("verifyCode", verifyCode.getText());
        //获得response对象的输出流用于图像的写入
        OutputStream os = response.getOutputStream();

        VerifyCode.output(image, os);//将图片对象映射到输出流中

    }

    @ResponseBody
    @RequestMapping(value = "/toHome")
    public AjaxResult getImg(HttpServletRequest request, AdminInfo adminInfo,
                             @RequestParam("verifyCode") String verifyCode) throws IOException {
        String oldVerifyCode = (String) request.getSession().getAttribute("verifyCode");
        if (oldVerifyCode.equalsIgnoreCase(verifyCode)) {
            AdminInfo adminInfo1 = adminInfoService.getAdminInfo(adminInfo);
            if (adminInfo1 != null) {
                return new AjaxResult(1);
            } else {
                return new AjaxResult(2);
            }
        }
        return new AjaxResult(3);
    }


    @RequestMapping(value = "/toFee")
    public String toFee() {
        return "fee/fee_list";
    }

    @ResponseBody
    @RequestMapping(value = "/feeList")
    public AjaxResult toFeeList() {

        return new AjaxResult(1);
    }

    //*********************************以下为管理员********************************
    //去管理员页面
    @ResponseBody
    @RequestMapping(value = "/adminList")
    public AjaxResult adminList() {
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toAdmin")
    public String toAdmit() {
        return "admin/admin_list";
    }

    //    获取全部管理员信息
    @ResponseBody
    @RequestMapping(value = "/selectAllAdmin")
    public AjaxResult selectAllAdmin() {
        List<AdminInfo> adminInfos = adminInfoService.selectAllAdminInfo();
        return new AjaxResult(adminInfos);
    }

    //      去addAdmin页面
    @ResponseBody
    @RequestMapping(value = "/addAdmin")
    public AjaxResult addAdmin() {

        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toAddAdmin")
    public String toAddAdmit() {
        return "admin/admin_add";
    }

    //  保存到Admin表
    @ResponseBody
    @RequestMapping(value = "/saveAddAdmin", method = RequestMethod.POST)
    public AjaxResult saveAddAdmin(HttpServletRequest request, AdminInfo adminInfo, @RequestParam("rePassword") String rePassword) {
        if (!rePassword.equals(adminInfo.getPassword())) {
            return new AjaxResult(2);
        }
        adminInfo.setEnrolldate(new Date());
        int i = adminInfoService.insetSelective(adminInfo);

        List<AdminInfo> adminInfos = adminInfoService.selectAllAdminInfo();

        int k = adminInfos.get(0).getAdminId();
        for (int j = 1; j < adminInfos.size(); j++) {
            if (adminInfos.get(j - 1).getAdminId() < adminInfos.get(j).getAdminId()) {
                k = adminInfos.get(j).getAdminId();
            }
        }

        request.getSession().setAttribute("k", k);

        return new AjaxResult(1);
    }

    //  删除Admin
    @ResponseBody
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST)
    public AjaxResult deleteAdmin(AdminInfo adminInfo) {
        int i = adminInfoService.deleteByPrimaryKey(adminInfo);
        int i1 = adminInfoService.deleteARByPrimaryKey(adminInfo);
        return new AjaxResult(1);
    }

    //    获取分页信息
    @ResponseBody
    @RequestMapping(value = "/adminPage", method = RequestMethod.POST)
    public PageInfo<AdminInfo> adminPage(@RequestParam("pagesize") Integer pageSize) {


        return adminInfoService.getPageinfo(pageSize);
    }

    //  保存到Admin_Role表
    @ResponseBody
    @RequestMapping(value = "/saveAddAdminSe")
    public AjaxResult saveAddAdminSe(HttpServletRequest request, @RequestParam("roleIdList") Integer[] roleIdList) {
        Integer k = (Integer) request.getSession().getAttribute("k");
        for (int i = 0; i < roleIdList.length; i++) {
            System.out.println(roleIdList[i]);
            int i1 = adminInfoService.insertAR(k, roleIdList[i]);
        }

        return new AjaxResult(1);
    }

    //  跳转到update页面
    @ResponseBody
    @RequestMapping(value = "/updateAdmin")
    public AjaxResult updateAdmin(HttpServletRequest request, AdminInfo adminInfo) {
        AdminInfo adminInfo1 = adminInfoService.selectAdminByPrimaryKey(adminInfo);
        request.getSession().setAttribute("adminInfoModi", adminInfo1);
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toUpdateAdmin")
    public String toUpdateAdmin() {
        return "admin/admin_modi";
    }

    //  update页面获取回显对象
    @ResponseBody
    @RequestMapping(value = "/rollBackAdmin")
    public AjaxResult rollBackAdmin(HttpServletRequest request) {

        AdminInfo adminInfoModi = (AdminInfo) request.getSession().getAttribute("adminInfoModi");
        return new AjaxResult(adminInfoModi);
    }

    //  update进表格
    @ResponseBody
    @RequestMapping(value = "/saveModiAdmin", method = RequestMethod.POST)
    public AjaxResult saveModiAdmin(HttpServletRequest request, AdminInfo adminInfo, @RequestParam("roleIdList") Integer[] roleIdList) {

        AdminInfo adminInfoModi = (AdminInfo) request.getSession().getAttribute("adminInfoModi");
        adminInfoModi.setName(adminInfo.getName());
        adminInfoModi.setAdminCode(adminInfo.getAdminCode());
        adminInfoModi.setTelephone(adminInfo.getTelephone());
        adminInfoModi.setEmail(adminInfo.getEmail());
        int i = adminInfoService.updateByPrimaryKey(adminInfoModi);

        int i1 = adminInfoService.deleteARByPrimaryKey(adminInfoModi);
        for (int j = 0; j < roleIdList.length; j++) {

            int i8 = adminInfoService.insertAR(adminInfoModi.getAdminId(), roleIdList[j]);
        }
        return new AjaxResult(1);
    }


}
