package com.ping.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ping.pojo.Product;

public interface AdminProductService {

	List<Product> findProductByVolume(int i);

	//分页查询商品信息
	PageInfo<Product> findBySplitPage(Integer page, Integer limit, String keyword);

	//上架
	int updatePublishProductInfoByPid(String pid);

	//下架
	int updateUnpublishProductInfoByPid(String pid);

	//添加商品
	Integer addProduct(Product product);

	//删除商品
	Integer deleteProduct(Integer pid);

	//更新商品
	Integer updateProduct(Product product);

}
