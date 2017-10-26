package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/21.
 */
@Service
public class CostServiceImpl implements CostService {
    @Resource
    private CostMapper costMapper;


    public List<Cost> selectAllCost() {

        List<Cost> costs = costMapper.selectAllCost();
        return costs;
    }

    public List<Cost> orderAllCostAsc() {
        List<Cost> costs = costMapper.orderAllCostAsc();
        return costs;
    }

    public List<Cost> orderAllCostDesc() {
        List<Cost> costs = costMapper.orderAllCostDesc();
        return costs;
    }

    public List<Cost> orderAllDurationAsc() {
        List<Cost> costs = costMapper.orderAllDurationAsc();
        return costs;
    }

    public List<Cost> orderAllDurationDesc() {
        List<Cost> costs = costMapper.orderAllDurationDesc();
        return costs;
    }

    public void delCost(Cost cost) {
        int i = costMapper.deleteByPrimaryKey(cost.getCostId());

    }

    public Cost selectByName(Cost cost) {
        Cost cost1 = costMapper.selectByName(cost);
        return cost1;
    }

    public Cost selectByPrimaryKey(Cost cost) {
        Cost cost1 = costMapper.selectByPrimaryKey(cost.getCostId());
        return cost1;
    }

    public void updateByPrimaryKey(Cost cost) {
        int i = costMapper.updateByPrimaryKeySelective(cost);
    }

    public int updateByPrimaryKeySelected(Cost cost) {
        int i = costMapper.updateByPrimaryKeySelective(cost);
        return i;
    }

    public void inseting(Cost cost) {

      costMapper.inserting(cost);

    }


    public List<Cost> findWithPageInfo(Integer pageNo, Integer pageSize) {
        //目标:获取pageInfo对象
        PageInfo<Cost> pageInfo=queryStudentByPage(pageNo,pageSize);

        return pageInfo.getList();
    }

    public PageInfo<Cost> getPageinfo(Integer pageSize) {

        return queryStudentByPage(null,pageSize);
    }


    public PageInfo<Cost>queryStudentByPage(Integer pageNo, Integer pageSize){

//        判断参数的合法性
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?5:pageSize;

        PageHelper.startPage(pageNo,pageSize);
//        获取全部学员
        List<Cost>studentList=costMapper.findAllStudents();
//        使用pageInfo对结果进行包装
        PageInfo<Cost>pageInfo=new PageInfo<Cost>(studentList);
        System.out.println(pageInfo);

        return pageInfo;
    }
}
