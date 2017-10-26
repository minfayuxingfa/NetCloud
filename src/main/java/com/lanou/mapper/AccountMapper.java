package com.lanou.mapper;

import com.lanou.bean.Account;
import com.lanou.bean.Cost;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account account);
    void insertSelect(Account account);
    Account selectByPrimaryKey(Integer accountId);
    List<Account> selectAllAccount();
    List<Account> selectBySituation(Account account);

    int updateByPrimaryKeySelective(Account account);

    int updateByPrimaryKey(Account record);

}