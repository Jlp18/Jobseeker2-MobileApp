package cn.tentact.nebula.email;

import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.email.EmailService;
import cn.tentact.nebula.email.I_EmailController;
import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.web.ResponseBean;
import com.google.common.base.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@SuppressWarnings("all")
public class EmailController implements I_EmailController {
  @Resource
  private EmailService emailService;
  
  @Qualifier("redisTemplate")
  @Autowired
  private RedisTemplate redis;
  
  @Autowired
  private I_UserDao userDao;
  
  /**
   * 验证邮件发送
   */
  @RequestMapping("/sendEmail")
  @Override
  public ResponseBean sendEmail(final String username, final String address) {
    String email = this.userDao.searchEmail(username);
    boolean _equals = Objects.equal(email, address);
    if (_equals) {
      Random random = new Random();
      String result = "";
      for (int i = 0; (i < 6); i++) {
        String _result = result;
        int _nextInt = random.nextInt(10);
        result = (_result + Integer.valueOf(_nextInt));
      }
      this.redis.opsForValue().set("result", result, JwtUtil.CODE_TIME, TimeUnit.MILLISECONDS);
      String sendTo = address;
      String title = "Jobseeker App";
      String content = (("验证码为:" + result) + "(5分钟内有效)");
      this.emailService.sendEmail(sendTo, title, content);
      return new ResponseBean(200, "验证码发送成功", true, Integer.valueOf(1));
    } else {
      return new ResponseBean(200, "邮箱与绑定邮箱不符", true, Integer.valueOf(0));
    }
  }
  
  @RequestMapping("/checkCode")
  @Override
  public ResponseBean checkCode(final String code) {
    Object resultBack = this.redis.opsForValue().get("result");
    boolean _equals = Objects.equal(resultBack, code);
    if (_equals) {
      return new ResponseBean(200, "验证成功", true, Integer.valueOf(1));
    } else {
      return new ResponseBean(200, "验证码错误", true, Integer.valueOf(0));
    }
  }
}
