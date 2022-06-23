package com.ping.mapper;

import java.util.List;

import com.ping.pojo.Product;

public interface AdminProductMapper {

	List<Product> findProductByVolume(int limit);

	//根据商品名称查询
	List<Product> findProductLikeName(String keyword);

	//查询所有商品信息
	List<Product> findAll();

	int updatePublishProductInfoByPid(int pid);

	int updateUnpublishProductInfoByPid(int pid);

	//添加商品
	int saveProduct(Product product);

	//删除商品
	int deleteProduct(Integer pid);

	//更新商品
	int updateProduct(Product product);

}
