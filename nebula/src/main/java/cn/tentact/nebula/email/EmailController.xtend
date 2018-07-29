package cn.tentact.nebula.email

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import javax.annotation.Resource
import cn.tentact.nebula.web.ResponseBean
import java.util.Random
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_UserDao
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.beans.factory.annotation.Qualifier
import cn.tentact.nebula.shiro.JwtUtil
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/email")
class EmailController implements I_EmailController {
	@Resource
	EmailService emailService;
	
	@Qualifier("redisTemplate")
	@Autowired
	RedisTemplate redis;
	
	@Autowired
	I_UserDao userDao;
	
	
	/**
	 * 验证邮件发送
	 */
	 @RequestMapping("/sendEmail")
	 override sendEmail(String username,String address) {
	 	var email=userDao.searchEmail(username);
	 	if(email==address){
	 		var random=new Random();
	 		var result="";
	 		for(var i=0;i<6;i++){
	 			result+=random.nextInt(10);
	 		}
	 		redis.opsForValue().set("result",result,JwtUtil.CODE_TIME, TimeUnit.MILLISECONDS);
	 		var sendTo=address;
	 		var title="Jobseeker App";
	 		var content="验证码为:"+result+"(5分钟内有效)";
	 		emailService.sendEmail(sendTo,title,content);  //发送验证码
	 		return new ResponseBean(200,"验证码发送成功",true,1);
	 	}
	 	else{
	 		return new ResponseBean(200,"邮箱与绑定邮箱不符",true,0);
	 	}
	 	
	 }
	 
	 @RequestMapping("/checkCode")
	 override checkCode(String code) {
	 	var resultBack = redis.opsForValue().get("result");
	 	if(resultBack==code){
	 		return new ResponseBean(200,"验证成功",true,1);
	 	}
	 	else{
	 		return new ResponseBean(200,"验证码错误",true,0);
	 	}
	 }
	 
}