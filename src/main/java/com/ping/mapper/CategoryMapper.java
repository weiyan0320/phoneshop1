package com.ping.mapper;

import java.util.List;

import com.ping.pojo.Category;

/**
 * 商品分类显示
 */
public interface CategoryMapper {

	/**
	 * 查询分类表中所有商品分类信息
	 * 
	 */
	public List<Category> selectCategoryList();
}
