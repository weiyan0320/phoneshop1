package com.ping.mapper;

import java.util.List;

import com.ping.pojo.Evaluate;

/**
 * 商品评价

 */
public interface EvaluateMapper {

	//添加商品评价
	Integer addEvaluate(Evaluate eva);
    //根据商品pid查询商品评价信息
	List<Evaluate> findEvaByPid(int pid);

}
