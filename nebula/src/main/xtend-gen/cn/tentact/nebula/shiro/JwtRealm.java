package cn.tentact.nebula.shiro;

import cn.tentact.nebula.role.I_RoleService;
import cn.tentact.nebula.shiro.JwtToken;
import cn.tentact.nebula.shiro.JwtUtil;
import com.google.common.base.Objects;
import java.util.Map;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * JWT的Realm类
 */
@SuppressWarnings("all")
public class JwtRealm extends AuthorizingRealm {
  @Qualifier("redisTemplate")
  @Autowired
  private RedisTemplate redis;
  
  @Autowired
  private I_RoleService roleService;
  
  /**
   * 大坑！，必须重写此方法，不然Shiro会报错
   */
  @Override
  public boolean supports(final AuthenticationToken token) {
    return (token instanceof JwtToken);
  }
  
  /**
   * 授权方法
   * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
   */
  @Override
  public AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
    String token = principals.toString();
    String username = JwtUtil.getUsername(token);
    String role = this.roleService.searchUserRole(username);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addRole(role);
    return info;
  }
  
  /**
   * 认证方法
   * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
   */
  @Override
  public AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken auth) throws AuthenticationException {
    Object _credentials = auth.getCredentials();
    String token = ((String) _credentials);
    String username = JwtUtil.getUsername(token);
    boolean _equals = Objects.equal(username, null);
    if (_equals) {
      throw new AuthenticationException("令牌无效");
    }
    Map record = this.redis.<Object, Object>opsForHash().entries(username);
    boolean _equals_1 = Objects.equal(record, null);
    if (_equals_1) {
      throw new AuthenticationException("令牌无效");
    }
    Object _get = record.get("password");
    String password = ((String) _get);
    boolean _verify = JwtUtil.verify(token, username, password);
    boolean _equals_2 = (_verify == false);
    if (_equals_2) {
      throw new AuthenticationException("令牌无效");
    }
    return new SimpleAuthenticationInfo(token, token, "jwt_realm");
  }
}
