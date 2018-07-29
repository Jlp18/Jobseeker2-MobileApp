package cn.tentact.nebula.shiro

import org.apache.shiro.authc.AuthenticationToken

/**
 * JWT令牌类
 */
class JwtToken implements AuthenticationToken {

	// 密钥
	String token;

	new(String token) {
		this.token = token;
	}

	override getPrincipal() {
		return token;
	}

	override getCredentials() {
		return token;
	}
}
