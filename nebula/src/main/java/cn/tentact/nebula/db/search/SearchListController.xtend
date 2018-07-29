package cn.tentact.nebula.db.search

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.apache.shiro.authz.annotation.RequiresRoles
import cn.tentact.nebula.shiro.JwtUtil
import cn.tentact.nebula.web.ResponseBean
import org.springframework.web.bind.annotation.RequestHeader

@RestController
@RequestMapping("/search")
class SearchListController implements I_SearchListController{
	@Autowired
	I_SearchListService searchlistService;
	
	@RequestMapping("/searchListDefault")
	@RequiresRoles("求职者")
	override searchListDefault(@RequestHeader("Authorization") String token, String content) {
		var username=JwtUtil.getUsername(token);
		var list=searchlistService.searchListDefault(username,content);
		return new ResponseBean(200,"搜索招聘信息",true,list);
	}
	
	@RequestMapping("/searchListNew")	
	@RequiresRoles("求职者")
	override searchListNew(@RequestHeader("Authorization") String token, String content) {
		var username=JwtUtil.getUsername(token);
		var list=searchlistService.searchListNew(username,content);
		return new ResponseBean(200,"搜索的最新招聘信息",true,list);
	}
	
	@RequestMapping("/searchListSalarymin")	
	@RequiresRoles("求职者")
	override searchListSalarymin(@RequestHeader("Authorization") String token, String content) {
		var username=JwtUtil.getUsername(token);
		var list=searchlistService.searchListSalarymin(username,content);
		return new ResponseBean(200,"搜索的按照最低月薪排序的招聘信息",true,list);
	}
	
	@RequestMapping("/searchListSalarymax")	
	@RequiresRoles("求职者")
	override searchListSalarymax(@RequestHeader("Authorization") String token, String content) {
		var username=JwtUtil.getUsername(token);
		var list=searchlistService.searchListSalarymax(username,content);
		return new ResponseBean(200,"搜索的按照最高月薪排序的招聘信息",true,list);
	}
	
	
}