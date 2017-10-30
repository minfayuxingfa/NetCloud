package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.RoleInfo;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */

public interface RoleService {
    List<RoleInfo> selectAllRoleInfo();
    PageInfo<RoleInfo> getPageinfo(Integer pageSize);
    int deleteByPrimaryKey(RoleInfo roleInfo);
    int deleteRMByPrimaryKey(RoleInfo roleInfo);
    int insert(RoleInfo roleInfo);
    int insertRM(Integer roleId,Integer moduleId);
    RoleInfo selectPromaryKey(RoleInfo roleInfo);
    RoleInfo selectByPrimaryKey(RoleInfo roleInfo);

    int updateByPrimaryKeySelected(RoleInfo roleInfo);
}
