package com.ping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ping.pojo.Category;
import com.ping.pojo.Product;
import com.ping.service.CategoryService;
import com.ping.service.ProductService;

@Controller
public class ViewController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	/**
	 * 
	* <p>Description:用户前台页面 </p>  
	
	 */
	//显示前台首页
	@RequestMapping("/index")
	public String showIndex(HttpServletRequest resquest,Model model) {
		//在访问首页前应该为首页显示需要准备的数据，准备好
		//请求获取session对象
		HttpSession session = resquest.getSession();
		//1.准备首页显示的商品分类数据，并且存储到session域中，以便每个jsp页面都能获取到该数据
		List<Category> categoryList = categoryService.selectCategoryList();
		//将商品分类信息数据存储到session域中
		session.setAttribute("categoryList", categoryList);
		//2.准备最新商品数据
		List<Product> newProductList=productService.selectNewProductList();
		//其实model对象就是springmvc用户存储数据，最终会将该数据存储到request域中
		model.addAttribute("newProductList", newProductList);
		//3.准备热门商品的数据
		List<Product> hotProductList=productService.selectHotProductList();
		model.addAttribute("hotProductList", hotProductList);
		//4.将数据存储到request域中转发到首页进行显示
		return "index";
	}
	
	

	//显示前台登录页面
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	//显示前台注册页面
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}
	

	//显示前台头像
	@RequestMapping("/showuserpicture")
	public String showuserpicture() {
		return "user_picture";
	}

	/**
	 * 
	* <p>Description:管理员后台页面 </p>  
	
	 */
	//后台登录
	@RequestMapping("/adminLogin")
	public String toAdmin(){
		return "admin/adminlogin";
	}
	//后台首页
	@RequestMapping("toAdminIndex")
	public String toAdminIndex(){
		return "admin/index";
	}
	//查看商品
	@RequestMapping("productList")
	public String pathControl(){
		return "admin/productlist";
	}
	//添加商品
	@RequestMapping("addProduct")
	public String toAddProduct(){
		return "admin/productadd";
	}
	
	//主页
	@RequestMapping("welcome")
	public String toWelcome(){
		return "admin/welcome";
	}
	//管理员个人信息
	@RequestMapping("admininfo")
	public String adminInfo(){
		return "admin/info";
	}
	
	//修改密码
	@RequestMapping("updatepass")
	public String updatePass(){
		return "admin/updatepass";
	}
	
	
	//订单管理
	@RequestMapping("orderlist")
	public String toOrderList(){
		return "admin/orderlist";
	}
	//用户管理
	@RequestMapping("userlist")
	public String toUserList(){
		return "admin/userlist";
	}
	
}
