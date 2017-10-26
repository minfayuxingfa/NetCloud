package com.lanou.mapper;

import com.lanou.bean.Cost;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost cost);
    void inserting(Cost cost);

    int insertSelective(Cost cost);

    Cost selectByPrimaryKey(Integer costId);
    Cost selectByName(Cost cost);

    List<Cost> selectAllCost();
    List<Cost> orderAllCostAsc();
    List<Cost> orderAllCostDesc();

    List<Cost> orderAllDurationAsc();
    List<Cost> orderAllDurationDesc();


    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);
    List<Cost>findAllStudents();
}