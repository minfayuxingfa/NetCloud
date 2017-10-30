package com.lanou.mapper;

import com.lanou.bean.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);
    int deleteARByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);
    int insertAR(@Param("adminId") Integer adminId,@Param("roleId") Integer roleId);

    int insertSelective(AdminInfo record);


    AdminInfo selectByPrimaryKey(Integer adminId);

    List<AdminInfo> selectAllAdminInfo();

    AdminInfo getAdminInfo(AdminInfo adminInfo);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
}