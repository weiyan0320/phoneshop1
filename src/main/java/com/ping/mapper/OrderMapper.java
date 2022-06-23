package com.ping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ping.pojo.Order;
import com.ping.pojo.OrderItem;
import com.ping.pojo.User;

/**
 * 处理订单daos
 */
public interface OrderMapper {

	//将用户提交订单信息插入数据库的订单表,返回值是int类型如果大于0说明插入成功
	public void  insertOrder(Order order);
	
	//将用户提交的订单信息也要插入订单项表（订单明细表）
	public void insertOrderItem(OrderItem orderItem);

	//根据用户的uid查询该用户的订单总条数
	public int selectOrderTotalCountByUid(User user);
    
	//根据用户的uid和当前页查询该页用户的订单信息,mybatis多个参数传递需要带
	public List<Order> selectOrderByUidAndCurrentPage(@Param("uid") int uid,@Param("currentStart") int currentStart,@Param("currentCount") int currentCount);

	//根据订单号查询该订单的所有的订单项，并封装好到list集合,还有联合商品表查询对应的商品信息
	public List<OrderItem> selectOrderItemByOid(String oid);

	//根据oid删除订单项表所有是该订单号的订单项记录
	public void deleteOrderItemByOid(String oid);
    
	//根据oid删除订单表order里面该订单记录
	public void deleteThisOrderByOid(String oid);
	
	//根据用户的uid查询该用户（待付款）的订单总条数
    public int selectReadyPayTotalCountByUid(User user);
    //根据用户的uid和当前页查询该页用户（待付款）的订单信息,mybatis多个参数传递需要带
  	public List<Order> selectReadyPayOrderByUidAndCurrentPage(@Param("uid") int uid,@Param("currentStart") int currentStart,@Param("currentCount") int currentCount);
  	
    //根据用户的uid查询该用户（待发货）的订单总条数
    public int selectReadyToDeliverTotalCountByUid(User user);
    //根据用户的uid和当前页查询该页用户（待发货）的订单信息,mybatis多个参数传递需要带
  	public List<Order> selectReadyToDeliverOrderByUidAndCurrentPage(@Param("uid") int uid,@Param("currentStart") int currentStart,@Param("currentCount") int currentCount);
  	
    //根据用户的uid查询该用户（待收货）的订单总条数
    public int selectReadyToReceiveTotalCountByUid(User user);
    //根据用户的uid和当前页查询该页用户（待收货）的订单信息,mybatis多个参数传递需要带
  	public List<Order> selectReadyToReceiveOrderByUidAndCurrentPage(@Param("uid") int uid,@Param("currentStart") int currentStart,@Param("currentCount") int currentCount);
  	
    //根据用户的uid查询该用户（待评价）的订单总条数
    public int selectReadyToEvaluateTotalCountByUid(User user);
    //根据用户的uid和当前页查询该页用户（待评价）的订单信息,mybatis多个参数传递需要带
  	public List<Order> selectReadyToEvaluateOrderByUidAndCurrentPage(@Param("uid") int uid,@Param("currentStart") int currentStart,@Param("currentCount") int currentCount);
  	
    //根据用户的uid查询该用户（已完成）的订单总条数
    public int selectFinishOrderTotalCountByUid(User user);
    //根据用户的uid和当前页查询该页用户（已完成）的订单信息,mybatis多个参数传递需要带
  	public List<Order> selectFinishOrderByUidAndCurrentPage(@Param("uid") int uid,@Param("currentStart") int currentStart,@Param("currentCount") int currentCount);
 
    //根据订单id更新订单状态
  	public void updateOrderStatusByOid(String oid);

	public Order findOrderById(@Param("oid")String oid);
    //更新订单状态为已完成
	public void evaOrder(String evaOrderId);
	
	//根据订单id更新订单状态
  	public int editOrderStatusByOid(@Param("oid") String oid,@Param("status") int status);

}
