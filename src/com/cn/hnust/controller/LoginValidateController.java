package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.cn.hnust.pojo.Information;
import com.cn.hnust.service.LoginValidateService;
import com.cn.hnust.util.MessagePropertiesUtil;

/**
 * 用户验证信息，验证是否处于被邀请的名单信息中
 * @author zcg
 * @time 2017-9-18
 * @since
 * 		第一次修订，增加了服务器文件的修订的限制数据  2017-9-24  22:08:12
 *
 *		第二次修订，增加空字符串得验证信息
 *
 *		第三次修订，防止某些无法识别的用户的id值生成不了二维码数据信息
 */
@Controller
@RequestMapping("/user")
public class LoginValidateController {
	
	@Autowired
	private LoginValidateService loginValidateSerice;
	
	//获取项目的url地址信息
	private String httpUrl=MessagePropertiesUtil.getProperties("httpUrl");
	/**
	 * 调用方式：
	 * 		http://域名信息/Test/user/login.action?user=小明&phone=18268137602&email=zcswl7961@126.com&inviter=三国
	 * 返回json:
	 * 		{'flag':'','qrcodeUrl':'http://pl.wamwv.cn/Test/page/asdfasdf.png'};
	 * 		flag:1,表示调用成功
	 * 		qrcodeUrl:表示返回的二维码的数据信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(@RequestBody Information information){
		System.out.println("接口调用到了该方法，获取到的信息为:"+information.getExhName()+"&"+information.getExhPhone());
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		// 得到文件绝对路径
		String realPath = servletContext.getRealPath("/uploadFile");
		System.out.println("获取到的路径信息为："+realPath);
		String path=loginValidateSerice.validate(information,realPath,httpUrl);
		return path;
	}
	/**
	 * 将指定的url数据（扫码数据中隐藏的url连接）
	 * 		当第一次扫码之后，顺势调用服务点的接口，调用之后进行二维码扫描次数的显示
	 * 		这个页面的开头要设置成这样，第一次会访问url参数的信息，显示出来，同时调用服务端的一个接口，进行验证扫码的次数信息
	 * 		同时要加入相应的时间的限制信息，如果不是当天时间，限制为未公开时间
	 * 	return 
	 * 		flag:0 表示二维码失效
	 * 		flag:二维码成功
	 */
	@ResponseBody
	@RequestMapping(value="/identify.action",method=RequestMethod.POST)
	public String identify(@RequestBody Information information){
		String identify = loginValidateSerice.identify(information);
		return identify;
	}
	//传参案例设计
	@ResponseBody
	@RequestMapping(value="/set.action",method=RequestMethod.POST)
	public Map<String,String> set(@RequestBody Information information){
		Map<String,String> map=new HashMap<>();
		map.put("a", "set");
		map.put("2", "a");
		return map;
	}
}
