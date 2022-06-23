package com.ping.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ping.pojo.Admins;
import com.ping.pojo.Product;
import com.ping.service.AdminService;
/**
 * 后台登录、退出

 */
@Controller
@Scope("prototype")
public class AdminController {
	@Autowired
	private AdminService adminService;
	//登录
	@RequestMapping("/toAdminLogin")
	@ResponseBody
	public String login(String adminName,String adminPass,HttpServletRequest request){
		Admins admin = adminService.login(adminName, adminPass);
		HttpSession session = request.getSession();
		if(admin!=null){
			session.setAttribute("admin", admin);
			return "success";
		}
		return "fail";
	}
	//退出
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		return "forward:/adminLogin";
	}
	//修改密码
	@RequestMapping("toUpdatePass")
	@ResponseBody
	public String updatePass(String oldPass,String newPass,HttpServletRequest request){
		HttpSession session = request.getSession();
		Admins admin = (Admins) session.getAttribute("admin");
		if(oldPass.equals(admin.getAdminPass())){
			admin.setAdminPass(newPass);
			adminService.updateAdmin(admin);
			session.removeAttribute("admin");
			return "success";
		}
		return "fail";
	}
	
	//修改资料
	@RequestMapping("updateAdmin")
	@ResponseBody
	public String updateAdmin(Admins admin,HttpServletRequest request){
		admin.setAdminDate(new Date());
		Integer rs = adminService.updateAdmin(admin);
		if(rs>0){
			HttpSession session = request.getSession();
			//更新session中admin
			Admins admins = adminService.findAdminById(admin.getAdminId());
			session.setAttribute("admin", admins);
			return "success";
		}else{
			return "fail";
		}
	}
}
