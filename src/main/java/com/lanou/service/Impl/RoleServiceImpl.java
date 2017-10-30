package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.RoleInfo;
import com.lanou.mapper.RoleInfoMapper;
import com.lanou.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleInfoMapper roleInfoMapper;

    public List<RoleInfo> selectAllRoleInfo() {
        List<RoleInfo> roleInfos = roleInfoMapper.selectAllRoleInfo();

        return roleInfos;
    }

    public PageInfo<RoleInfo> getPageinfo(Integer pageSize) {

            return queryStudentByPage(null,pageSize);
        }

    public int deleteByPrimaryKey(RoleInfo roleInfo) {
        int i = roleInfoMapper.deleteByPrimaryKey(roleInfo.getRoleId());
        return i;
    }

    public int deleteRMByPrimaryKey(RoleInfo roleInfo) {
        int i = roleInfoMapper.deleteRMByPrimaryKey(roleInfo.getRoleId());
        return i;
    }

    public int insert(RoleInfo roleInfo) {
        int i = roleInfoMapper.insert(roleInfo);
        return i;
    }

    public int insertRM(Integer roleId, Integer moduleId) {
        int i = roleInfoMapper.insertRM(roleId, moduleId);
        return i;
    }

    public RoleInfo selectPromaryKey(RoleInfo roleInfo) {
        RoleInfo roleInfo1 = roleInfoMapper.selectPrimaryKey(roleInfo);
        return roleInfo1;
    }

    public RoleInfo selectByPrimaryKey(RoleInfo roleInfo) {
        RoleInfo roleInfo1 = roleInfoMapper.selectByPrimaryKey(roleInfo.getRoleId());
        return roleInfo1;
    }

    public int updateByPrimaryKeySelected(RoleInfo roleInfo) {
        int i = roleInfoMapper.updateByPrimaryKeySelective(roleInfo);
        return i;
    }

    public PageInfo<RoleInfo>queryStudentByPage(Integer pageNo, Integer pageSize){

//        判断参数的合法性
            pageNo=pageNo==null?1:pageNo;
            pageSize=pageSize==null?5:pageSize;

            PageHelper.startPage(pageNo,pageSize);
//        获取全部学员
            List<RoleInfo>studentList=roleInfoMapper.selectAllRoleInfo();
//        使用pageInfo对结果进行包装
            PageInfo<RoleInfo>pageInfo=new PageInfo<RoleInfo>(studentList);

            return pageInfo;
        }

    }

