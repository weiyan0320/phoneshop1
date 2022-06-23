package com.ping.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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
import com.ping.pojo.User;
import com.ping.service.AdminUserService;


@Controller
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	/**
	 * 
	
	* <p>Description: 查询所有用户</p>  
	
	 */
	@RequestMapping("findAllUser")
	@ResponseBody
	public JSONObject findAllUser(Integer page,Integer limit,String keyword){
		PageInfo<User> info = adminUserService.findAllUsersBySplitPage(page,limit,keyword);
		JSONObject obj=new JSONObject();
		obj.put("msg", "");
		obj.put("code", 0);
		obj.put("count",info.getTotal());
		obj.put("data", info.getList());
		return obj;
	}
	
	/**
	 * 
	
	* <p>Description: 删除用户</p>  
	
	
	 */
	@RequestMapping("deleteAdminUser")
	@ResponseBody
	public String deleteUser(Integer uid){
		Integer rs = adminUserService.deleteUser(uid);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 
	
	* <p>Description: 上传用户图片</p>  
	
	 */
	@RequestMapping(value="uploadAdminUserImg",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject uploadHeaderPic(@PathVariable(value="file")MultipartFile file,HttpServletRequest request){
		String str = file.getOriginalFilename();
		String name=UUIDUtil.getUUID()+str.substring(str.lastIndexOf("."));
		String path=request.getSession().getServletContext().getRealPath("/img/upload")+"/"+name;
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		JSONObject obj=new JSONObject();
		obj.put("userImg", name);
		return obj;
	}
	
	/**
	 * 
	
	* <p>Description:修改用户资料 </p>  
	
	
	 */
	@RequestMapping("updateAdminUser")
	@ResponseBody
	public String updateUser(User user){
		if(user !=null && user.getUserimg()!=null && !"".equals(user.getUserimg())) {
			user.setUserimg("img/upload/"+user.getUserimg());
		}
		Integer rs = adminUserService.updateUserInfo(user);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}

}
