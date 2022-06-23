package com.ping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.mapper.EvaimgMapper;
import com.ping.mapper.EvaluateMapper;
import com.ping.pojo.Evaimg;
import com.ping.pojo.Evaluate;

/**
 * 商品评价
 */
@Service
public class IEvaluateServiceImpl implements IEvaluateService {

	@Autowired
	private EvaluateMapper evaluateMapper;
	
	@Autowired
	private EvaimgMapper evaImgMapper;
	//添加商品评价
	@Override
	public Integer addEvaluate(Evaluate eva,String[] imgs) {
		Integer rs = evaluateMapper.addEvaluate(eva);
		if(rs>0){
			for (String img : imgs) {
				Evaimg evaimg=new Evaimg("img/upload/"+img, eva.getEva_Id());
				evaImgMapper.addEvaimg(evaimg);
			}
		}
		return rs;
	}
	//根据商品pid查询商品评价信息
	@Override
	public List<Evaluate> findEvaluateByPid(int pid) {
		List<Evaluate> evaList = evaluateMapper.findEvaByPid(pid);
		for (Evaluate evaluate : evaList) {
			List<Evaimg> imgList = evaImgMapper.findEvaimgByEvaId(evaluate.getEva_Id());
			evaluate.setImgList(imgList);
		}
		return evaList;
	}
	
}
