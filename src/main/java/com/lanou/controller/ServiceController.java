package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Service;
import com.lanou.service.AccountService;
import com.lanou.service.ServiceService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    @ResponseBody
    @RequestMapping(value = "/serviceList")
    public AjaxResult serviceList() {
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toService")
    public String toService() {
        return "/service/service_list";
    }

    @RequestMapping(value = "/toServiceDetail")
    public String toServiceDetail() {
        return "/service/service_detail";
    }

    //查询所有的Service
    @ResponseBody
    @RequestMapping(value = "/selectAllService", method = RequestMethod.POST)
    public AjaxResult selectAllServiece() {

        List<Service> services = serviceService.selectAllService();

        return new AjaxResult(services);
    }

    //    去添加页面
    @ResponseBody
    @RequestMapping(value = "/addService", method = RequestMethod.POST)
    public AjaxResult addService() {
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toAddService")
    public String toAddService() {
        return "/service/service_add";
    }

    //    保存添加
    @ResponseBody
    @RequestMapping(value = "/saveAddService", method = RequestMethod.POST)
    public AjaxResult saveAddService(Service service, @RequestParam("reLoginPasswd") String reLoginPasswd) {
        if (service.getLoginPasswd().equals(reLoginPasswd)) {
            service.setStatus("1");
            service.setCreateDate(new Date());
            int i = serviceService.insertSelective(service);
            return new AjaxResult(1);
        } else {
            return new AjaxResult(2);
        }

    }

    //    获取分页信息
    @ResponseBody
    @RequestMapping(value = "/servicePage", method = RequestMethod.POST)
    public PageInfo<Service> accountPage(@RequestParam("pagesize") Integer pageSize) {


        return serviceService.getPageinfo(pageSize);
    }


    //    获取Service的详细信息放入session
    @ResponseBody
    @RequestMapping(value = "/getServiceDetailService", method = RequestMethod.POST)
    public AjaxResult getAccountDetail(HttpServletRequest request, Service service) {
        Service service1 = serviceService.selectServiceByPrimaryKey(service);
        request.getSession().setAttribute("serviceDetail", service1);
        return new AjaxResult(1);
    }

    //    在service_detail中获取service的详细信息
    @ResponseBody
    @RequestMapping(value = "/selectAccountDetail", method = RequestMethod.POST)
    public AjaxResult selectAccountDetail(HttpServletRequest request) {
        Service serviceDetail = (Service) request.getSession().getAttribute("serviceDetail");

        return new AjaxResult(serviceDetail);
    }

}