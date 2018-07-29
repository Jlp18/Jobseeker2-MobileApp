package cn.tentact.nebula.company

import cn.tentact.nebula.web.ResponseBean

interface I_CompanyController {
	def ResponseBean insert(String token,String name,String tel,String website,String scale,String type,String resume);
	def ResponseBean selectFromMongo(String token,Integer company_id);
	def ResponseBean selectFromMysql(String token,int company_id);
}