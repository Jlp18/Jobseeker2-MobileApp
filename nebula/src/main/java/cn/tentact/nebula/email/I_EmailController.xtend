package cn.tentact.nebula.email

import cn.tentact.nebula.web.ResponseBean

interface I_EmailController {
	def ResponseBean sendEmail(String username,String address);
	def ResponseBean checkCode(String code);
}