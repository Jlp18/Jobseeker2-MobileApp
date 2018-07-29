package cn.tentact.nebula.test

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/test")
class TestController {
	@RequestMapping("/hello")
	def sayHello(){
		return #{"result"->"你好世界"}	
	}	
	@RequestMapping("/login")
	def login(String username,String password){
		return #{"username" -> username, "password" -> password}
	}
}