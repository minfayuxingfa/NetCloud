package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.service.AccountService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 17/10/23.
 */
@Controller
public class AccountController {
    @Resource
    private AccountService accountService;

    //去账务账号
    @RequestMapping(value = "/toAccount")
    public String toAccount() {
        return "/account/account_list";
    }

    @ResponseBody
    @RequestMapping(value = "/accountList")
    public AjaxResult accountList() {
        return new AjaxResult(1);
    }

    //查询所有Account
    @ResponseBody
    @RequestMapping(value = "/selectAllAccount")
    public AjaxResult selectAllAccount() {

        List<Account> accounts = accountService.selectAllAccount();

        return new AjaxResult(accounts);
    }

    //添加Account
    @ResponseBody
    @RequestMapping(value = "/addAccount")
    public AjaxResult addAccount() {
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/toAccountAdd")
    public String toAdd() {
        return "/account/account_add";
    }

    //    取消
    @ResponseBody
    @RequestMapping(value = "/cancel")
    public AjaxResult cancel() {
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/AccountToList")
    public String toList() {
        return "/account/account_list";
    }

    //    保存
    @ResponseBody
    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public AjaxResult saveAccount(@RequestParam("rePassword") String rePassword,
                                  Account account) {
//      account.setBirthdate(new Date());
        Random random = new Random();
        int i = random.nextInt(5000);
        account.setAccountId(i);
        account.setCreateDate(new Date());
        account.setStatus("1");
        if (rePassword.equals(account.getLoginPasswd())) {
            accountService.insertSelect(account);
            return new AjaxResult(1);
        } else {

            return new AjaxResult(2);
        }
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public AjaxResult deleteAccount(Account account) {
        int i = accountService.deleteByPrimaryKey(account);
        return new AjaxResult(1);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteStatus", method = RequestMethod.POST)
    public AjaxResult deleteStatus(Account account) {
        account.setStatus("3");
        int i = accountService.updateByPrimaryKeySelected(account);
        return new AjaxResult(1);
    }

    //            修改
    @ResponseBody
    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    public AjaxResult updateAccount(HttpServletRequest request, Account account) {
        account.setCloseDate(new Date());
        Account account1 = accountService.selectByPrimaryKey(account);
        request.getSession().setAttribute("updateAccount", account1);
        return new AjaxResult(1);
    }


    @RequestMapping(value = "/toAccountUpdate")
    public String toUpdate() {
        return "/account/account_modi";
    }

    //    修改的回显
    @ResponseBody
    @RequestMapping(value = "/rollBackAccount", method = RequestMethod.POST)
    public AjaxResult rollBackAccount(HttpServletRequest request) {
        Account updateAccount = (Account) request.getSession().getAttribute("updateAccount");
        return new AjaxResult(updateAccount);
    }

    //得到birthday
    @ResponseBody
    @RequestMapping(value = "/birthday", method = RequestMethod.POST)
    public AjaxResult birthday(Account account) {
        String[] birth = new String[8];
        String birthdate = account.getBirthdate();
        String[] strings = birthdate.split("");
        String Birthday = strings[6] + strings[7] + strings[8] + strings[9] + "-" + strings[10] + strings[11]
                + "-" + strings[12] + strings[13];
        return new AjaxResult(Birthday);
    }

    //取消
    @ResponseBody
    @RequestMapping(value = "/cancelAccount", method = RequestMethod.POST)
    public AjaxResult deleteAccount() {

        return new AjaxResult(1);
    }

    //提交修改
    @ResponseBody
    @RequestMapping(value = "/saveUpdateAccount", method = RequestMethod.POST)
    public AjaxResult saveUpdateAccount(Account account) {
        int i = accountService.updateByPrimaryKeySelected(account);
        return new AjaxResult(1);
    }

    //查询详细信息
    @ResponseBody
    @RequestMapping(value = "/showDetail", method = RequestMethod.POST)
    public AjaxResult showDetail(HttpServletRequest request, Account account) {
        Account account1 = accountService.selectByPrimaryKey(account);
        request.getSession().setAttribute("detail", account1);
        return new AjaxResult(1);
    }

    //跳转到详细信息页面
    @RequestMapping(value = "/toAccountDetail")
    public String toDetail() {
        return "/account/account_detail";
    }

    //获得详细信息用于显示
    @ResponseBody
    @RequestMapping(value = "/getAccountDetail", method = RequestMethod.POST)
    public AjaxResult getAccountDetail(HttpServletRequest request) {

        Account detail = (Account) request.getSession().getAttribute("detail");
        return new AjaxResult(detail);
    }

    //显示详细信息后返回
    @ResponseBody
    @RequestMapping(value = "/detailToList", method = RequestMethod.POST)
    public AjaxResult detailToList() {
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/DetailToList")
    public String DetailToList() {
        return "/account/account_list";
    }

    //    获取分页信息
    @ResponseBody
    @RequestMapping(value = "/accountPage", method = RequestMethod.POST)
    public PageInfo<Account> accountPage(@RequestParam("pagesize") Integer pageSize) {


        return accountService.getPageinfo(pageSize);
    }

    //暂停/开通点击事件
    @ResponseBody
    @RequestMapping(value = "/setStatus", method = RequestMethod.POST)
    public AjaxResult setStatus(Account account) {
        if (account.getStatus().equals("1")) {
            account.setPauseDate(new Date());
            account.setStatus("2");
            int i = accountService.updateByPrimaryKeySelected(account);
        } else {
            account.setStatus("1");
            account.setPauseDate(null);
            int i = accountService.updateByPrimaryKeySelected(account);
        }

        return new AjaxResult(1);
    }

    //模糊查询
    @ResponseBody
    @RequestMapping(value = "/startSearch", method = RequestMethod.POST)
    public AjaxResult startSearch(HttpServletRequest request, Account account) {


        List<Account> accounts = accountService.selectBySituation(account);
        request.getSession().setAttribute("searchAccount", accounts);
        return new AjaxResult(accounts);
    }

    //模糊查询分页
    @ResponseBody
    @RequestMapping(value = "/getSearch", method = RequestMethod.POST)
    public AjaxResult getSearch(HttpServletRequest request) {


        List<Account> searchAccount = (List<Account>) request.getSession().getAttribute("searchAccount");
        return new AjaxResult(searchAccount);

    }

}