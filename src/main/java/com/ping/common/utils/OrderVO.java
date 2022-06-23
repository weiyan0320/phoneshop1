package com.ping.common.utils;

public class OrderVO {
	private Long totalMoney;//总金额
	private String orderMonth;//月份
	private Integer sheets;//总订单数量
	public Long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getOrderMonth() {
		return orderMonth;
	}
	public void setOrderMonth(String orderMonth) {
		this.orderMonth = orderMonth;
	}
	public Integer getSheets() {
		return sheets;
	}
	public void setSheets(Integer sheets) {
		this.sheets = sheets;
	}
	public OrderVO(Long totalMoney, String orderMonth, Integer sheets) {
		super();
		this.totalMoney = totalMoney;
		this.orderMonth = orderMonth;
		this.sheets = sheets;
	}
	public OrderVO() {
		super();
	}
}
