package cn.tentact.nebula.email;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_EmailController {
  public abstract ResponseBean sendEmail(final String username, final String address);
  
  public abstract ResponseBean checkCode(final String code);
}
