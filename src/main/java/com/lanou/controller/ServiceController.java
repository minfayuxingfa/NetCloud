package com.lanou.controller;

import com.lanou.bean.Service;
import com.lanou.service.ServiceService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    public AjaxResult serviceList(){
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toService")
    public String toService(){
        return "/service/service_list";
    }

//查询所有的Service
    @ResponseBody
    @RequestMapping(value = "/selectAllService",method = RequestMethod.POST)
    public AjaxResult selectAllServiece(){

        List<Service> services = serviceService.selectAllService();

        return new AjaxResult(services);
    }
}
