package com.ping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.common.utils.AESUtils;
import com.ping.pojo.User;
import com.ping.service.UserService;

/**
 * 修改密码

 */
@Controller
public class ChangePasswordController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 显示修改密码页面
	 */
	@RequestMapping("/showpassword")
	public String showPassword(HttpServletRequest request)
	{
		//1.判断用户是否登陆，如果没有跳转到登陆页面
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
		//首先判断用户是否登录如果用户没有登录重定向到登录界面
		if(user==null)
		{
			//用户没有登录，重定向到登录界面，下面代码不执行
			return "redirect:login";
		}
		return "changepassword";
	}
	
	/**
	 * 异步校验原密码是否正确
	 */
	@RequestMapping("/checkOldPassword")
	@ResponseBody
	public boolean checkOldPassword(String inputpassword,HttpServletRequest request)
	{
		
		//1.获取当前登陆用户的密码
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    String dbPassword="";
	    if(user!=null) {
	    	User dbUser=userService.selectUserById(user.getUid());//获取该用户的信息
	    	dbPassword = dbUser.getPassword();
	    	try {
	    		dbPassword=AESUtils.decrypt(dbPassword);//解密
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    boolean checkResult=false;
	    //2.判断输入的密码和当前用户密码是否相等
	    if(dbPassword.equals(inputpassword)) {
	    	checkResult=false;
	    }else {
	    	checkResult=true;
	    }
	    return checkResult;
	}
	
	/**
	 * 更新修改密码
	 */
	@RequestMapping("/changePassword")
	@ResponseBody
	public boolean saveChangePassword(String password,HttpServletRequest request) {
		//1.获取当前登陆用户的密码
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    boolean isNotSuccess=false;
	    if(user!=null) {
	    	try {
	    		//加密
				user.setPassword(AESUtils.encryption(password));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//保存新修改的密码
	    	isNotSuccess =userService.updateChangePassword(user);
	    }
		
		return isNotSuccess;
		
	}
}
