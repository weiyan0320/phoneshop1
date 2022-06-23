package com.ping.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ping.common.utils.OrderSearchVO;
import com.ping.common.utils.OrderVO;
import com.ping.pojo.Order;
import com.ping.service.AdminOrderService;

/** 
* 后台订单管理类
*/
@Controller
public class AdminOrderController {
	
	@Autowired
	private AdminOrderService adminOrderService;

	/**
	 * 
	* <p>Description: 后台首页查询订单总量和待发货</p>  
	
	 */
	@RequestMapping("findToDo")
	@ResponseBody
	public JSONObject findToDo(){
		Integer totalOrder = adminOrderService.findTotalOrder();
		Integer totalDediver1 = adminOrderService.findTotalDeliverOrder(2);
		Integer totalDediver2 = adminOrderService.findTotalDeliverOrder(3);
		Integer totalDediver3 = adminOrderService.findTotalDeliverOrder(4);
	
		JSONObject obj=new JSONObject();
		obj.put("total", totalOrder);
		obj.put("deliver1", totalDediver1);//待发货
		obj.put("deliver2", totalDediver2);//待收货
		obj.put("deliver3", totalDediver3);//待评价
		return obj;
	}
	
	@RequestMapping("findTotalOrder")
	@ResponseBody
	public JSONObject findOrderTotalMoney(){
		List<OrderVO> list = adminOrderService.findTotalMoneyByMonth(6);
		String[] month=new String[6];
		Long[] total=new Long[6];
		Integer[] sheets=new Integer[6];
		for(int i=0;i<list.size();i++){
			month[i]=list.get(i).getOrderMonth();
			total[i]=list.get(i).getTotalMoney();
			sheets[i]=list.get(i).getSheets();
		}
		JSONObject obj=new JSONObject();
		obj.put("month", month);
		obj.put("total", total);
		obj.put("sheets", sheets);
		return obj;
	}
	
	/**
	 * 
	
	* <p>Description: 订单列表信息</p>  

	 */
	@RequestMapping("findOrderBySplitPage")
	@ResponseBody
	public JSONObject findOrderBySplitPage(Integer page,Integer limit,OrderSearchVO vo){
		if(vo!=null){
			System.out.println(vo.getOrderState()+"========================");
		}
		PageInfo<Order> info = adminOrderService.findOrdersBySplitPage(page, limit,vo);
		JSONObject obj=new JSONObject();
		obj.put("msg", "");
		obj.put("code", 0);
		obj.put("count", info.getTotal());
		obj.put("data", info.getList());
		return obj;
	}
	/**
	 * 
	
	* <p>Description:查看订单信息 </p>  

	 */
	@RequestMapping("findOrderById")
	@ResponseBody
	public Order findOrderById(String oid){
		Order order = adminOrderService.findOrderById(oid);
		return order;
	}
	/**
	 * 
	
	* <p>Description: 删除订单</p>  
	

	 */
	@RequestMapping("deleteOrder")
	@ResponseBody
	public String deleteOrder(String oid){
		Integer rs = adminOrderService.deleteOrder(oid);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 
	
	* <p>Description: 发货</p>  
	
	
	 */
	@RequestMapping("deliverOrder")
	@ResponseBody
	public String deliverOrder(String oid,String express_no){
		if(!express_no.trim().equals("")){
			Integer rs = adminOrderService.deliverOrder(oid, express_no);
			if(rs>0){
				return "success";
			}else{
				return "fail";
			}
		}else{
			return "fail";
		}
	}
	
}
