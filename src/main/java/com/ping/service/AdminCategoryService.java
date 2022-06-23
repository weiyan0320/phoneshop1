package com.ping.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ping.pojo.Category;

public interface AdminCategoryService {

	//查询所有分类信息
	List<Category> selectAllCategoryInfo();

	//分页查询商品分类
	PageInfo<Category> findCategoryBySplitPage(Integer page, Integer limit, String keyword);

	Integer addProductCategory(Category category);

	Integer updateProductCategory(Category category);

	Integer deleteProductCategory(Integer cid);

}
