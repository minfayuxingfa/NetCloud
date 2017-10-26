package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/21.
 */
public interface CostService {
    List<Cost> selectAllCost();
    List<Cost> orderAllCostAsc();
    List<Cost> orderAllCostDesc();

    List<Cost> orderAllDurationAsc();
    List<Cost> orderAllDurationDesc();

    void delCost(Cost cost);
Cost selectByName(Cost cost);
Cost selectByPrimaryKey(Cost cost);
    void updateByPrimaryKey(Cost cost);
    int updateByPrimaryKeySelected(Cost cost);
    void inseting(Cost cost);
    List<Cost>findWithPageInfo(Integer pageNo,Integer pageSize);
    PageInfo<Cost> getPageinfo(Integer pageSize);

}
