package com.ping.service;

import java.util.List;

import com.ping.pojo.Evaluate;

/**
 * 商品评价
 */
public interface IEvaluateService {
    //添加商品评价
	Integer addEvaluate(Evaluate eva, String[] imgs);
    //根据商品pid查询商品评价信息
	List<Evaluate> findEvaluateByPid(int pid);

}
