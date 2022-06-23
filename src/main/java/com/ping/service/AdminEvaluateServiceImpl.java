package com.ping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ping.mapper.AdminEvaluateMapper;
import com.ping.mapper.EvaimgMapper;
import com.ping.pojo.Evaimg;
import com.ping.pojo.Evaluate;

@Service
public class AdminEvaluateServiceImpl implements AdminEvaluateService {

	@Autowired
	private AdminEvaluateMapper  adminEvaluateMapper;
	@Autowired
	private EvaimgMapper evaImgMapper;
	
	//查询所有商品评价
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public PageInfo<Evaluate> findAllEvaluate(Integer page,Integer limit,String keyword) {
		PageHelper.startPage(page, limit);
		List<Evaluate> evaList=new ArrayList<Evaluate>();
		if(keyword!=null&&!keyword.trim().equals("")){
			evaList=adminEvaluateMapper.findAllEvaluteLikeContent(keyword);
		}else{
			evaList = adminEvaluateMapper.findAllEvalute();
		}
		PageInfo<Evaluate> info=new PageInfo<Evaluate>(evaList);
		return info;
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Evaluate findEvaluteById(Integer evaId) {
		Evaluate eva = adminEvaluateMapper.findEvaById(evaId);
		List<Evaimg> imgList = evaImgMapper.findEvaimgByEvaId(eva.getEva_Id());
		eva.setImgList(imgList);
		return eva;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteEvaluate(Integer evaId) {
		return  adminEvaluateMapper.deleteEvaluate(evaId);
	}
}
