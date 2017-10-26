package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dllo on 17/10/21.
 */
@Controller
public class CostController {

    @Resource
    private CostService costService;

    @RequestMapping(value = "/toModi")
    public String toModi(){
        return "fee/fee_modi";
    }
    @RequestMapping(value = "/toDetail")
    public String toDetail(){
        return "fee/fee_detail";
    }
    @RequestMapping(value = "/toAdd")
    public String toAdd(){
        return "fee/fee_add";
    }
    @RequestMapping(value = "/toList")
    public String toList(){
        return "fee/fee_list";
    }

    @ResponseBody
    @RequestMapping("/selectAllCost")
    public AjaxResult selectAllCost(){
        List<Cost> costs = costService.selectAllCost();

        return new AjaxResult(costs);
    }

    @ResponseBody
    @RequestMapping("/orderAllCostAsc")
    public AjaxResult orderAllCostAsc(HttpServletRequest request){
        List<Cost> costs = costService.orderAllCostAsc();
        return new AjaxResult(costs);
    }

    @ResponseBody
    @RequestMapping("/orderAllCostDesc")
    public AjaxResult orderAllCostDesc(HttpServletRequest request){
        List<Cost> costs = costService.orderAllCostDesc();
        return new AjaxResult(costs);
    }


    @ResponseBody
    @RequestMapping("/orderAllDurationAsc")
    public AjaxResult orderAllDurationAsc(HttpServletRequest request){
        List<Cost> costs = costService.orderAllDurationAsc();
        return new AjaxResult(costs);
    }

    @ResponseBody
    @RequestMapping("/orderAllDurationDesc")
    public AjaxResult orderAllDurationDesc(HttpServletRequest request){
        List<Cost> costs = costService.orderAllDurationDesc();
        return new AjaxResult(costs);
    }

    @ResponseBody
    @RequestMapping("/delCost")
    public AjaxResult delCost(Cost cost){
        costService.delCost(cost);

        return new AjaxResult(cost.getCostId());
    }

    @ResponseBody
    @RequestMapping("/addCost")
    public AjaxResult addCost(){

        return new AjaxResult(1);
    }
    @ResponseBody
    @RequestMapping("/detailBack")
    public AjaxResult detailBack(){

        return new AjaxResult(1);
    }

    @ResponseBody
    @RequestMapping(value = "/goback",method = RequestMethod.POST)
    public AjaxResult goback(){

        return new AjaxResult(1);
    }

    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public AjaxResult goback(HttpServletRequest request,Cost cost){
        Cost cost1 = costService.selectByName(cost);
        request.getSession().setAttribute("detail",cost1);

        return new AjaxResult(1);
    }
    @ResponseBody
    @RequestMapping(value = "/getDetail",method = RequestMethod.POST)
    public AjaxResult goback(HttpServletRequest request){

        Cost detail = (Cost) request.getSession().getAttribute("detail");
        return new AjaxResult(detail);
    }

    @ResponseBody
    @RequestMapping(value = "/checkFeeName",method =RequestMethod.POST)
    public AjaxResult checkFeeName(Cost cost){
        Cost cost1 = costService.selectByName(cost);
        return new AjaxResult(cost1);
    }

    @ResponseBody
    @RequestMapping(value = "/addFee",method = RequestMethod.POST)
    public AjaxResult addFee(Cost cost){
      costService.inseting(cost);
        return new AjaxResult(1);
    }

    @ResponseBody
    @RequestMapping(value = "/updateFee",method = RequestMethod.POST)
    public AjaxResult updateFee(Cost cost){
        int i = costService.updateByPrimaryKeySelected(cost);
        System.out.println(i);
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/goBack")
    public String goBack(){
        return "fee/fee_list";
    }

    @ResponseBody
    @RequestMapping(value = "/costPage")
    public AjaxResult studentList(@RequestParam("no") Integer pageNo, @RequestParam("size") Integer pageSize) {

        return new AjaxResult(costService.findWithPageInfo(pageNo, pageSize));

    }
    @ResponseBody
    @RequestMapping(value = "/pageinfo", method = RequestMethod.POST)
    public PageInfo<Cost> pageInfo(@RequestParam("pagesize") Integer pageSize) {


        return costService.getPageinfo(pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/startFee")
    public AjaxResult startFee(Cost cost){
        cost.setStatus("1");
        costService.updateByPrimaryKey(cost);
        return new AjaxResult(cost.getCostId());
    }

    @ResponseBody
    @RequestMapping(value = "/modiCost")
    public AjaxResult modiCost(HttpServletRequest request,Cost cost){
        Cost cost1 = costService.selectByPrimaryKey(cost);
        request.getSession().setAttribute("UpdateCost",cost1);
        return new AjaxResult(1);
    }

    @ResponseBody
    @RequestMapping(value = "/modicost",method = RequestMethod.POST)
    public AjaxResult modicost(){

        return new AjaxResult(1);
    }

    @ResponseBody
    @RequestMapping(value = "/getId")
    public AjaxResult getId(HttpServletRequest request){

        Cost cost = (Cost) request.getSession().getAttribute("UpdateCost");
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/getAllAccountName",method = RequestMethod.POST)
    public AjaxResult getAllAccountName(){
        List<Cost> costs = costService.selectAllCost();
        return new AjaxResult(costs);
    }

//    Service中修改
    @ResponseBody
    @RequestMapping(value = "/selecetCostIdByCostName",method = RequestMethod.POST)
    public AjaxResult selecetCostIdByCostName(Cost cost){
        Cost cost1 = costService.selectByName(cost);
        return new AjaxResult(cost1);
    }
}
