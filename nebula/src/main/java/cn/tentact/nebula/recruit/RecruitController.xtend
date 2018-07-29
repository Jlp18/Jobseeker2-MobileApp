package cn.tentact.nebula.recruit

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.apache.shiro.authz.annotation.RequiresRoles
import cn.tentact.nebula.web.ResponseBean
import org.springframework.web.bind.annotation.RequestHeader
import cn.tentact.nebula.shiro.JwtUtil

@RestController
@RequestMapping("/recruit")
class RecruitController implements I_RecruitController{
	@Autowired
	I_RecruitService recruitService;

	@RequiresRoles("求职者")
	@RequestMapping("/searchCurrentRecruit")
	override searchCurrentRecruit(@RequestHeader("Authorization") String token) {
		var username=JwtUtil.getUsername(token);
		var list=recruitService.searchCurrentRecruit(username);
		return new ResponseBean(200,"最新招聘信息",true,list);
	}
	
}