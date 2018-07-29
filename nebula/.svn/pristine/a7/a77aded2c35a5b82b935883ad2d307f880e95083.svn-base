package cn.tentact.nebula.shiro

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import java.io.UnsupportedEncodingException
import java.util.Date

/**
 * JWT工具类
 */
class JwtUtil {
	//用户登录信息  过期时间15分钟
	public static final long EXPIRE_TIME = 15 * 60 * 1000;
	//验证码 过期时间5分钟
	public static final long CODE_TIME = 5 * 60 * 1000;
	

	/**
	 * 校验token是否正确
	 * @param token 密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	def static verify(String token, String username, String secret) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			var verifier = JWT.require(algorithm).withClaim("username", username).build();
			verifier.verify(token);
			return true;
		} catch(Exception exception) {
			return false;
		}
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 * @return token中包含的用户名
	 */
	def static getUsername(String token) {
		try {
			var jwt = JWT.decode(token);
			return jwt.getClaim("username").asString();
		} catch(JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 生成签名,5min后过期
	 * @param username 用户名
	 * @param secret 用户的密码
	 * @return 加密的token
	 */
	def static sign(String username, String secret) {
		try {
			var date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			var algorithm = Algorithm.HMAC256(secret);
			// 附带username信息
			return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
		} catch(UnsupportedEncodingException e) {
			return null;
		}
	}
}
