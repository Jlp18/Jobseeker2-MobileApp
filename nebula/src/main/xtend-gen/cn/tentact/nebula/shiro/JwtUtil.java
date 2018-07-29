package cn.tentact.nebula.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * JWT工具类
 */
@SuppressWarnings("all")
public class JwtUtil {
  public final static long EXPIRE_TIME = ((15 * 60) * 1000);
  
  public final static long CODE_TIME = ((5 * 60) * 1000);
  
  /**
   * 校验token是否正确
   * @param token 密钥
   * @param secret 用户的密码
   * @return 是否正确
   */
  public static boolean verify(final String token, final String username, final String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
      verifier.verify(token);
      return true;
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception exception = (Exception)_t;
        return false;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  /**
   * 获得token中的信息无需secret解密也能获得
   * @return token中包含的用户名
   */
  public static String getUsername(final String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("username").asString();
    } catch (final Throwable _t) {
      if (_t instanceof JWTDecodeException) {
        final JWTDecodeException e = (JWTDecodeException)_t;
        return null;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  /**
   * 生成签名,5min后过期
   * @param username 用户名
   * @param secret 用户的密码
   * @return 加密的token
   */
  public static String sign(final String username, final String secret) {
    try {
      long _currentTimeMillis = System.currentTimeMillis();
      long _plus = (_currentTimeMillis + JwtUtil.EXPIRE_TIME);
      Date date = new Date(_plus);
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
    } catch (final Throwable _t) {
      if (_t instanceof UnsupportedEncodingException) {
        final UnsupportedEncodingException e = (UnsupportedEncodingException)_t;
        return null;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
