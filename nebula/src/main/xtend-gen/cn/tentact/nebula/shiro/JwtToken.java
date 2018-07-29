package cn.tentact.nebula.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT令牌类
 */
@SuppressWarnings("all")
public class JwtToken implements AuthenticationToken {
  private String token;
  
  public JwtToken(final String token) {
    this.token = token;
  }
  
  @Override
  public Object getPrincipal() {
    return this.token;
  }
  
  @Override
  public Object getCredentials() {
    return this.token;
  }
}
