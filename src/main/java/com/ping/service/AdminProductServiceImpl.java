package com.ping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ping.mapper.AdminProductMapper;
import com.ping.pojo.Product;

@Service
public class AdminProductServiceImpl implements AdminProductService{

	@Autowired
	private AdminProductMapper adminProductMapper;
	
	@Override
	public List<Product> findProductByVolume(int limit) {
		// TODO Auto-generated method stub
		return adminProductMapper.findProductByVolume(limit);
	}

	//分页查询商品信息
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public PageInfo<Product> findBySplitPage(Integer page,Integer size,String keyword){
		List<Product> list =new ArrayList<Product>();
		PageHelper.startPage(page, size);//1.pagehelper分页实现
		if(keyword!=null &&!keyword.trim().equals("")){ //2.按条件查询
			list=adminProductMapper.findProductLikeName(keyword);
		}else{
			list = adminProductMapper.findAll();
		}
		PageInfo<Product> info=new PageInfo<Product>(list);//3.封装信息
		return info;
	}

	//商品上架
	@Override
	public int updatePublishProductInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return adminProductMapper.updatePublishProductInfoByPid(Integer.parseInt(pid));
	}

	//商品下架
	@Override
	public int updateUnpublishProductInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return adminProductMapper.updateUnpublishProductInfoByPid(Integer.parseInt(pid));
	}

	//添加商品
	@Override
	public Integer addProduct(Product product) {
		// TODO Auto-generated method stub
		Integer rs = adminProductMapper.saveProduct(product);
		return rs;
	}

	//删除商品
	@Override
	public Integer deleteProduct(Integer pid) {
		Integer rs = adminProductMapper.deleteProduct(pid);
		return rs;
	}

	//更新商品
	@Override
	public Integer updateProduct(Product product) {
		Integer rs = adminProductMapper.updateProduct(product);
		return rs;
	}

}
