package cn.tentact.nebula.recruit

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.web.bind.annotation.RequestHeader
import cn.tentact.nebula.web.ResponseBean

@RestController
@RequestMapping("/recruitDetails")
class RecruitDetailsController implements I_RecruitDetailsController {
	@Autowired
	I_RecruitDetailsService recruitDetailsService;
	
	@RequiresRoles("求职者")
	@RequestMapping("/fromMysql")
	override RecruitDetails(@RequestHeader("Authorization")String token,int recruit_id) {
		var map=recruitDetailsService.RecruitDetails(recruit_id);
		return new ResponseBean(200,"招聘信息详情",true,map);
	}
	
	@RequiresRoles("求职者")
	@RequestMapping("/fromMongo")
	override searchJobMessage(@RequestHeader("Authorization")String token, int recruit_id) {
		var job=recruitDetailsService.searchJobMessage(recruit_id);
		return new ResponseBean(200,"职位介绍详情",true,job);
	}
	
}