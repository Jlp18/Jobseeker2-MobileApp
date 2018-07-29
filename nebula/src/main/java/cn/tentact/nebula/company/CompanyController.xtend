package cn.tentact.nebula.company

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestHeader
import cn.tentact.nebula.web.ResponseBean

@RestController
@RequestMapping("/company")
class CompanyController implements I_CompanyController {
	@Autowired
	I_CompanyService companyService;
	
	@RequestMapping("/insert")
	override insert(@RequestHeader("Authorization")String token, String name, String tel, String website, String scale, String type,String resume) {
		var map=#{
			"name"->name,
			"tel"->tel,
			"website"->website,
			"scale"->scale,
			"type"->type,
			"resume"->resume
		};
		companyService.insert(map);
		return new ResponseBean(200,"保存成功",true,null);
	}
	
	@RequestMapping("/selectFromMongo")
	override selectFromMongo(@RequestHeader("Authorization")String token,Integer company_id) {
		var company=companyService.selectFromMongo(company_id);
		return new ResponseBean(200,"查询成功",true,company);
	}
	
	@RequestMapping("/selectFromMysql")
	override selectFromMysql(@RequestHeader("Authorization")String token, int company_id) {
		var map=companyService.selectFromMysql(company_id);
		return new ResponseBean(200,"查询成功",true,map);
	}
	
	
}