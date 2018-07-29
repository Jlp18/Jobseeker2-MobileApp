package cn.tentact.nebula.shiro

import java.util.Map
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.beans.factory.annotation.Qualifier
import cn.tentact.nebula.role.I_RoleService

/**
 * JWT的Realm类
 */
class JwtRealm extends AuthorizingRealm {
	@Qualifier("redisTemplate")
	@Autowired
	RedisTemplate redis;
	
	@Autowired
	I_RoleService roleService;


	/**
	 * 大坑！，必须重写此方法，不然Shiro会报错
	 */
	override supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 授权方法
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 */
	override doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取令牌
		var token=principals.toString();
		//获取用户名
		var username=JwtUtil.getUsername(token);
		//查询用户角色
		var role=roleService.searchUserRole(username);
		//返回用户角色
		var info=new SimpleAuthorizationInfo();
		info.addRole(role);
		return info;
	}

	/**
	 * 认证方法
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
	 */
	override doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		var token=auth.credentials as String; //用户上传的令牌
		var username=JwtUtil.getUsername(token);  //取出令牌中的用户名
		if(username==null){
			throw new AuthenticationException("令牌无效");
		}
		
		//查找Redis中的令牌数据
		var record=redis.opsForHash.entries(username);
		if(record==null){
			throw new AuthenticationException("令牌无效");
		}
		
		//验证令牌的有效性
		var password=record.get("password") as String;
		if(JwtUtil.verify(token,username,password)==false){
			throw new AuthenticationException("令牌无效");
		}
		return new SimpleAuthenticationInfo(token, token, "jwt_realm");
	}
}
