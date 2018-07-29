package cn.tentact.nebula.shiro

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.SimpleAuthorizationInfo
import java.util.List

/**
 * Shiro认证与授权类
 */
class ShiroRealm extends AuthorizingRealm {
	
	/**
	 * 授权函数
	 */
	override doGetAuthorizationInfo(PrincipalCollection collection) {
		var subject = SecurityUtils.getSubject();
		var session = subject.getSession();
		var privileges = session.getAttribute("privileges") as List<String>; // 权限列表
		var info = new SimpleAuthorizationInfo();
		info.addStringPermissions(privileges);
		return info; // 返回用户的授权信息
	}

	/**
	 * 认证函数
	 */
	override doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		var tk = token as UsernamePasswordToken;
		var username = tk.getUsername(); // 用户名
		var password = new String(tk.getPassword()); // 密码
		var info = new SimpleAuthenticationInfo(username, password, getName());
		info; // 返回认证对象
	}
}
