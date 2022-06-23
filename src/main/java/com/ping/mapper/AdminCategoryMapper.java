package com.ping.mapper;

import java.util.List;

import com.ping.pojo.Category;

public interface AdminCategoryMapper {

	//查询所有分类信息
	List<Category> selectAllCategoryInfo();

	List<Category> findAllCategoryByLikeName(String keyword);

	List<Category> findAllCategoryBySplitPage();

	Integer saveProductCategory(Category category);

	Integer updateProductCategory(Category category);

	Integer deleteProductCategory(Integer cid);

}
