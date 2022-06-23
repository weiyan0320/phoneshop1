package com.ping.service;

import com.github.pagehelper.PageInfo;
import com.ping.pojo.Evaluate;

public interface AdminEvaluateService {

	//查询所有商品评价
	PageInfo<Evaluate> findAllEvaluate(Integer page, Integer limit, String keyword);

	Evaluate findEvaluteById(Integer evaId);

	Integer deleteEvaluate(Integer evaId);

}
