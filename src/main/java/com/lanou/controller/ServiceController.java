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

    @RequestMapping(value = "/toModiService")
    public String toModi() {
        return "/service/service_modi";
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

    //   暂停service
    @ResponseBody
    @RequestMapping(value = "/stopService", method = RequestMethod.POST)
    public AjaxResult stopService(Service service) {
        service.setPauseDate(new Date());
        service.setStatus("2");
        int i = serviceService.updateByPrimaryKeySelective(service);
        return new AjaxResult(1);
    }

    //   开通service
    @ResponseBody
    @RequestMapping(value = "/startService", method = RequestMethod.POST)
    public AjaxResult startService(Service service) {
        service.setPauseDate(null);
        service.setStatus("1");
        int i = serviceService.updateByPrimaryKeySelective(service);
        return new AjaxResult(1);
    }
    //   修改service
    @ResponseBody
    @RequestMapping(value = "/updateService", method = RequestMethod.POST)
    public AjaxResult updateService(HttpServletRequest request,Service service) {
        Service service1 = serviceService.selectServiceByPrimaryKey(service);
         request.getSession().setAttribute("updateService",service1);
        return new AjaxResult(1);
    }

    @ResponseBody
    @RequestMapping(value = "/selectServiceModi", method = RequestMethod.POST)
    public AjaxResult selectServiceModi(HttpServletRequest request) {

        Service updateService = (Service) request.getSession().getAttribute("updateService");
        return new AjaxResult(updateService);
    }

    @ResponseBody
    @RequestMapping(value = "/saveUpdateService", method = RequestMethod.POST)
    public AjaxResult saveUpdateService(Service service) {


        return new AjaxResult(1);
    }



    //  删除service
    @ResponseBody
    @RequestMapping(value = "/deleteService", method = RequestMethod.POST)
    public AjaxResult deleteService(Service service) {
        service.setCloseDate(new Date());
        service.setStatus("3");
        int i = serviceService.updateByPrimaryKeySelective(service);
        return new AjaxResult(1);
    }

    // 多条件查询
    @ResponseBody
    @RequestMapping(value = "/multiSituationSelect", method = RequestMethod.POST)
    public AjaxResult multiSituationSelect(Service service) {

        System.out.println(service.getAccountId());
        System.out.println(service.getStatus());
        System.out.println(service.getOsUsername());
        System.out.println(service.getUnixHost());
        List<Service> services = serviceService.selectServiceSelected(service);
        return new AjaxResult(services);
    }

}