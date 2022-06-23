package com.ping.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.ping.common.utils.AESUtils;
import com.ping.common.utils.UUIDUtil;
import com.ping.pojo.ResultUtil;
import com.ping.pojo.Shipping;
import com.ping.pojo.User;
import com.ping.service.UserService;

/**
 * 1.用户注册ajax同步判断用户是否存在
 * 2.用户注册
 * 3.用户登录
 * 4.退出登录
 * 5.修改头像
 */
@Controller
public class CheckUserNameController {

	@Autowired
	private UserService userService;

	/**
	 * 1.检查用户名是否存在
	 */
	@RequestMapping("/checkUserName")
	@ResponseBody
	public boolean getUserInfo(String username) {
		boolean checkUserIsExist = userService.checkUserIsExist(username);
		return checkUserIsExist;
	}

	/**
	 * 2.用户注册
	 */
	@RequestMapping(value="/registerUser")
	@ResponseBody
	public ResultUtil getRegisterInfo(User user, String validatecode, HttpServletRequest request, Model model) {
		// 检验验证码是否正确
		// 获取生成放在session中的验证码
		HttpSession session = request.getSession();
		String checkcode = (String) session.getAttribute("code");
		ResultUtil rs=new ResultUtil();
		// 比较验证码是否一致
		if (StringUtils.isEmpty(validatecode)) {
			rs.setCode("01");
			rs.setMessage("验证码不能为空！");
		} else if (!checkcode.equals(validatecode)) {
			// 如果两次验证码不一致，输出提示信息
			rs.setCode("01");
			rs.setMessage("验证码不正确,注意区分大小写！");
		} else {
			// 输入的验证码正确，将用户信息插入到数据库
			try {
				user.setPassword(AESUtils.encryption(user.getPassword()));//密码加密
				user.setUserimg("img/upload/defaulthead.jpg");//默认头像
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean isNotSuccess = userService.addRegisterInfo(user);
			if (isNotSuccess) {
				//System.out.println("注册成功！");
				rs.setCode("00");
				rs.setMessage("注册成功！");
			} else {
				//System.out.println("注册失败！");
				rs.setCode("01");
				rs.setMessage("发生异常！");
			}

		}
		return rs;

	}

	/**
	 * 3.用户登录
	 * 
	 * @throws UnsupportedEncodingException
	 */

	@RequestMapping("/loginUser")
	@ResponseBody
	public ResultUtil login(User user1, HttpServletRequest request, HttpServletResponse response, String validatecode,
			Model model)  {
		// 获取controller生成验证码
		HttpSession session = request.getSession();
		String checkImg = (String) session.getAttribute("code");
		ResultUtil rs=new ResultUtil();//封装响应信息
		// 判断输入的验证码是否为空
		if (StringUtils.isEmpty(validatecode)) {
			// 如果是空，返回到登录页面，输出错误提示信息
			rs.setCode("01");
			rs.setMessage("验证码不能为空！");
		}
		// 判断输入验证码和生成的验证码是否一致
		else if (!checkImg.equals(validatecode)) {
			// 如果不一致就返回登录页面输出错误提示信息
			rs.setCode("01");
			rs.setMessage("验证码不正确,注意区分大小写!");
		} else {
			// 执行到这里，满足验证码输入不为空并且相等，接下来效验用户名和密码
			// 通过用户名和密码查询数据库是否存在该用户
			try {
				user1.setPassword(AESUtils.encryption(user1.getPassword()));//加密
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			User isExistUser = userService.selectByUserNameAndPassword(user1);
			if (isExistUser != null) {
				// 登录成功就将用户存在到session中，以便用来判断用户是否登录
				session.setAttribute("user", isExistUser);
				//登录成功还有做两件事情，判断用户是否选择记住用户名和密码，是否选择自动登录
				
				//1. 判断用户是否选择记住用户名和密码
				String parameter = request.getParameter("remember_me");
				if (parameter != null) {
					// 由于cookie是不能存储中文，对象中文的用户名要进行编码
					String encodeUserName = null;
					try {
						encodeUserName = URLEncoder.encode(isExistUser.getUsername(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 1.用户选择记住用户名和密码，创建cookie将用户名和密码存储到cookie中
					Cookie cookie_username = new Cookie("username1", encodeUserName);
					Cookie cookie_password = null;
					try {
						cookie_password = new Cookie("password1", AESUtils.decrypt(isExistUser.getPassword()));//解密
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 2.设置cookie的存储时间,以秒为单位，设置为一个小时
					cookie_username.setMaxAge(60 * 60);
					cookie_password.setMaxAge(60 * 60);
					// 3.设置cookie的携带路径
					cookie_username.setPath(request.getContextPath());
					cookie_password.setPath(request.getContextPath());
					// 4.将cookie添加到客户端浏览器上
					response.addCookie(cookie_username);
					response.addCookie(cookie_password);

				} else {
					/**
					 * 清除cookie信息，如果这次用户没有选择记住用户名和密码，应该清除上一次存储cookie中用户名和密码
					 */
					// 如果用户没有选择记住用户名和密码，将原来存储在cookie中的用户名和密码清除
					// 1.怎么清楚cookie，就是创建和原来名称一样cookie，存储null,将原来覆盖，并且设置存储时间为0
					Cookie cookie_username = new Cookie("username1", null);
					Cookie cookie_password = new Cookie("password1", null);
					// 2.设置存储时间为0
					cookie_username.setMaxAge(0);
					cookie_password.setMaxAge(0);
					// 3.设置cookie的携带路径
					cookie_username.setPath(request.getContextPath());
					cookie_password.setPath(request.getContextPath());
					// 4.将该cookie添加到客户端
					response.addCookie(cookie_username);
					response.addCookie(cookie_password);
				}
				
				// 判断用户是否选择自动登录
				String autoLogin = request.getParameter("autologin");
				if (autoLogin != null) {
					// 说明用户选择自动登录，将用户的用户名和密码存储到cookie中
					// 由于cookie是不能存储中文，对象中文的用户名要进行编码
					String encodeUserName = null;
					try {
						encodeUserName = URLEncoder.encode(isExistUser.getUsername(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 1.用户选择记住用户名和密码，创建cookie将用户名和密码存储到cookie中
					Cookie cookie_username = new Cookie("username2", encodeUserName);
					Cookie cookie_password = null;
					try {
						cookie_password = new Cookie("password2", AESUtils.decrypt(isExistUser.getPassword()));//解密
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 2.设置cookie的存储时间,以秒为单位，设置为一个小时
					cookie_username.setMaxAge(60 * 60);
					cookie_password.setMaxAge(60 * 60);
					// 3.设置cookie的携带路径
					cookie_username.setPath(request.getContextPath());
					cookie_password.setPath(request.getContextPath());
					// 4.将cookie添加到客户端浏览器上
					response.addCookie(cookie_username);
					response.addCookie(cookie_password);

				} else {
					/**
					 * 清除cookie信息,这次没有选择自动登录，应该清空上一次选择自动登录的cookie信息
					 */
					// 如果用户没有选择记住用户名和密码，将原来存储在cookie中的用户名和密码清除
					// 1.怎么清楚cookie，就是创建和原来名称一样cookie，存储null,将原来覆盖，并且设置存储时间为0
					Cookie cookie_username = new Cookie("username2", null);
					Cookie cookie_password = new Cookie("password2", null);
					
					// 2.设置存储时间为0
					cookie_username.setMaxAge(0);
					cookie_password.setMaxAge(0);
					// 3.设置cookie的携带路径
					cookie_username.setPath(request.getContextPath());
					cookie_password.setPath(request.getContextPath());
					// 4.将该cookie添加到客户端
					response.addCookie(cookie_username);
					response.addCookie(cookie_password);
					
				}
				
				//登录成功
				rs.setCode("00");
			} else {
				rs.setCode("01");
				rs.setMessage("用户名或者密码错误，请从新输入！");
			}
		}
		return rs;

	}

	/**
	 * 4.退出登录，就是将存储在session中的用户删除掉，相当于注销用户
	 */
	@RequestMapping("/deleteUser")
	public String deleteUserInfo(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		//清除cookie中用户自动登录的用户名和密码
		/**
		 * 清除cookie信息,自动登录，在选择退出登录应该清除该cookie信息，
		 * 不然每次访问项目任何东西都走拦截器都会取cookie中用户名和密码，
		 * 每次都登录，每次存储用户到session，使得退出登录点击没有效果
		 */
		// 如果用户没有选择记住用户名和密码，将原来存储在cookie中的用户名和密码清除
		// 1.怎么清楚cookie，就是创建和原来名称一样cookie，存储null,将原来覆盖，并且设置存储时间为0
		Cookie cookie_username = new Cookie("username2", null);
		Cookie cookie_password = new Cookie("password2", null);
		//2.设置存储时间为0
		cookie_username.setMaxAge(0);
		cookie_password.setMaxAge(0);
		//3.设置cookie的携带路径
		cookie_username.setPath(request.getContextPath());
		cookie_password.setPath(request.getContextPath());
		//4.将该cookie添加到客户端
		response.addCookie(cookie_username);
		response.addCookie(cookie_password);
		/**
		 * 记住用户名和密码就不要清除cookie，不然点击退出登录，显示登录页面就无法从cookie中获取用户名和密码显示到控件上
		 */
		return "redirect:login";
	}
	
	/**
	 * 用户上传头像
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/userUploadPicture",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject uploadHeaderPic(@PathVariable(value="file")MultipartFile file,HttpServletRequest request){
		String str = file.getOriginalFilename();//原图片名称
		String name=UUIDUtil.getUUID()+str.substring(str.lastIndexOf("."));//UUID生成新图片名称
		String path=request.getSession().getServletContext().getRealPath("/img/upload")+"/"+name;//保存上传图片路径
		try {
			file.transferTo(new File(path));//上传文件
			//更新用户头像路径
			User user = (User) request.getSession().getAttribute("user");
			user.setUserimg("img/upload/"+name);
			userService.updateUserImg(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject obj=new JSONObject();
		obj.put("userImg", name);
		return obj;
	}
	
	/**
	 * 回显个人资料
	 */
	@RequestMapping("/showuserinfo")
	public String showuserinfo(HttpServletRequest request,Model model)
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
		User newUser=new User();
		try {
			User dbUser=userService.selectUserById(user.getUid());
			BeanUtils.copyProperties(dbUser, newUser);
			newUser.setPassword(AESUtils.decrypt(newUser.getPassword()));//解密
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("user", newUser);
		return "edituser";
	}
	
	/**
	 * 个人资料修改保存
	 */
	@RequestMapping(value="/updateuserinfo")
	@ResponseBody
	public ResultUtil updateUserInfo(User user,HttpServletRequest request) {
		
		ResultUtil rs=new ResultUtil();
		try {
			user.setPassword(AESUtils.encryption(user.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//密码加密
		boolean isNotSuccess = userService.updateUserInfo(user);
		//更新session中的用户
		HttpSession session = request.getSession();
		User dbUser=userService.selectUserById(user.getUid());
        session.setAttribute("user", dbUser);
		if (isNotSuccess) {
			rs.setCode("00");
			rs.setMessage("个人资料修改成功！");
		} else {
			rs.setCode("01");
			rs.setMessage("个人资料修改失败！");
		}
		return rs;
	}
}
