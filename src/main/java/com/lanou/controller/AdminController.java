package com.lanou.controller;

import com.lanou.bean.AdminInfo;
import com.lanou.service.AdminInfoService;
import com.lanou.utils.AjaxResult;
import com.lanou.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

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
    public AjaxResult toFeeList(){

        return new AjaxResult(1);
    }



}
