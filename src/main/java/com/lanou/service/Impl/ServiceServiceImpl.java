package com.lanou.service.Impl;

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
}
