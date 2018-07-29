package cn.tentact.nebula.user

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import cn.tentact.nebula.shiro.JwtUtil
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.web.ResponseBean
import org.apache.shiro.authz.annotation.RequiresRoles

@RestController
@RequestMapping("/userlevel")
class UserLevelController implements I_UserLevelController {
	@Autowired
	I_UserLevelService userLevelService;
	
	@RequiresRoles("求职者")
	@RequestMapping("/updateExp_1")
	override updateExp_1(@RequestHeader("Authorization") String token) {
		var username=JwtUtil.getUsername(token);
		var map=newHashMap("username"->username,"exp"->10);
		var i=userLevelService.updateExp(map);
		return new ResponseBean(200,"更改结果",true,i);
	}
	
}