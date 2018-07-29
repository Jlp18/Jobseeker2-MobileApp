package cn.tentact.nebula.email;

@SuppressWarnings("all")
public interface I_EmailService {
  /**
   * 发送简单邮件
   * @param sendTo 收件人地址
   * @param title  邮件标题
   * @param content 邮件内容
   */
  public abstract void sendEmail(final String sendTo, final String title, final String content);
}
