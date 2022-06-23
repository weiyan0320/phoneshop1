package com.ping.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ping.common.utils.OrderSearchVO;
import com.ping.common.utils.OrderVO;
import com.ping.pojo.Order;


public interface AdminOrderService {
	//订单总量
	Integer findTotalOrder();
	//待发货总量
	Integer findTotalDeliverOrder(int status);
	
	List<OrderVO> findTotalMoneyByMonth(Integer limit);
	
	PageInfo<Order> findOrdersBySplitPage(Integer page, Integer limit, OrderSearchVO vo);
	
	Order findOrderById(String oid);
	
	Integer deleteOrder(String oid);
	
	Integer deliverOrder(String oid, String express_no);
	
}
