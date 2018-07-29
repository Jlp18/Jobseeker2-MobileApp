package cn.tentact.nebula.email


import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender


@Service
class EmailService implements I_EmailService {
	@Autowired
	EmailConfig emailConfig;
	@Autowired
	JavaMailSender mailSender;
	
	override sendEmail(String sendTo, String title, String content) {
		var message=new SimpleMailMessage();
		message.setFrom(emailConfig.getEmailFrom());
		message.setTo(sendTo);
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
	}
	
}