package com.lanou.mapper;

import com.lanou.bean.ModuleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleInfoMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(ModuleInfo record);

    int insertSelective(ModuleInfo record);

    ModuleInfo selectByPrimaryKey(Integer moduleId);
    List<ModuleInfo> findModuleByRoleId(@Param("role_id") Integer roleId);
    List<ModuleInfo> findAllModule();

    int updateByPrimaryKeySelective(ModuleInfo record);

    int updateByPrimaryKey(ModuleInfo record);


}