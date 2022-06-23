package com.ping.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ping.common.utils.OrderSearchVO;
import com.ping.common.utils.OrderVO;
import com.ping.mapper.AdminOrderMapper;
import com.ping.mapper.OrderMapper;
import com.ping.pojo.Order;
import com.ping.pojo.OrderItem;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
	@Autowired
	private AdminOrderMapper adminOrderMapper;
	@Autowired
	private OrderMapper orderMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Integer findTotalOrder() {
		return adminOrderMapper.findTotalOrder();
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Integer findTotalDeliverOrder(int status) {
		return adminOrderMapper.findTotalDeliverOrder(status);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public List<OrderVO> findTotalMoneyByMonth(Integer limit) {
		return adminOrderMapper.findTotalMoneyByMonth(limit);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public PageInfo<Order> findOrdersBySplitPage(Integer page, Integer limit,OrderSearchVO vo) {
		PageHelper.startPage(page, limit);
		List<Order> list =new ArrayList<Order>();
		if(vo!=null){
			list = adminOrderMapper.findAllOrderBySearchVO(vo);
		}else{
			list = adminOrderMapper.findAllOrder();
		}
		//格式化订单日期
		if(list!=null && list.size()>0) {
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<list.size();i++) {
				Order order = list.get(i);
				try {
					order.setOrdertime(sdf.format(sdf.parse(order.getOrdertime())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		PageInfo<Order> info=new PageInfo<Order>(list);
		return info;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Order findOrderById(String oid) {
		Order order = adminOrderMapper.findOrderById(oid);
		List<OrderItem> list = orderMapper.selectOrderItemByOid(order.getOid());
		order.setOrderItems(list);;
		return order;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteOrder(String orderId) {
		return adminOrderMapper.deleteOrder(orderId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deliverOrder(String oid, String express_no) {
		return adminOrderMapper.deliverOrder(oid, express_no);
	}
}
