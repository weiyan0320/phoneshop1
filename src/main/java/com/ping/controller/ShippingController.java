package com.ping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.pojo.Shipping;
import com.ping.pojo.User;
import com.ping.service.UserService;

/**
 * 用户收货地址

 */
@Controller
public class ShippingController {
	@Autowired
	private UserService userService;
	
	/**
	 * 显示收货地址页面
	 */
	@RequestMapping("/showshippinginfo")
	public String showPassword(HttpServletRequest request,Model model)
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
		
		//查询该用户所有收货地址
		List<Shipping> shipinfoList=userService.selectUserAllShipping(user.getUid());
		model.addAttribute("shipinfoList", shipinfoList);
		return "shipping_info";
	}
	
	
	/**
	 * 设置默认收货地址
	 */
	@RequestMapping("/updateDefaultShip")
	public String updateDefaultShip(HttpServletRequest request,Shipping shipobj)
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
		//查询当前用户所有收货地址
		Integer uid = user.getUid();
		List<Shipping> shipinfoList=userService.selectUserAllShipping(user.getUid());
		//遍历更新用户所有地址为非默认0
		if(shipinfoList!=null && shipinfoList.size()>0) {
			for(Shipping ship:shipinfoList ) {
				ship.setDefault_flag("0");//非默认
				userService.updateShipDefaultFlag(ship);
			}
		}
		//设置当前为默认地址
		shipobj.setDefault_flag("1");
		userService.updateShipDefaultFlag(shipobj);
		//重定向到地址管理页面
		return "redirect:showshippinginfo";
	}
	
	/**
	 * 删除收货地址
	 */
	@RequestMapping("/deleteShip")
	public String deleteShip(HttpServletRequest request,Shipping shipobj)
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
		//删除收货地址
		userService.deleteShip(shipobj);
		
		//重定向到地址管理页面
		return "redirect:showshippinginfo";
	}
	
	/**
	 * 显示添加页面
	 */
	@RequestMapping("/showaddship")
	public String showaddship(HttpServletRequest request)
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
		return "add_shipping";
	}
	
	/**
	 * 添加用户收货地址信息
	 */
	@RequestMapping("/addship")
	@ResponseBody
	public boolean addship(Shipping shipobj, HttpServletRequest request) {
		//1.获取当前登陆用户
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    //2.查询该用户的收货地址信息
	    List<Shipping> shipinfoList=userService.selectUserAllShipping(user.getUid());
	    //3.如果不存在设置为默认地址，否则非默认
	    if(shipinfoList!=null && shipinfoList.size()>0) {
	    	shipobj.setUid(user.getUid());//保存当前用户id
	    	shipobj.setDefault_flag("0");
	    }else {  //如果没有地址，设置为默认地址
	    	shipobj.setUid(user.getUid());//保存当前用户id
	    	shipobj.setDefault_flag("1");
	    }
	    boolean isNotSuccess=false;
	    //保存插入收货地址信息
    	isNotSuccess=userService.saveShippingInfo(shipobj);
    	
		return isNotSuccess;
	}
	
	/**
	 * 回显修改收货地址页面
	 */
	@RequestMapping("/showeditship")
	public String showeditship(Shipping shipobj,HttpServletRequest request,Model model)
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
		Shipping ship=userService.selectUserShippingInfoById(shipobj);//根据id查询收货地址信息
		model.addAttribute("ship", ship);
		return "edit_shipping";
	}
	
	/**
	 * 更新用户收货地址信息
	 */
	@RequestMapping("/updateship")
	@ResponseBody
	public boolean updateship(Shipping shipobj, HttpServletRequest request) {
		//1.获取当前登陆用户
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    //3.判断查询对象是否为空，如果为空，保存插入一条收货地址，否则更新该条收货地址信息
	    boolean isNotSuccess=false;
    	//更新收货地址信息
    	isNotSuccess =userService.updateShippingInfo(shipobj);
		return isNotSuccess;
	}
}
