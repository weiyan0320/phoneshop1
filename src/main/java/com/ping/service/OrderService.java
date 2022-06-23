package com.ping.service;

import java.util.List;

import com.alipay.api.domain.OrderDetail;
import com.ping.common.utils.PageBean;
import com.ping.pojo.Order;
import com.ping.pojo.OrderItem;
import com.ping.pojo.User;

/**
 * 处理订单信息
 */
public interface OrderService {

	//创建或插入用户提交订单
	public void insertOrder(Order order);
   
	/**
	 * 分页查询该用户的订单信息
	 */
    //根据用户uid和当前页信息查询该用户的当前页的订单信息，将数据封装到pageBean分页对象
	public PageBean selectOrderListByUidAndCurrentPage(User user, int currentPage, int currentCount);

	//根据订单号删除订单和订单项表里面相应的订单项
	public void deleteThisOrderByOid(String oid);
    //1.待付款
	public PageBean showReadyPayOrder(User user, int currentPage, int currentCount);
	//2.待发货
	public PageBean findReadyToDeliverOrder(User user, int currentPage, int currentCount);
	
	//3.待收货
    public PageBean findReadyToReceiveOrder(User user, int currentPage, int currentCount);
    //4.待评价
    public PageBean findReadyToEvaluateOrder(User user, int currentPage, int currentCount);
    
    //5.完成订单
    public PageBean findFinishOrder(User user, int currentPage, int currentCount);
    
    //根据订单id更新订单状态
  	public void updateOrderStatusByOid(String out_trade_no);

	public Order findOrderById(String orderNumber);

	//根据订单号查询订单项
	public List<OrderItem> selectOrderItemByOid(String evaOrderId);
    //更新订单状态为已完成
	public void evaOrder(String evaOrderId);
	//更新订单状态
	public int editOrderStatusByOid(String out_trade_no,int status);
}
