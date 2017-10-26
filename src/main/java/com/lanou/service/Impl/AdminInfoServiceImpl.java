package com.lanou.service.Impl;

import com.lanou.bean.AdminInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.AdminInfoMapper;
import com.lanou.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/21.
 */

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
        @Resource
        private AdminInfoMapper adminInfoMapper;


    public AdminInfo getAdminInfo(AdminInfo adminInfo) {
        AdminInfo adminInfo1 = adminInfoMapper.getAdminInfo(adminInfo);
        return adminInfo1;
    }


}
