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
class RecruitListController implements I_RecruitListController{
	@Autowired
	I_RecruitListService recruitlistService;
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchRecruitListDefault")
	override searchRecruitListDefault(@RequestHeader("Authorization") String token, Integer s) {
		var username=JwtUtil.getUsername(token);
		var list=recruitlistService.searchRecruitListDefault(username,s);
		return new ResponseBean(200,"招聘信息",true,list);
	}
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchRecruitListNew")
	override searchRecruitListNew(@RequestHeader("Authorization") String token, Integer d) {
		var username=JwtUtil.getUsername(token);
		var list=recruitlistService.searchRecruitListNew(username,d);
		return new ResponseBean(200,"招聘信息",true,list);
	}
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchRecruitListSalarymin")
	override searchRecruitListSalarymin(@RequestHeader("Authorization") String token, Integer smin) {
		var username=JwtUtil.getUsername(token);
		var list=recruitlistService.searchRecruitListSalarymin(username,smin);
		return new ResponseBean(200,"最低月薪排序",true,list);
	}
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchRecruitListSalarymax")
	override searchRecruitListSalarymax(@RequestHeader("Authorization") String token, Integer smax) {
		var username=JwtUtil.getUsername(token);
		var list=recruitlistService.searchRecruitListSalarymax(username,smax);
		return new ResponseBean(200,"最高月薪排序",true,list);
	}
	

}