package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Service;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface ServiceService {

    List<Service>selectAllService();
    List<Service>selectServiceSelected(Service service);

    int insertSelective(Service service);
    PageInfo<Service> getPageinfo(Integer pageSize);

    Service selectServiceByPrimaryKey(Service service);
    int updateByPrimaryKeySelective(Service service);
}
