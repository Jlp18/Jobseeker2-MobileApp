package cn.tentact.nebula.email

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Value

@Component
class EmailConfig {
	/**
	 * 发送邮件
	 */
	 @Value("${spring.mail.username}")
	 String emailFrom;
	 
	 def String getEmailFrom(){
	 	return emailFrom;
	 }
	 
	 def void setEmailFrom(String emailFrom){
	 	this.emailFrom = emailFrom;
	 } 
}