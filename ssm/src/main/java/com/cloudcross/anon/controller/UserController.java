package com.cloudcross.anon.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudcross.anon.model.User;
import com.cloudcross.anon.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource
	private IUserService userService;

	// Jetty 和 JUnit 测试用： /user/test?id=1
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		System.out.println("userId:" + userId);
		User user = null;
		if (userId == 1) {
			user = new User();
			user.setAge(11);
			user.setId(1);
			user.setPassword("123");
			user.setUserName("javen");
		}

		log.debug(user.toString());
		model.addAttribute("user", user);
		return "index";
	}

	// /user/showUser?id=1 HttpServletRequest //以HTTP请求的方式取值
	@RequestMapping(value = "/showUser", method = RequestMethod.GET)
	public String toIndex(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id")); 
		System.out.println("userId:" + userId);
		User user = this.userService.getUserById(userId);
		log.debug(user.toString());
		model.addAttribute("user", user);
		return "showUser";
	}

	// /user/showUser2?id=1 @RequestParam //以参数的方式取值
	@RequestMapping(value = "/showUser2", method = RequestMethod.GET)
	public String toIndex2(@RequestParam("id") String id, Model model) {
		int userId = Integer.parseInt(id);
		System.out.println("userId:" + userId);
		User user = this.userService.getUserById(userId);
		log.debug(user.toString());
		model.addAttribute("user", user);
		return "showUser";
	}

	// /user/showUser3/{id} Map @PathVariable
	@RequestMapping(value = "/showUser3/{id}", method = RequestMethod.GET)
	public String toIndex3(@PathVariable("id") String id,Map<String, Object> model) {
		int userId = Integer.parseInt(id);
		System.out.println("userId:" + userId);
		User user = this.userService.getUserById(userId);
		log.debug(user.toString());
		model.put("user", user);
		return "showUser";
	}

	// /user/{id}
	// http://localhost:8080/ssm/user/1
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUserInJson(@PathVariable String id, Map<String, Object> model) {
		int userId = Integer.parseInt(id);
		System.out.println("userId:" + userId);
		User user = this.userService.getUserById(userId);
		log.info(user.toString());
		return user;
	}
	
	// /user/{id}
	// http://localhost:8080/ssm/user/jsontype/1
    @RequestMapping(value="/jsontype/{id}",method=RequestMethod.GET)  
    public ResponseEntity<User>  getUserInJson2(@PathVariable String id,Map<String, Object> model){  
        int userId = Integer.parseInt(id);  
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);  
        log.info(user.toString());
        return new ResponseEntity<User>(user,HttpStatus.OK);  
    } 

}
