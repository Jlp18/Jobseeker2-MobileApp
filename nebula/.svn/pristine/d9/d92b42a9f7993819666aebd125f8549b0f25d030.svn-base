package cn.tentact.nebula.recruit

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.web.bind.annotation.RequestHeader
import cn.tentact.nebula.shiro.JwtUtil
import cn.tentact.nebula.web.ResponseBean

@RestController
@RequestMapping("/recruit")
class RecruitlistNewController implements I_RecruitlistNewController{	
	@Autowired
	I_RecruitlistNewService recruitlistnewService;
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchRecruitlistNew")
	
	override searchRecruitlistNew(@RequestHeader("Authorization") String token, Integer d) {
		var username=JwtUtil.getUsername(token);
		var list=recruitlistnewService.searchRecruitlistNew(username,d);
		return new ResponseBean(200,"招聘信息",true,list);
	}
	
}