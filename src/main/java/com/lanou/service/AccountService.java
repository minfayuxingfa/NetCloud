package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
public interface  AccountService {
List<Account> selectAllAccount();
List<Account> selectBySituation(Account account);

void insertSelect(Account account);
int deleteByPrimaryKey(Account account);
Account selectByPrimaryKey(Account account);
Account selectByIdCard(Account account);
int updateByPrimaryKeySelected(Account account);
    PageInfo<Account> getPageinfo(Integer pageSize);
}
