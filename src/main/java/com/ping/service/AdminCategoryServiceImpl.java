package com.ping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ping.mapper.AdminCategoryMapper;
import com.ping.pojo.Category;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

	@Autowired
	private AdminCategoryMapper adminCategoryMapper;
	
	@Override
	public List<Category> selectAllCategoryInfo() {
		//查询所有分类信息
		List<Category> categoryList=adminCategoryMapper.selectAllCategoryInfo();
		return categoryList;
	}

	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public PageInfo<Category> findCategoryBySplitPage(Integer page, Integer limit, String keyword) {
		PageHelper.startPage(page, limit);
		List<Category> list=new ArrayList<Category>();
 		if(keyword!=null&&!keyword.trim().equals("")){
			list=adminCategoryMapper.findAllCategoryByLikeName(keyword);
		}else{
			list=adminCategoryMapper.findAllCategoryBySplitPage();
		}
 		PageInfo<Category> info=new PageInfo<Category>(list);
 		return info;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer addProductCategory(Category category) {
		return adminCategoryMapper.saveProductCategory(category);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer updateProductCategory(Category category) {
		return adminCategoryMapper.updateProductCategory(category);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteProductCategory(Integer cid) {
		return adminCategoryMapper.deleteProductCategory(cid);
	}
}
