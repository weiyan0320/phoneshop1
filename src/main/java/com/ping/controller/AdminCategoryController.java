package com.ping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ping.pojo.Category;
import com.ping.service.AdminCategoryService;

@Controller
public class AdminCategoryController {
	
	@Autowired
	private AdminCategoryService adminCategoryService;
	
	/**
	 * 
	
	* <p>Description: 管理员添加商品时候，要进行页面商品分类信息回显，供用户选择</p>  

	 */
	@RequestMapping("/selectAllCategoryInfo")
	@ResponseBody
	public List<Category> selectAllCategoryInfo()
	{
		List<Category> categoryList=adminCategoryService.selectAllCategoryInfo();
		return categoryList;
	}

	/**
	 * 
	
	* <p>Description: </p>  
	
	 */
	@RequestMapping("findCategoryBySplitPage")
	@ResponseBody
	public JSONObject findCategoryBySplitPage(Integer page,Integer limit,String keyword){
		PageInfo<Category> info = adminCategoryService.findCategoryBySplitPage(page, limit, keyword);
		JSONObject obj=new JSONObject();
		obj.put("code", 0);
		obj.put("msg", "");
		obj.put("count", info.getTotal());
		obj.put("data", info.getList());
		return obj;
	}
	
	/**
	 * 
	
	* <p>Description: </p>  
	

	 */
	@RequestMapping("addProductCategory")
	@ResponseBody
	public String addProductCategory(Category category){
		Integer rs = adminCategoryService.addProductCategory(category);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}

}
