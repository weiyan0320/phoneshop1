package com.ping.mapper;

import java.util.List;

import com.ping.pojo.Evaimg;

/**
 * 商品评价图片

 */
public interface EvaimgMapper {

	//添加商品评价图片
	void addEvaimg(Evaimg evaimg);
    //根据商品评价id查询对应商品图片信息
	List<Evaimg> findEvaimgByEvaId(int eva_Id);

}
