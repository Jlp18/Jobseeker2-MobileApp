package cn.tentact.nebula.recruit

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestHeader
import cn.tentact.nebula.shiro.JwtUtil
import cn.tentact.nebula.web.ResponseBean
import cn.tentact.nebula.db.dao.I_RecruitlistDefaultDao

@RestController
@RequestMapping("/recruit")
class RecruitlistDefaultController implements I_RecruitlistDefaultController{
	@Autowired
	I_RecruitlistDefaultService recruitlistdefaultService;
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchRecruitlistDefault")
	override searchRecruitlistDefault(@RequestHeader("Authorization") String token,Integer s) {
		var username=JwtUtil.getUsername(token);
		var list=recruitlistdefaultService.searchRecruitlistDefault(username,s);
		return new ResponseBean(200,"招聘信息",true,list);
	}
}