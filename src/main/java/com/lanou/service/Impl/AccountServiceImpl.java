package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    //显示全部Account
    public List<Account> selectAllAccount() {
        List<Account> accounts = accountMapper.selectAllAccount();

        return accounts;
    }

    public List<Account> selectBySituation(Account account) {
        List<Account> accounts = accountMapper.selectBySituation(account);
        return accounts;
    }

    //添加
    public void insertSelect(Account account) {
       accountMapper.insertSelective(account);

    }

    public int deleteByPrimaryKey(Account account) {
        int i = accountMapper.deleteByPrimaryKey(account.getAccountId());

        return i;
    }

    public Account selectByPrimaryKey(Account account) {
        Account account1 = accountMapper.selectByPrimaryKey(account.getAccountId());
        return account1;
    }

    public Account selectByIdCard(Account account) {
        Account account1 = accountMapper.selectByIdCard(account);
        return account1;
    }

    public int updateByPrimaryKeySelected(Account account) {
        int i = accountMapper.updateByPrimaryKeySelective(account);

        return i;
    }

    public PageInfo<Account>getPageinfo(Integer pageSize) {
        return queryStudentByPage(null,pageSize);
    }
    public PageInfo<Account>queryStudentByPage(Integer pageNo, Integer pageSize){

//        判断参数的合法性
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?5:pageSize;

        PageHelper.startPage(pageNo,pageSize);
//        获取全部学员
        List<Account>studentList=accountMapper.selectAllAccount();
//        使用pageInfo对结果进行包装
        PageInfo<Account>pageInfo=new PageInfo<Account>(studentList);

        return pageInfo;
    }
}
