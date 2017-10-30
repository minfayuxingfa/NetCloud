package com.lanou.mapper;

import com.lanou.bean.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);
    int deleteRMByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);
    int insertRM(@Param("roleId") Integer roleId,@Param("moduleId") Integer moduleId);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);
    RoleInfo selectPrimaryKey(RoleInfo roleInfo);
    List<RoleInfo> selectRoleInfosByAdminId(@Param("adminId") Integer adminId);

    RoleInfo findRoleByModuleId(@Param("moduleId") Integer moduleId);


    List<RoleInfo> selectAllRoleInfo();

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}