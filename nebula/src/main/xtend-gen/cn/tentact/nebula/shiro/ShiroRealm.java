package cn.tentact.nebula.shiro;

import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * Shiro认证与授权类
 */
@SuppressWarnings("all")
public class ShiroRealm extends AuthorizingRealm {
  /**
   * 授权函数
   */
  @Override
  public AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection collection) {
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    Object _attribute = session.getAttribute("privileges");
    List<String> privileges = ((List<String>) _attribute);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addStringPermissions(privileges);
    return info;
  }
  
  /**
   * 认证函数
   */
  @Override
  public AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {
    SimpleAuthenticationInfo _xblockexpression = null;
    {
      UsernamePasswordToken tk = ((UsernamePasswordToken) token);
      String username = tk.getUsername();
      char[] _password = tk.getPassword();
      String password = new String(_password);
      String _name = this.getName();
      SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, _name);
      _xblockexpression = info;
    }
    return _xblockexpression;
  }
}
