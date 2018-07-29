package cn.tentact.nebula.email;

import cn.tentact.nebula.email.EmailConfig;
import cn.tentact.nebula.email.I_EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class EmailService implements I_EmailService {
  @Autowired
  private EmailConfig emailConfig;
  
  @Autowired
  private JavaMailSender mailSender;
  
  @Override
  public void sendEmail(final String sendTo, final String title, final String content) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(this.emailConfig.getEmailFrom());
    message.setTo(sendTo);
    message.setSubject(title);
    message.setText(content);
    this.mailSender.send(message);
  }
}
