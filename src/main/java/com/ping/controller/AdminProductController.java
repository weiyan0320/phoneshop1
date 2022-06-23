package com.ping.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ping.common.utils.UUIDUtil;
import com.ping.pojo.Product;
import com.ping.service.AdminProductService;


@Controller
public class AdminProductController {

	@Autowired
	private AdminProductService adminProductService;
	/**
	 * 
	
	* <p>Description:查询首页手机销量 </p>  
	

	 */
	@RequestMapping("findProductByVolume")
	@ResponseBody
	public JSONObject findByVolume(){
		List<Product> productList = adminProductService.findProductByVolume(5);
		String[] name=new String[5];
		Integer[] volume=new Integer[5];
		for(int i=0;i<productList.size();i++){
			name[i]=productList.get(i).getPname();
			volume[i]=productList.get(i).getVolume();
		}
		JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("volume", volume);
		return obj;
	}
	/**
	 * 
	
	* <p>Description:查看商品信息 </p>  
	
	 */
	@RequestMapping("findBySplitPage")
	@ResponseBody
	public JSONObject findBySplitPage(Integer page,Integer limit,String keyword){
		PageInfo<Product> info = adminProductService.findBySplitPage(page, limit,keyword);
		JSONObject obj=new JSONObject();
		obj.put("msg", "");
		obj.put("code", 0);
		obj.put("count", info.getTotal());
		obj.put("data", info.getList());
		return obj;
	}
	
	/**
	 * 
	
	* <p>Description: 商品上架，通过商品的pid更新商品信息设置pflag=1表示商品上架</p>  
	
	
	 */
	@RequestMapping("/publishProductInfo")
	@ResponseBody
	public String getPublishProductInfo(String pid)
	{
	    //将数组传输到service层
	  	int row=adminProductService.updatePublishProductInfoByPid(pid);
	    //如果row>0表示删除成功，否则失败
  		if(row>0)
  		{   
  			return "success";
  		}else
  		{
  			return "fail";
  		}
	}
	
	/**
	 * 
	
	* <p>Description:商品下架，通过商品的pid更新商品信息设置pflag=0表示商品下架 </p>  
	
	
	 */
	@RequestMapping("/unpublishProductInfo")
	@ResponseBody
	public String getUnpublishProductInfo(String pid)
	{

	    //将数组传输到service层
	  	int row=adminProductService.updateUnpublishProductInfoByPid(pid);
	    //如果row>0表示商品下架成功，否则失败
  		if(row>0)
  		{  
  			return "success";
  		}else
  		{
  			return "fail";
  		}
	}
	/**
	 * 
	
	* <p>Description: 添加商品</p>  

	 */
	@RequestMapping("toAddProduct")
	@ResponseBody
	public String addProduct(Product product){
		product.setPflag(1);//上架
		product.setVolume(0);//销售默认为0
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String pdate = dateFormat.format(new Date());
		product.setPdate(pdate);
		product.setPimage("img/upload/"+product.getPimage());
		Integer rs = adminProductService.addProduct(product);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 
	
	* <p>Description:上传图片 </p>  
	
	 */
	@RequestMapping(value="uploadImg",method={RequestMethod.POST})
	@ResponseBody
	public Object uploadGoodsImg(@PathVariable(value="file")MultipartFile file,HttpServletRequest request){
		String str = file.getOriginalFilename();
		String name=UUIDUtil.getUUID()+str.substring(str.lastIndexOf("."));
		String path=request.getSession().getServletContext().getRealPath("/img/upload")+"/"+name;
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		JSONObject obj=new JSONObject();
		obj.put("src", name);
		return obj;
	}
	
	/**
	 * 
	
	* <p>Description: 商品删除</p>  
	
	
	 */
	@RequestMapping("deleteProduct")
	@ResponseBody
	public String deleteProduct(Integer pid){
		Integer rs = adminProductService.deleteProduct(pid);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 
	
	* <p>Description: 更新商品</p>  
	
	 */
	@RequestMapping("updateProduct")
	@ResponseBody
	public String updateProduct(Product product){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String pdate = dateFormat.format(new Date());
		product.setPdate(pdate);
		if(product!=null && product.getPimage()!=null && !"".equals(product.getPimage())) {
			product.setPimage("img/upload/"+product.getPimage());
		}
		Integer rs = adminProductService.updateProduct(product);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
}
