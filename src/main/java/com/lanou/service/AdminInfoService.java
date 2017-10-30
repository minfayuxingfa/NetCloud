package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.bean.Cost;
import com.lanou.bean.RoleInfo;

import java.util.List;

/**
 * Created by dllo on 17/10/21.
 */
public interface AdminInfoService {
    AdminInfo getAdminInfo(AdminInfo adminInfo);
    AdminInfo selectAdminByPrimaryKey(AdminInfo adminInfo);
    List<AdminInfo> selectAllAdminInfo();
    int insetSelective(AdminInfo adminInfo);
    int deleteByPrimaryKey(AdminInfo adminInfo);
    int deleteARByPrimaryKey(AdminInfo adminInfo);
    PageInfo<AdminInfo> getPageinfo(Integer pageSize);
    int insertAR(Integer adminId,Integer roleId);
    int updateByPrimaryKey(AdminInfo adminInfo);
}
