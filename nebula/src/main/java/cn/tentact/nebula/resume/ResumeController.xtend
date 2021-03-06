package cn.tentact.nebula.resume

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.shiro.JwtUtil
import cn.tentact.nebula.web.ResponseBean
import org.apache.shiro.authz.annotation.RequiresRoles
import cn.tentact.nebula.db.dao.I_UserDao

@RestController
@RequestMapping("/resume")
class ResumeController implements I_ResumeController {
	@Autowired
	I_ResumeService resumeService;
	
	@Autowired
	I_UserDao userDao;
	
	@RequiresRoles("求职者")
	@RequestMapping("/sendMyResume")
	override sendMyResume(@RequestHeader("Authorization") String token, int recruitId) {
		var username=JwtUtil.getUsername(token);
		var map=newHashMap("username"->username,"recruitId"->recruitId);
		var i=resumeService.sendMyResume(map);
		return new ResponseBean(200,"投递结果",true,i);
		
		
	}
	@RequestMapping("/updateResumeinfo")
	override updateResumeinfo(@RequestHeader("Authorization")String token,String name,String sex,
		String birthday,String height,String weight,String married,String city,String education,String tel,
		String email,String job_city,String job_salary,String job_name,String job_hiredate,String school,String major,String year,String education_1,String company_name,String hiredate,String leavedate,String works
	) {
		var username=JwtUtil.getUsername(token);
		var id=userDao.searchId(username);
		var map=#{
			"name"->name,
			"sex"->sex,
			"birthday"->birthday,
			"height"->height,
			"weight"->weight,
			"married"->married,
			"city"->city,
			"education"->education,
			"tel"->tel,
			"email"->email,
			"user_id"->id,
			"job_city"->job_city,
			"job_salary"->job_salary,
			"job_name"->job_name,
			"job_hiredate"->job_hiredate,
			"school"->school,
			"major"->major,
			"year"->year,
			"education_1"->education_1,
			"company_name"->company_name,
			"hiredate"->hiredate,
			"leavedate"->leavedate,
			"works"->works
			
		};
		resumeService.updateResumeinfo(map);
		return new ResponseBean(200,"简历修改成功",true,null);
   }
	@RequestMapping("/insertResumeinfo")		
	override insertResumeinfo(@RequestHeader("Authorization")String token, String name,String auth, String sex, String user_id, String birthday, String height, String weight, String married, String city, String education, String tel, String email,String job_city,String job_salary,String job_name,String job_hiredate,String school,String major,String year,String education_1,String company_name,String hiredate,String leavedate,String works) {
		var username=JwtUtil.getUsername(token);
		var id=userDao.searchId(username);
		var map=#{
			"name"->name,
			"auth"->auth,
			"sex"->sex,
			"birthday"->birthday,
			"height"->height,
			"weight"->weight,
			"married"->married,
			"city"->city,
			"education"->education,
			"tel"->tel,
			"email"->email,
			"user_id"->id,
			"job_city"->job_city,
			"job_salary"->job_salary,
			"job_name"->job_name,
			"job_hiredate"->job_hiredate,
			"school"->school,
			"major"->major,
			"year"->year,
			"education_1"->education_1,
			"company_name"->company_name,
			"hiredate"->hiredate,
			"leavedate"->leavedate,
			"works"->works
		};
		resumeService.insertResumeinfo(map);
		return new ResponseBean(200,"保存成功",true,null);	
	}
	
	@RequestMapping("/selectResumeinfo")
	override selectResumeinfo(@RequestHeader("Authorization")String token) {
		var username=JwtUtil.getUsername(token);
		var id=userDao.searchId(username);
		var map=resumeService.selectResumeinfo(id);
		return new ResponseBean(200,"查询成功",true,map);
	}
	
	@RequestMapping("/updatePhoto")
	override updatePhoto(@RequestHeader("Authorization")String token, String photo_path) {
		var username=JwtUtil.getUsername(token);
		var map=#{
			"photo_path"->photo_path,
			"username"->username
		};
		resumeService.updatePhoto(map);
		return new ResponseBean(200,"上传结果",true,null);
	}
	
}