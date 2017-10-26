package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Service;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
          @Resource
          private ServiceMapper serviceMapper;


        public List<Service> selectAllService() {
        List<Service> services = serviceMapper.selectAllService();
        return services;
    }

    public int insertSelective(Service service) {
        int i = serviceMapper.insertSelective(service);
        return i;
    }

    public PageInfo<Service> getPageinfo(Integer pageSize) {
        return queryStudentByPage(null,pageSize);
    }

    public Service selectServiceByPrimaryKey(Service service) {
        Service service1 = serviceMapper.selectByPrimaryKey(service.getServiceId());
        return service1;
    }

    public PageInfo<Service>queryStudentByPage(Integer pageNo, Integer pageSize){

//        判断参数的合法性
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?5:pageSize;

        PageHelper.startPage(pageNo,pageSize);
//        获取全部学员
        List<Service>studentList=serviceMapper.selectAllService();
//        使用pageInfo对结果进行包装
        PageInfo<Service>pageInfo=new PageInfo<Service>(studentList);

        return pageInfo;
    }
}
