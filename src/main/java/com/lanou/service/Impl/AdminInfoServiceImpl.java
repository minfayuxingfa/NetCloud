package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.bean.Cost;
import com.lanou.bean.RoleInfo;
import com.lanou.mapper.AdminInfoMapper;
import com.lanou.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public AdminInfo selectAdminByPrimaryKey(AdminInfo adminInfo) {
        AdminInfo adminInfo1 = adminInfoMapper.selectByPrimaryKey(adminInfo.getAdminId());
        return adminInfo1;
    }

    public List<AdminInfo> selectAllAdminInfo() {
        List<AdminInfo> adminInfos = adminInfoMapper.selectAllAdminInfo();
        return adminInfos;
    }

    public int insetSelective(AdminInfo adminInfo) {
        int i = adminInfoMapper.insertSelective(adminInfo);
        return i;
    }

    public int deleteByPrimaryKey(AdminInfo adminInfo) {
        int i = adminInfoMapper.deleteByPrimaryKey(adminInfo.getAdminId());
        return i;
    }

    public int deleteARByPrimaryKey(AdminInfo adminInfo) {
        int i = adminInfoMapper.deleteARByPrimaryKey(adminInfo.getAdminId());
        return i;
    }

    public PageInfo<AdminInfo> getPageinfo(Integer pageSize) {

        return queryStudentByPage(null,pageSize);

    }

    public int insertAR(Integer adminId, Integer roleId) {
        int i = adminInfoMapper.insertAR(adminId, roleId);
        return i;
    }

    public int updateByPrimaryKey(AdminInfo adminInfo) {
        int i = adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
        return i;
    }

    public PageInfo<AdminInfo>queryStudentByPage(Integer pageNo, Integer pageSize){

//        判断参数的合法性
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?5:pageSize;

        PageHelper.startPage(pageNo,pageSize);
//        获取全部学员
        List<AdminInfo>studentList=adminInfoMapper.selectAllAdminInfo();
//        使用pageInfo对结果进行包装
        PageInfo<AdminInfo>pageInfo=new PageInfo<AdminInfo>(studentList);

        return pageInfo;
    }

}
