package com.ping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ping.common.utils.OrderSearchVO;
import com.ping.common.utils.OrderVO;
import com.ping.pojo.Order;

public interface AdminOrderMapper {
	Integer findTotalOrder();
	Integer findTotalDeliverOrder(int status);
	List<OrderVO> findTotalMoneyByMonth(Integer limit);
	List<Order> findAllOrderBySearchVO(OrderSearchVO vo);
	List<Order> findAllOrder();
	Order findOrderById(String oid);
	Integer deleteOrder(String orderId);
	Integer deliverOrder(@Param("oid") String oid, @Param("express_no") String express_no);
}
