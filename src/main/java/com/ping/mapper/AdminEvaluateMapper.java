package com.ping.mapper;

import java.util.List;

import com.ping.pojo.Evaimg;
import com.ping.pojo.Evaluate;

public interface AdminEvaluateMapper {

	List<Evaluate> findAllEvaluteLikeContent(String keyword);

	List<Evaluate> findAllEvalute();

	Evaluate findEvaById(Integer evaId);

	List<Evaimg> findEvaimgByEvaId(Integer eva_Id);

	Integer deleteEvaluate(Integer evaId);

}
